package com.wuwii.excetion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

/**
 * 表示 注解 RestController 的异常统一处理
 * <p>
 * 首先处理已知异常，
 *
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/2 23:33</pre>
 */
@RestControllerAdvice
public class ExceptionHandler {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 处理系统自定义的异常
     *
     * @param e 异常
     * @return 状态码和错误信息
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(KCException.class)
    public ResponseEntity<String> handleKCException(KCException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(e.getCode()).body(e.getMessage());
    }

    /**
     * 参数检验违反约束（数据校验）
     *
     * @param e BindException
     * @return error message
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleConstraintViolationException(BindException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(BAD_REQUEST).body(
                e.getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(",")));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(CONFLICT).body("数据库中已存在该记录");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleException(AccessDeniedException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(UNAUTHORIZED).body("不允许访问");
    }


    /**
     * 处理异常
     *
     * @param e 异常
     * @return 状态码
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
    }
}
