package com.nullexcom.mvisamplekotlin.models;

public class Movie {
    private String entryTitle;
    private String originalTitle;
    private String url;
    private String imgUrl;
    private String status;
    private String date;

    public Movie(String entryTitle, String originalTitle, String url, String imgUrl, String status) {
        this.entryTitle = entryTitle;
        this.originalTitle = originalTitle;
        this.url = url;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
