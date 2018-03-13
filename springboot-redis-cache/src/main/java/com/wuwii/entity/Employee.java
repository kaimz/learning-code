package com.wuwii.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/13 10:36</pre>
 */
@Entity
@Data
@DynamicUpdate
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
}
