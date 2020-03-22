package com.abc.entity;

import java.util.Date;

public class Orders {
    private String id;

    private Date created;

    private String title;

    private String detail;

    private Integer isDeleted;

    private String number;

    private Integer commentCount;

    private Float total;

    private String coverUrl;

    private String uid;

    private String remark;

    private Integer queue;

    private String queueType;

    private String status;

    private Integer code;

    private String meta;

    public Orders(String id, Date created, String title, String detail, Integer isDeleted, String number, Integer commentCount, Float total, String coverUrl, String uid, String remark, Integer queue, String queueType, String status, Integer code, String meta) {
        this.id = id;
        this.created = created;
        this.title = title;
        this.detail = detail;
        this.isDeleted = isDeleted;
        this.number = number;
        this.commentCount = commentCount;
        this.total = total;
        this.coverUrl = coverUrl;
        this.uid = uid;
        this.remark = remark;
        this.queue = queue;
        this.queueType = queueType;
        this.status = status;
        this.code = code;
        this.meta = meta;
    }

    public Orders() {
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

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType == null ? null : queueType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta == null ? null : meta.trim();
    }
}