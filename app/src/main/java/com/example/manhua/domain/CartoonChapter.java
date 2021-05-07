package com.example.manhua.domain;

import java.io.Serializable;
import java.util.List;

public class CartoonChapter implements Serializable {
    private Integer chapterId;
    private Integer chapterOrderId;
    private Integer cartoonId;
    private String chapterTitle;
    private List<PageItem> chatContent;

    public CartoonChapter() {
    }

    public CartoonChapter(Integer chapterId, Integer chapterOrderId, Integer cartoonId, String chapterTitle, List<PageItem> chatContent) {
        this.chapterId = chapterId;
        this.chapterOrderId = chapterOrderId;
        this.cartoonId = cartoonId;
        this.chapterTitle = chapterTitle;
        this.chatContent = chatContent;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getChapterOrderId() {
        return chapterOrderId;
    }

    public void setChapterOrderId(Integer chapterOrderID) {
        this.chapterOrderId = chapterOrderID;
    }

    public Integer getCartoonId() {
        return cartoonId;
    }

    public void setCartoonId(Integer cartoonId) {
        this.cartoonId = cartoonId;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public List<PageItem> getChatContent() {
        return chatContent;
    }

    public void setChatContent(List<PageItem> chatContent) {
        this.chatContent = chatContent;
    }

    @Override
    public String toString() {
        return "CartoonChapter{" +
                "chapterId=" + chapterId +
                ", chapterOrderId=" + chapterOrderId +
                ", cartoonId=" + cartoonId +
                ", chapterTitle='" + chapterTitle + '\'' +
                ", chatContent=" + chatContent +
                '}';
    }
}
