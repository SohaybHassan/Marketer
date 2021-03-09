package com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.model.ChangePasswordResult;
import com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.view.ChangePasswordActivity;
import com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.view.IChangePassword;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.LoginActivity;


public class ChangePasswordPrsenter {
    ChangePasswordActivity changePasswordActivity;
    IChangePassword view;

    public ChangePasswordPrsenter(ChangePasswordActivity changePasswordActivity, IChangePassword view) {
        this.changePasswordActivity = changePasswordActivity;
        this.view = view;
    }

    public void getDate(String oldPass, String newPass) {
        GenerlNetworking.getInstance().changePass(oldPass, newPass, new RequesListiner<ChangePasswordResult>() {
            @Override
            public void onSuccess(ChangePasswordResult data) {
                view.homeFragment();
                view.hideLoading();
            }

            @Override
            public void onFiler(String massage, int code) {

                if (code==401){
                    Intent intent401= new Intent(changePasswordActivity, LoginActivity.class);
                    changePasswordActivity.startActivity(intent401);
                    changePasswordActivity.finish();
                    view.onGetDataFail(massage);
                    view.hideLoading();

                }


                view.onGetDataFail(massage);
                view.hideLoading();

            }
        });

    }
}
