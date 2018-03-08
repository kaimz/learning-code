package com.wuwii.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 10:21</pre>
 */
@StaticMetamodel(Job.class)
public class Job_ {
    public static volatile SingularAttribute<Job, Long> id;
    public static volatile SingularAttribute<Job, String> name;
    public static volatile SetAttribute<Job, Employee> employees;
}
