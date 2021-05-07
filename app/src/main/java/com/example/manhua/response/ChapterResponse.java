package com.example.manhua.response;


import com.example.manhua.domain.CartoonChapter;

import java.util.List;


public class ChapterResponse {
    private String msg;
    private Integer code;
    private List<CartoonChapter> cartoonChapters;

    public ChapterResponse() {
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

    public List<CartoonChapter> getChapters() {
        return cartoonChapters;
    }

    public void setChapters(List<CartoonChapter> chapters) {
        this.cartoonChapters = cartoonChapters;
    }

    @Override
    public String toString() {
        return "ChapterResponse{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", cartoonChapters=" + cartoonChapters +
                '}';
    }
}
