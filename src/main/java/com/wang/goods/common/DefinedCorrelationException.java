package com.wang.goods.common;

/**
 * 自定义异常
 */
public class DefinedCorrelationException extends RuntimeException{

    public DefinedCorrelationException(String msg) {
        super(msg);
    }
}
