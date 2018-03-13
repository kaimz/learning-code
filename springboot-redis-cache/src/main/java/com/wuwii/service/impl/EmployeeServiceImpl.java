package com.wuwii.service.impl;

import com.wuwii.dao.EmployeeDao;
import com.wuwii.entity.Employee;
import com.wuwii.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 控制缓存的生命周期
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/13 10:40</pre>
 */
@Service
@CacheConfig(cacheNames = "em")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao dao;

    @Override
    @Cacheable(key = "#p0")
    public Employee findOne(Long id) {
        return dao.findOne(id);
    }

    /**
     * 更新缓存中的数据，
     * 由于 redis 是存在外部，不是 ehcache 那样存在于项目进程中，需要我们主动去更新 缓存
     * @param employee
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(key = "#p0.id")
    public Employee update(Employee employee) {
        return dao.save(employee);
    }

    /**
     * 同样主动去删除 cache
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "#p0")
    public void delete(Long id) {
        dao.delete(id);
    }
}
