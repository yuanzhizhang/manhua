package com.example.manhua.response;


public class LoginResponse {
    private String msg;
    private Integer code;
    private String res;

    public LoginResponse(String msg, Integer code, String res) {
        this.msg = msg;
        this.code = code;
        this.res = res;
    }

    public LoginResponse() {
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", res='" + res + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
