package com.misknet.tabseet.marketer.featuers.HomeFragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyNewPaymentVisits {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("items")
    @Expose
    private List<DataToMvisits> items = null;
    @SerializedName("code")
    @Expose
    private int code;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataToMvisits> getItems() {
        return items;
    }

    public void setItems(List<DataToMvisits> items) {
        this.items = items;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}