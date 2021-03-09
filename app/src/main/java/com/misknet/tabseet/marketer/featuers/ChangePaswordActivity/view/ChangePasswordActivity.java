package com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.utiles.NetworkingParent;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.model.ChangePasswordResult;
import com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.presenter.ChangePasswordPrsenter;
import com.misknet.tabseet.marketer.featuers.ParentOfNavigationActivity.view.ParentOfNavigationActivity;
import com.wang.avi.AVLoadingIndicatorView;

public class ChangePasswordActivity extends AppCompatActivity implements IChangePassword {

    com.misknet.tabseet.marketer.custemEditText.EditTextThought my_old_pass, my_mew_pass, my_confirm_pass;
    TextView tv_top, tv_dvice;
    String oldPass, newPass, ConfirmPass;
    Button btn_save_new_pass, btn_refresh;
    RelativeLayout internetConnection;
    ProgressBar progres;
    ChangePasswordPrsenter changePasswordPrsenter;
    IChangePassword view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        tv_top = findViewById(R.id.tv_top);
        btn_save_new_pass = findViewById(R.id.btn_save_new_pass);
        tv_dvice = findViewById(R.id.tv_dvice);
        my_old_pass = findViewById(R.id.ed_your_old_password);
        my_mew_pass = findViewById(R.id.ed_your_new_password);
        internetConnection = findViewById(R.id.layout_no_internet);
        my_confirm_pass = findViewById(R.id.ed_your_confirm_new_password);
        btn_refresh = findViewById(R.id.btn_refresh);
        progres = findViewById(R.id.avi);

        view = this;
        changePasswordPrsenter = new ChangePasswordPrsenter(this, view);

        internetConnection();
        btn_save_new_pass.setOnClickListener(v -> {
            internetConnection();
        });
        btn_refresh.setOnClickListener(v -> {
            internetConnection();
        });

    }

    public void svae(View view) {

    }


    @Override
    public void onGetDataSuccee(String oldPass, String newPass) {

    }

    @Override
    public void onGetDataFail(String message) {
        Log.e("TAGTAG4", message);
    }

    @Override
    public void hideLoading() {
        progres.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progres.setVisibility(View.VISIBLE);
    }

    @Override
    public void homeFragment() {
        startActivity(new Intent(ChangePasswordActivity.this, ParentOfNavigationActivity.class));
    }


    public void internetConnection() {
        if (NetworkingParent.myConnection) {

            oldPass = my_old_pass.getmyText().toString();
            newPass = my_mew_pass.getmyText().toString();
            ConfirmPass = my_confirm_pass.getmyText().toString();

            showLoading();

            if (!oldPass.isEmpty() && !newPass.isEmpty() && !ConfirmPass.isEmpty()) {
                if (newPass.equals(ConfirmPass)) {
                    btn_save_new_pass.setVisibility(View.VISIBLE);
                    my_old_pass.setVisibility(View.VISIBLE);
                    my_mew_pass.setVisibility(View.VISIBLE);
                    my_confirm_pass.setVisibility(View.VISIBLE);
                    tv_top.setVisibility(View.VISIBLE);
                    tv_dvice.setVisibility(View.VISIBLE);
                    internetConnection.setVisibility(View.GONE);

                    changePasswordPrsenter.getDate(oldPass, newPass);
                } else {
                    hideLoading();
                }
            } else {
                hideLoading();
            }

        } else {
            btn_save_new_pass.setVisibility(View.GONE);
            my_old_pass.setVisibility(View.GONE);
            my_mew_pass.setVisibility(View.GONE);
            my_confirm_pass.setVisibility(View.GONE);
            tv_top.setVisibility(View.GONE);
            tv_dvice.setVisibility(View.GONE);
            internetConnection.setVisibility(View.VISIBLE);
        }


    }
}
