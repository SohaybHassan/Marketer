package com.misknet.tabseet.marketer.featuers.settingFragment.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.asp.models.TabseetApp;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.custemEditText.ContactUsDialog;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.LoginActivity;
import com.misknet.tabseet.marketer.featuers.settingFragment.model.ContactUsReslet;

public class ContactUsAvtivity extends AppCompatActivity {
    TextView tv_send_text, tv_call_us;
    ContactUsDialog contactUsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_avtivity);

        Toolbar toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        contactUsDialog = new ContactUsDialog(massag -> {

            Log.e("ContactUsDialogss-1", massag);
            GenerlNetworking.getInstance().contactus(massag, new RequesListiner<ContactUsReslet>() {
                @Override
                public void onSuccess(ContactUsReslet data) {
                    Log.e("onSuccess", massag);
                }

                @Override
                public void onFiler(String massage, int Code) {

                    Toast.makeText(ContactUsAvtivity.this, "your not Authentecated", Toast.LENGTH_SHORT).show();

                    Log.e("onFiler", massag);


                }
            });
        });
        tv_send_text = findViewById(R.id.tv_en_language);
        tv_call_us = findViewById(R.id.tv_call_us);

        tv_send_text.setOnClickListener(v -> contactUsDialog.show(getSupportFragmentManager(), "my Text"));

        tv_call_us.setOnClickListener(v -> {
            String uri = "tel:" + "0597727313";
            Intent intentCall = new Intent(Intent.ACTION_DIAL);
            intentCall.setData(Uri.parse(uri));
            startActivity(intentCall);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
