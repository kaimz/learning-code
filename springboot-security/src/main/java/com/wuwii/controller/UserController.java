package com.wuwii.controller;

import com.wuwii.entity.User;
import com.wuwii.service.UserService;
import com.wuwii.vo.UserAddDTO;
import com.wuwii.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasRole('USER')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasPermission()")
    public ResponseEntity<List<UserVO>> getAllUser() {
        List<User> users = userService.findAll();
        List<UserVO> userViews = userService.castUserVO(users);
        return ResponseEntity.ok(userViews);
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody UserAddDTO userAddDTO) {
        userService.insertUser(userAddDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
