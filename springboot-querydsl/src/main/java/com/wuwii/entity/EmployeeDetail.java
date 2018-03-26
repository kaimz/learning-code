package com.wuwii.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 10:00</pre>
 */
@Entity
@Data
public class EmployeeDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private Integer age;
}
