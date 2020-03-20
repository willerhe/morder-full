package com.abc.entity;

import java.util.Date;

public class Order {
    private String id;

    private Date created;

    private String title;

    private String detail;

    private Integer isDeleted;

    private String number;

    private Integer commentCount;

    private Float total;

    private Object meta;

    private String coverUrl;

    private String uid;

    private String remark;

    private Integer queue;

    public Order(String id, Date created, String title, String detail, Integer isDeleted, String number, Integer commentCount, Float total, Object meta, String coverUrl, String uid, String remark, Integer queue) {
        this.id = id;
        this.created = created;
        this.title = title;
        this.detail = detail;
        this.isDeleted = isDeleted;
        this.number = number;
        this.commentCount = commentCount;
        this.total = total;
        this.meta = meta;
        this.coverUrl = coverUrl;
        this.uid = uid;
        this.remark = remark;
        this.queue = queue;
    }

    public Order() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getQueue() {
        return queue;
    }

    public void setQueue(Integer queue) {
        this.queue = queue;
    }
}