package com.misknet.tabseet.marketer.featuers.RecoedFragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyOldVisits {

@SerializedName("status")
@Expose
private boolean status;
@SerializedName("message")
@Expose
private String message;
@SerializedName("items")
@Expose
private List<MyoldItemData> items = null;
@SerializedName("code")
@Expose
private int code;
@SerializedName("page")
@Expose
private int page;
@SerializedName("page_count")
@Expose
private int pageCount;

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

public List<MyoldItemData> getItems() {
return items;
}

public void setItems(List<MyoldItemData> items) {
this.items = items;
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