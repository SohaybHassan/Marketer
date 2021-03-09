package com.misknet.tabseet.marketer.featuers.settingFragment.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.asp.models.PreferinceHelper;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.ParentOfNavigationActivity.view.ParentOfNavigationActivity;
import com.misknet.tabseet.marketer.featuers.settingFragment.model.Changelang;

import java.util.Locale;

public class Language extends AppCompatActivity {
    PreferinceHelper preferinceHelper;
    TextView tv_en_language, tv_ar_language;
    private Locale myLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        preferinceHelper = new PreferinceHelper();
        Toolbar toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        tv_en_language=findViewById(R.id.tv_en_language);
        tv_ar_language=findViewById(R.id.tv_ar_language);


        GenerlNetworking.getInstance().changLang(new RequesListiner<Changelang>() {
            @Override
            public void onSuccess(Changelang data) {

                tv_en_language.setOnClickListener(v -> {
                    changeLang("en");

                });
                tv_ar_language.setOnClickListener(v ->{
                    changeLang("ar");

                });
            }

            @Override
            public void onFiler(String massage, int Code) {

            }
        });




    }


    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
       PreferinceHelper.setAppLanguge(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Intent intent = new Intent(getApplicationContext(), ParentOfNavigationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
