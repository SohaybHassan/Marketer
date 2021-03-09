package com.misknet.tabseet.marketer.Network.utiles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Contract;

public class AppResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("items")
    @Expose
    private Object items;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("page_count")
    @Expose
    private int pageCount;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
