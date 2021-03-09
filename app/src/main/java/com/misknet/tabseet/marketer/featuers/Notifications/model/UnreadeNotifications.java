package com.misknet.tabseet.marketer.featuers.Notifications.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnreadeNotifications {

@SerializedName("status")
@Expose
private boolean status;
@SerializedName("message")
@Expose
private String message;
@SerializedName("items")
@Expose
private NotifcationCounter number;
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

public NotifcationCounter getItems() {
return number;
}

public void setItems(NotifcationCounter items) {
this.number = items;
}

public int getCode() {
return code;
}

public void setCode(int code) {
this.code = code;
}

}