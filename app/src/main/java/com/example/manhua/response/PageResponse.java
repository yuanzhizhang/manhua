package com.example.manhua.response;

import com.example.manhua.domain.PageItem;

import java.util.List;

public class PageResponse {
    private String msg;
    private Integer code;
    private List<PageItem> pages;

    public PageResponse(String msg, Integer code, List<PageItem> pages) {
        this.msg = msg;
        this.code = code;
        this.pages = pages;
    }

    public PageResponse() {
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

    public List<PageItem> getPages() {
        return pages;
    }

    public void setPages(List<PageItem> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "PageResponse{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", pages=" + pages +
                '}';
    }
}
