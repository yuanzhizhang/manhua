package com.example.manhua.domain;

import java.io.Serializable;
import java.util.List;

public class CartoonBook implements Serializable {
    private Integer cId;
    private String cName;
    private String cartoonLastChat;
    private String cartoonUpdateTime;
    private String cartoonCoverUrl;
    private String cartoonType;
    private String introduction;


    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCartoonType() {
        return cartoonType;
    }

    public void setCartoonType(String cartoonType) {
        this.cartoonType = cartoonType;
    }

    private List<CartoonChapter> bookContent;

    public List<CartoonChapter> getBookContent() {
        return bookContent;
    }

    public void setBookContent(List<CartoonChapter> bookContent) {
        this.bookContent = bookContent;
    }

    public CartoonBook() {
    }

    public CartoonBook(Integer cId, String cName, String cartoonLastChat, String cartoonUpdateTime, String cartoonCoverUrl) {
        this.cId = cId;
        this.cName = cName;
        this.cartoonLastChat = cartoonLastChat;
        this.cartoonUpdateTime = cartoonUpdateTime;
        this.cartoonCoverUrl = cartoonCoverUrl;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCartoonLastChat() {
        return cartoonLastChat;
    }

    public void setCartoonLastChat(String cartoonLastChat) {
        this.cartoonLastChat = cartoonLastChat;
    }

    public String getCartoonUpdateTime() {
        return cartoonUpdateTime;
    }

    public void setCartoonUpdateTime(String cartoonUpdateTime) {
        this.cartoonUpdateTime = cartoonUpdateTime;
    }

    public String getCartoonCoverUrl() {
        return cartoonCoverUrl;
    }

    public void setCartoonCoverUrl(String cartoonCoverUrl) {
        this.cartoonCoverUrl = cartoonCoverUrl;
    }

    @Override
    public String toString() {
        return "CartoonBook{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cartoonLastChat='" + cartoonLastChat + '\'' +
                ", cartoonUpdateTime='" + cartoonUpdateTime + '\'' +
                ", cartoonCoverUrl='" + cartoonCoverUrl + '\'' +
                ", cartoonType='" + cartoonType + '\'' +
                ", introduction='" + introduction + '\'' +
                ", bookContent=" + bookContent +
                '}';
    }
}
