package com.misknet.tabseet.marketer.featuers.HomeFragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("email_verified_at")
    @Expose
    private Object emailVerifiedAt;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("nationalid")
    @Expose
    private Object nationalid;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("city_id")
    @Expose
    private Object cityId;
    @SerializedName("neighborhood_id")
    @Expose
    private Object neighborhoodId;
    @SerializedName("department_id")
    @Expose
    private Object departmentId;
    @SerializedName("supervisor_id")
    @Expose
    private Object supervisorId;
    @SerializedName("user_type_id")
    @Expose
    private String userTypeId;
    @SerializedName("last_time_login")
    @Expose
    private String lastTimeLogin;
    @SerializedName("try_send")
    @Expose
    private String trySend;
    @SerializedName("sms_verify")
    @Expose
    private String smsVerify;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("is_change_password")
    @Expose
    private boolean isChangePassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Object getNationalid() {
        return nationalid;
    }

    public void setNationalid(Object nationalid) {
        this.nationalid = nationalid;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getCityId() {
        return cityId;
    }

    public void setCityId(Object cityId) {
        this.cityId = cityId;
    }

    public Object getNeighborhoodId() {
        return neighborhoodId;
    }

    public void setNeighborhoodId(Object neighborhoodId) {
        this.neighborhoodId = neighborhoodId;
    }

    public Object getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Object departmentId) {
        this.departmentId = departmentId;
    }

    public Object getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Object supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getLastTimeLogin() {
        return lastTimeLogin;
    }

    public void setLastTimeLogin(String lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public String getTrySend() {
        return trySend;
    }

    public void setTrySend(String trySend) {
        this.trySend = trySend;
    }

    public String getSmsVerify() {
        return smsVerify;
    }

    public void setSmsVerify(String smsVerify) {
        this.smsVerify = smsVerify;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isIsChangePassword() {
        return isChangePassword;
    }

    public void setIsChangePassword(boolean isChangePassword) {
        this.isChangePassword = isChangePassword;
    }

}