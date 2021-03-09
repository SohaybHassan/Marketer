package com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.view;

public interface IChangePassword {


    void onGetDataSuccee(String oldPass, String newPass);

    void onGetDataFail(String message);

    void hideLoading();

    void showLoading();

    void homeFragment();
}
