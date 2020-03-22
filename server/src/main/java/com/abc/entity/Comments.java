package com.abc.entity;

import java.util.Date;

public class Comments {
    private String id;

    private String oid;

    private String uid;

    private Date created;

    private Integer isDeleted;

    private String detail;

    private Integer rate;

    private String nickname;

    private String coverUrl;

    private String goodsIds;

    public Comments(String id, String oid, String uid, Date created, Integer isDeleted, String detail, Integer rate, String nickname, String coverUrl, String goodsIds) {
        this.id = id;
        this.oid = oid;
        this.uid = uid;
        this.created = created;
        this.isDeleted = isDeleted;
        this.detail = detail;
        this.rate = rate;
        this.nickname = nickname;
        this.coverUrl = coverUrl;
        this.goodsIds = goodsIds;
    }

    public Comments() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds == null ? null : goodsIds.trim();
    }
}