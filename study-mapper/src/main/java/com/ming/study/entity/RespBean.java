package com.ming.study.entity;

/**
 * 响应消息类
 *
 * @author: ziming
 * @Time: 2022/8/24 23:12
 */
public class RespBean {

    /**
     * 成功:200
     * 失败:500
     * 账号密码错误:501
     * 未登录:502
     * 权限不足:503
     */
    private Integer code;
    private String message; // 消息
    private Object data;    // 返回的数据

    private RespBean(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RespBean(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    private RespBean(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static RespBean ok(String message) {
        return new RespBean(200, message);
    }

    public static RespBean ok(Object data) {
        return new RespBean(200, data);
    }

    public static RespBean ok(String message, Object data) {
        return new RespBean(200, message, data);
    }

    public static RespBean loginFail(String message) {
        return new RespBean(501, message);
    }

    public static RespBean notLogin(String message) {
        return new RespBean(502, message);
    }

    public static RespBean denied(String message) {
        return new RespBean(503, message);
    }

    public static RespBean error(String message) {
        return new RespBean(500, message);
    }

    public static RespBean error(String message, Object data) {
        return new RespBean(500, message, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
