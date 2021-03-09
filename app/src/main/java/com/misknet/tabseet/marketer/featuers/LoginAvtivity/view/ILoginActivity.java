package com.misknet.tabseet.marketer.featuers.LoginAvtivity.view;

public interface ILoginActivity {


    void onGetDataFail(String message);

    void hideLoading();

    void showLoading();

    void sendTokenandPass(String token,boolean ischangepass);
}
