package com.brijframework.integration.model.pixel;

public class Photo {
    private Integer id;
    private Integer width;
    private Integer height;
    private String url;
    private String photographer;
    private String photographerUrl;
    private Integer photographerId;
    private String avgColor;
    private Src src;
    private Boolean liked;
    private String alt;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getWidth() {
        return width;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPhotographer() {
        return photographer;
    }
    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }
    public String getPhotographerUrl() {
        return photographerUrl;
    }
    public void setPhotographerUrl(String photographerUrl) {
        this.photographerUrl = photographerUrl;
    }
    public Integer getPhotographerId() {
        return photographerId;
    }
    public void setPhotographerId(Integer photographerId) {
        this.photographerId = photographerId;
    }
    public String getAvgColor() {
        return avgColor;
    }
    public void setAvgColor(String avgColor) {
        this.avgColor = avgColor;
    }
    public Src getSrc() {
        return src;
    }
    public void setSrc(Src src) {
        this.src = src;
    }
    public Boolean getLiked() {
        return liked;
    }
    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
    public String getAlt() {
        return alt;
    }
    public void setAlt(String alt) {
        this.alt = alt;
    }
}
