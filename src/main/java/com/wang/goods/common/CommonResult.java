package com.wang.goods.common;

import lombok.Data;

@Data
public class CommonResult<T> {

    private int code ;  // 0 or 1
    private String msg;  //错误时，传回提示消息
    private T data; //成功时，传回数据

    public static <T> CommonResult<T> success(T object){

        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(1);
        commonResult.setData(object);
        return commonResult;
    }

    public static <T> CommonResult<T> error(String msg){

        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(0);
        commonResult.setMsg(msg);
        return commonResult;
    }
}
