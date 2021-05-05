package com.example.manhua.response;



import com.example.manhua.domain.CartoonBook;

import java.util.List;

public class CartoonBooksResponse {
    private String msg;
    private Integer code;
    private List<CartoonBook> cartoonBooks;

    @Override
    public String toString() {
        return "CartoonBookResponse{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", cartoonBooks=" + cartoonBooks +
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

    public List<CartoonBook> getCartoonBooks() {
        return cartoonBooks;
    }

    public void setCartoonBooks(List<CartoonBook> cartoonBooks) {
        this.cartoonBooks = cartoonBooks;
    }

    public CartoonBooksResponse() {
    }

    public CartoonBooksResponse(String msg, Integer code, List<CartoonBook> cartoonBooks) {
        this.msg = msg;
        this.code = code;
        this.cartoonBooks = cartoonBooks;
    }
}
