package com.wuwii.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 不知道什么原因
 * 这个持久性单元模型需要与实体类相同的包中，否则相关的值不会注入到 spring 容器中
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 10:16</pre>
 */
@StaticMetamodel(Employee.class)
public class Employee_ {
    public static volatile SingularAttribute<Employee, Long> id;
    public static volatile SingularAttribute<Employee, EmployeeDetail> detail;
    public static volatile SingularAttribute<Employee, Job> job;
}
