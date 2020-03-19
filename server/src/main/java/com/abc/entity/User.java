package com.abc.entity;

import java.util.Date;

public class User {
    private String id;

    private String nickName;

    private Date created;

    private Integer isDeleted;

    private String openid;

    private Integer gender;

    private String phoneNumber;

    private String city;

    private String avatarUrl;

    public User(String id, String nickName, Date created, Integer isDeleted, String openid, Integer gender, String phoneNumber, String city, String avatarUrl) {
        this.id = id;
        this.nickName = nickName;
        this.created = created;
        this.isDeleted = isDeleted;
        this.openid = openid;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.avatarUrl = avatarUrl;
    }

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
}