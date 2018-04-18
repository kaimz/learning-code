package com.wuwii.entity;

import lombok.Data;

import javax.persistence.*;

/**
 *
 */
@Entity
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private Long userId;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "roleId", nullable = false, foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Role role;
}
