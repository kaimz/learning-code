package com.wuwii.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 *
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @OneToMany(targetEntity = UserRole.class, mappedBy = "userId", fetch = FetchType.EAGER) // mappedBy 只有在双向关联的时候设置，表示关系维护的一端，否则会生成中间表A_B
    @org.hibernate.annotations.ForeignKey(name = "none") // 注意这里不能使用 @JoinColumn 不然会生成外键
    private Set<UserRole> userRoles;
}
