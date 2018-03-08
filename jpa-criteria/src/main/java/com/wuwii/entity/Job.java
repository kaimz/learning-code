package com.wuwii.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 9:54</pre>
 */
@Entity
@Data
public class Job implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Employee.class, mappedBy = "job") // mappedBy 只有在双向关联的时候设置，表示关系维护的一端，否则会生成中间表A_B
    @org.hibernate.annotations.ForeignKey(name = "none") // 注意这里不能使用 @JoinColumn 不然会生成外键
    private Set<Employee> employees;
}
