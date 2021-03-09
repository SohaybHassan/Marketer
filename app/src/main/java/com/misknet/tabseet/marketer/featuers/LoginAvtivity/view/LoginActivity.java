package com.misknet.tabseet.marketer.featuers.LoginAvtivity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.utiles.NetworkingParent;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.custemEditText.DialogFragmentError;
import com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.view.ChangePasswordActivity;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.presenter.LoginPresenter;
import com.misknet.tabseet.marketer.featuers.ParentOfNavigationActivity.view.ParentOfNavigationActivity;
import com.wang.avi.AVLoadingIndicatorView;


public class LoginActivity extends AppCompatActivity implements ILoginActivity {
    com.misknet.tabseet.marketer.custemEditText.EditTextThought ed_myPhone, ed_myPasswrod;
    Button btn_login, btn_refresh;
    ImageView image_login_Activity;
    TextView text_tabseet, tv_login;
    RelativeLayout internetConnection;

    String phone;
    String pass;
    LoginPresenter loginPresenter;
    ILoginActivity view;
    ProgressBar progresbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        view = this;
        loginPresenter = new LoginPresenter(this, view);
        btn_login = findViewById(R.id.btn_log_in);
        image_login_Activity = findViewById(R.id.image_login_Activity);
        text_tabseet = findViewById(R.id.text_tabseet);
        tv_login = findViewById(R.id.tv_login);
        btn_refresh = findViewById(R.id.btn_refresh);
        internetConnection = findViewById(R.id.layout_no_internet);

       // checkinternetConnection();


        btn_login.setOnClickListener(v -> {
            checkinternetConnection();
        });

        btn_refresh.setOnClickListener(v -> checkinternetConnection());


    }


    public void myIntent(String token, boolean changePassword) {
        if (changePassword) {
            Intent intent = new Intent(LoginActivity.this, ParentOfNavigationActivity.class);
            intent.putExtra("pass", pass);
            intent.putExtra("token", token);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(LoginActivity.this, ChangePasswordActivity.class);
            intent.putExtra("pass", pass);
            intent.putExtra("token", token);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onGetDataFail(String message) {
        Log.e("sohaib", message);
    }

    @Override
    public void hideLoading() {
        progresbar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progresbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void sendTokenandPass(String token, boolean ischangePass) {
        myIntent(token, ischangePass);
    }

    public void init() {
        ed_myPhone = findViewById(R.id.ed_your_phone);
        ed_myPasswrod = findViewById(R.id.ed_your_password);
        btn_login = findViewById(R.id.btn_log_in);
        progresbar = findViewById(R.id.avi);
    }


    public void checkinternetConnection() {

        if (NetworkingParent.myConnection) {

            phone = ed_myPhone.getmyText().toString();
            pass = ed_myPasswrod.getmyText().toString();

            if (!phone.isEmpty() && !pass.isEmpty()) {
                showLoading();

                btn_login.setEnabled(false);

                btn_login.setVisibility(View.VISIBLE);
                image_login_Activity.setVisibility(View.VISIBLE);
                ed_myPhone.setVisibility(View.VISIBLE);
                ed_myPasswrod.setVisibility(View.VISIBLE);
                text_tabseet.setVisibility(View.VISIBLE);
                tv_login.setVisibility(View.VISIBLE);
                internetConnection.setVisibility(View.GONE);
                loginPresenter.login(phone, pass,btn_login);



            } else {

                hideLoading();
            }
        } else {
            btn_login.setVisibility(View.GONE);
            image_login_Activity.setVisibility(View.GONE);
            ed_myPhone.setVisibility(View.GONE);
            ed_myPasswrod.setVisibility(View.GONE);
            text_tabseet.setVisibility(View.GONE);
            tv_login.setVisibility(View.GONE);
            internetConnection.setVisibility(View.VISIBLE);


        }
    }


}
