package com.abc.entity;

import java.util.Date;

public class Goods {
    private String id;

    private String title;

    private String detail;

    private Float price;

    private String coverUrl;

    private Date created;

    private String tag;

    private Integer isDeleted;

    private String type;

    public Goods(String id, String title, String detail, Float price, String coverUrl, Date created, String tag, Integer isDeleted, String type) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.coverUrl = coverUrl;
        this.created = created;
        this.tag = tag;
        this.isDeleted = isDeleted;
        this.type = type;
    }

    public Goods() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}