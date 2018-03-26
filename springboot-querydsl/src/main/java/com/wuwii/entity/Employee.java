package com.wuwii.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 9:52</pre>
 */
@Entity
@Data
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    // 拥有级联维护的一方，参考http://westerly-lzh.github.io/cn/2014/12/JPA-CascadeType-Explaining/
    @JoinColumn(foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private EmployeeDetail detail;

    @ManyToOne(fetch = FetchType.LAZY) // 默认 lazy ，通过懒加载，知道需要使用级联的数据，才去数据库查询这个数据，提高查询效率。
    // 设置外键的问题，参考http://mario1412.github.io/2016/06/27/JPA%E4%B8%AD%E5%B1%8F%E8%94%BD%E5%AE%9E%E4%BD%93%E9%97%B4%E5%A4%96%E9%94%AE/
    @JoinColumn(name = "jobId", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Job job;
}
