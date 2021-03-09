package com.misknet.tabseet.marketer.featuers.splashActivtiy.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidstudy.networkmanager.Tovuti;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.misknet.tabseet.marketer.Network.asp.models.PreferinceHelper;
import com.misknet.tabseet.marketer.Network.utiles.NetworkingParent;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.LoginActivity;
import com.misknet.tabseet.marketer.featuers.ParentOfNavigationActivity.view.ParentOfNavigationActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000;
    private static final String TAG = MainActivity.class.getSimpleName();
    PreferinceHelper preferinceHelper;
    RelativeLayout no_inter_net;
    FrameLayout splash;
    ImageView logo;
    Button btn_refresh;
    private Locale myLocale;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferinceHelper = new PreferinceHelper();
        splash = findViewById(R.id.splash);
        logo = findViewById(R.id.logo);
        btn_refresh = findViewById(R.id.btn_refresh);
        no_inter_net = findViewById(R.id.layout_no_internet);
        changeLang(PreferinceHelper.getAppLanguge());
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                       // String msg = getString(R.string.msg_token_fmt, token);

                        Log.d(TAG+"token", token);

                    }
                });
       NetworkingParent.isNetworkConnected();

       check();

        btn_refresh.setOnClickListener(v -> {
           check();

        });
    }

    public void check() {
        new Handler().postDelayed(() -> {
            if (NetworkingParent.myConnection) {

                if (preferinceHelper.getToken().equals("")) {
                    Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    /* Create an Intent that will start the Menu-Activity. */
//
                    startActivity(new Intent(MainActivity.this, ParentOfNavigationActivity.class));
                    finish();
                }
            } else {

                splash.setBackground(getResources().getDrawable(R.drawable.splash_logo, null));
                logo.setVisibility(View.GONE);
                no_inter_net.setVisibility(View.VISIBLE);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }


    @Override
    protected void onStop() {
        Tovuti.from(this).stop();
        super.onStop();
    }
}
