package com.example.manhua.domain;

public class PageItem {
    private String imageUrl;
    private Integer page;

    public PageItem(String imageUrl, Integer page) {
        this.imageUrl = imageUrl;
        this.page = page;
    }

    public PageItem() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
