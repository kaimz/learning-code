package com.wuwii.service.impl;

import com.wuwii.dao.EmployeeDao;
import com.wuwii.dao.JobDao;
import com.wuwii.entity.*;
import com.wuwii.form.EmployeeSearch;
import com.wuwii.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 10:08</pre>
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private JobDao jobDao;
    @PersistenceContext
    private EntityManager em;

    /**
     * 多条件动态分页查询，
     * <p>
     * 如果后期还需要加入其他的查询的条件，可以直接添加代码逻辑就好了。
     * <p>
     * 分割，需要注意的是 spring data 还提供了<code>Specification</code> 这个类直接提供了 eq | gt | equal 等等 Specification
     * 接口的方法，但是它的方法已经过时了，不推荐使用，如果需要使用记录下
     * 来自 网站 https://www.tianmaying.com/tutorial/spring-jpa-page-sort
     * <code>
     * public Page<Person> findAll(SearchRequest request) {
     * Specification<Person> specification = new Specifications<Person>()
     * .eq(StringUtils.isNotBlank(request.getName()), "name", request.getName())
     * .gt(Objects.nonNull(request.getAge()), "age", 18)
     * .between("birthday", new Range<>(new Date(), new Date()))
     * .like("nickName", "%og%", "%me")
     * .build();
     * return personRepository.findAll(specification, new PageRequest(0, 15));
     * }
     * </code>
     *
     * @param search   查询属性
     * @param pageable 分页和排序
     * @return 分页数据
     */
    @Override
    public Page<Employee> pageBySearch(EmployeeSearch search, Pageable pageable) {
        return employeeDao.findAll((Specification<Employee>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            Optional<EmployeeSearch> optional = Optional.ofNullable(search);
            // 根据 employee id 查询
            optional.map(EmployeeSearch::getEmployeeId).ifPresent(id -> {
                predicates.add(criteriaBuilder.equal(root.get(Employee_.id), id));
            });
            // 根据 employee detail name 模糊查询
            optional.map(EmployeeSearch::getEmployeeName).ifPresent(name -> {
                // 使用左联接，如果直接 get(Employee_.detail).get(EmployeeDetail_.name) 就是无条件内联，
                // 相当于 cross join，会产生 笛卡尔积
                Join<Employee, EmployeeDetail> join = root.join(Employee_.detail, JoinType.LEFT);
                predicates.add(criteriaBuilder.like(join.get(EmployeeDetail_.name),
                        "%" + name + "%"));
            });
            // 根据职位名查询
            optional.map(EmployeeSearch::getJobName).ifPresent(name -> {
                Join<Employee, Job> join = root.join(Employee_.job, JoinType.LEFT);
                predicates.add(criteriaBuilder.equal(join.get(Job_.name), name));
            });
            Predicate[] array = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(array));
        }, pageable);
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    /**
     * Search age gt or eq the parameter
     *
     * @param age
     * @return
     */
    @Override
    public List<Employee> listByAge(Integer age) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class); // 设置查询根，可以根据查询的类型设置不同的
        List<Predicate> predicates = new LinkedList<>();
        // 连表查询使用左连接
        Join<Employee, EmployeeDetail> join = root.join(Employee_.detail, JoinType.LEFT);
        predicates.add(cb.gt(join.get(EmployeeDetail_.age), age));
        predicates.add(cb.equal(join.get(EmployeeDetail_.age), age));
        // 设置排序规则
        Order order = cb.asc(root.get(Employee_.id));
        query.orderBy(order);
        query.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery typedQuery = em.createQuery(query); // TypedQuery执行查询与获取元模型实例
        return typedQuery.getResultList();
    }
}
