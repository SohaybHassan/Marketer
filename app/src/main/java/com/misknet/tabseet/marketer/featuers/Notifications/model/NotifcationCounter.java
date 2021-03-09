package com.misknet.tabseet.marketer.featuers.Notifications.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifcationCounter {

    @SerializedName("count_unread")
    @Expose
    private int countUnread;

    public int getCountUnread() {
        return countUnread;
    }

    public void setCountUnread(int countUnread) {
        this.countUnread = countUnread;
    }
}
