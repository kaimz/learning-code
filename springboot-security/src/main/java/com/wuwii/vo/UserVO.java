package com.wuwii.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class UserVO implements Serializable {
    private Long id;
    private String username;
}
