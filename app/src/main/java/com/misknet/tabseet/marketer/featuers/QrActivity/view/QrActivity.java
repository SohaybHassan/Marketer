package com.misknet.tabseet.marketer.featuers.QrActivity.view;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.nfc.tech.NfcBarcode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.custemEditText.FireMissilesDialogFragment;

import java.util.Objects;

import me.ydcool.lib.qrmodule.encoding.QrGenerator;

public class QrActivity extends AppCompatActivity {
    ImageView imageQeCode;
    FireMissilesDialogFragment fireMissilesDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Toolbar toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        fireMissilesDialogFragment = new FireMissilesDialogFragment();
        // fireMissilesDialogFragment.show(getSupportFragmentManager(),"test");
        Intent intent = getIntent();
        String qr = intent.getStringExtra("qr");
        drowBarcode(qr);


    }

    public void drowBarcode(String qr) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            NfcBarcode preferances;
            Bitmap bitmap = barcodeEncoder.encodeBitmap(qr, BarcodeFormat.QR_CODE, 550, 550);
            ImageView imageQeCode = (ImageView) findViewById(R.id.im_qr_code);
            imageQeCode.setImageBitmap(bitmap);


        } catch (Exception e) {

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
