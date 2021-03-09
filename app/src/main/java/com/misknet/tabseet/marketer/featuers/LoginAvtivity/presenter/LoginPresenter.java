package com.misknet.tabseet.marketer.featuers.LoginAvtivity.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.asp.models.TabseetApp;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.custemEditText.DialogFragmentError;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.model.Items;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.ILoginActivity;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.LoginActivity;

public class LoginPresenter {
    LoginActivity loginActivity;
    ILoginActivity view;

    DialogFragmentError dialogFragmentError;

    public LoginPresenter(LoginActivity loginActivity, ILoginActivity view) {

        this.loginActivity = loginActivity;
        this.view = view;
    }

    public void login(String mobil, String pass, Button btn_login) {

        GenerlNetworking.getInstance().LoginMarketer(pass, mobil, new RequesListiner<Items>() {
            @Override
            public void onSuccess(Items data) {
                view.hideLoading();
                view.sendTokenandPass(data.getAccessToken(), data.getUser().isIsChangePassword());

            }

            @Override
            public void onFiler(String massage, int Code) {
                Log.e("myCodeErrore", "my Code Errore" + Code);

                if (Code == 103) {
                    dialogFragmentError = new DialogFragmentError();
                    dialogFragmentError.show(loginActivity.getSupportFragmentManager(), "");
                    btn_login.setEnabled(true);
                }
                view.onGetDataFail(massage);

                view.hideLoading();
            }
        });

    }


}
