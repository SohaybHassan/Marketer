package com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePassword {

@SerializedName("old_password")
@Expose
private String oldPassword;
@SerializedName("new_password")
@Expose
private String newPassword;

public String getOldPassword() {
return oldPassword;
}

public void setOldPassword(String oldPassword) {
this.oldPassword = oldPassword;
}

public String getNewPassword() {
return newPassword;
}

public void setNewPassword(String newPassword) {
this.newPassword = newPassword;
}

}