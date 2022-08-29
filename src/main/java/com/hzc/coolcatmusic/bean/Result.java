package com.hzc.coolcatmusic.bean;

import lombok.Data;
@Data
public class Result<T> {
    private boolean status;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<T>();
        result.setStatus(true);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String message){
        Result<T> result = new Result<T>();
        result.setStatus(false);
        result.setMsg(message);
        return result;
    }
}
