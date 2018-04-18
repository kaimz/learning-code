package com.wuwii.entity;

import lombok.Data;

import javax.persistence.*;

/**
 *
 */
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

}
