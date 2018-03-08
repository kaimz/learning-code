package com.wuwii.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 10:18</pre>
 */
@StaticMetamodel(EmployeeDetail.class)
public class EmployeeDetail_ {
    public static volatile SingularAttribute<EmployeeDetail, Long> id;
    public static volatile SingularAttribute<EmployeeDetail, String> name;
    public static volatile SingularAttribute<EmployeeDetail, String> phone;
    public static volatile SingularAttribute<EmployeeDetail, Integer> age;
}
