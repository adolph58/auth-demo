package com.example.demo.util;

import com.example.demo.enumeration.ReturnCode;

/**
 * http请求返回的最外层对象
 */
public class Result<T> {

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 具体的内容.
     */
    private T data;

    public Result() {
    }

    public Result(Integer code, String msg, Long timestamp, T data) {
        this.code = code;
        this.msg = msg;
        this.timestamp = timestamp;
        this.data = data;
    }

    public static Result success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), System.currentTimeMillis(), data);
    }


    public static Result fail() {
        return fail(null);
    }

    public static Result fail(ReturnCode returnCode) {
        if (null == returnCode) {
            return fail(ReturnCode.FAIL.getCode(), ReturnCode.FAIL.getMsg(), null);
        }
        return fail(returnCode.getCode(), returnCode.getMsg(), null);
    }

    public static <T> Result<T> fail(T data) {
        return fail(ReturnCode.FAIL.getCode(), ReturnCode.FAIL.getMsg(), data);
    }

    public static <T> Result<T> fail(String msg, T data) {
        return fail(ReturnCode.FAIL.getCode(), msg, data);
    }

    public static Result fail(Integer code, String msg) {
        return fail(code, msg, null);
    }

    public static <T> Result<T> fail(Integer code, String msg, T data) {
        return new Result(code, msg, System.currentTimeMillis(), data);
    }

    public static Result error() {
        return error(null);
    }

    public static <T> Result<T> error(T data) {
        return error(ReturnCode.SERVER_ERROR.getCode(), ReturnCode.SERVER_ERROR.getMsg(), data);
    }

    public static <T> Result<T> error(String msg, T data) {
        return error(ReturnCode.SERVER_ERROR.getCode(), msg, data);
    }

    public static Result error(Integer code, String msg) {
        return error(code, msg, null);
    }

    public static <T> Result<T> error(Integer code, String msg, T data) {
        return new Result(code, msg, System.currentTimeMillis(), data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
