package com.wang.goods.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public CommonResult<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){

        if(ex.getMessage().contains("Duplicate entry")){
            String[] str = ex.getMessage().split(" ");
            String msg = str[2]+"已存在";
            return CommonResult.error(msg);
        }
        return CommonResult.error("未知错误");
    }

    @ExceptionHandler(DefinedCorrelationException.class)
    public CommonResult<String> exceptionHandler(DefinedCorrelationException ex){
        return CommonResult.error(ex.getMessage());
    }
}