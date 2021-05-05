package com.example.manhua.domain;

import java.util.List;

public class CartoonChapter {
    private Integer chatID;
    private Integer chatOrderID;
    private Integer cartoonID;
    private String chatTitle;
    private List<PageItem> chatContent;

    public CartoonChapter() {
    }

    public Integer getChatID() {
        return chatID;
    }

    public void setChatID(Integer chatID) {
        this.chatID = chatID;
    }

    public Integer getChatOrderID() {
        return chatOrderID;
    }

    public void setChatOrderID(Integer chatOrderID) {
        this.chatOrderID = chatOrderID;
    }

    public Integer getCartoonID() {
        return cartoonID;
    }

    public void setCartoonID(Integer cartoonID) {
        this.cartoonID = cartoonID;
    }

    public String getChatTitle() {
        return chatTitle;
    }

    public void setChatTitle(String chatTitle) {
        this.chatTitle = chatTitle;
    }

    public List<PageItem> getChatContent() {
        return chatContent;
    }

    public void setChatContent(List<PageItem> chatContent) {
        this.chatContent = chatContent;
    }
}
