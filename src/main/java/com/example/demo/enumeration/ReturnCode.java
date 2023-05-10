package com.example.demo.enumeration;

/**
 * 返回码定义
 */
public enum ReturnCode {

    SUCCESS(200, "操作成功"),
    EMPTY(201, "资源为空"),
    TOKEN_EXPIRE(401, "token 过期"),
    TOKEN_IS_NULL(401, "token 不能为空"),
    REQUEST_METHOD_ERROR(403, "当前接口不支持此请求方式!"),
    SERVER_ERROR(500, "请求无法处理,请稍后再试！"),
    FAIL(600, "操作失败"),
    PARAMETER_ERROR(601, "参数异常"),
    DATA_NOT_NULL(602, "参数不能为空"),
    ;

    private Integer code;
    private String msg;

    ReturnCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
