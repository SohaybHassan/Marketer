package com.misknet.tabseet.marketer.featuers.ResidentialPaymentConfirmation.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.GoogleMap.view.MapsActivity;
import com.misknet.tabseet.marketer.featuers.QrActivity.view.QrActivity;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.view.RecordFragment;

public class ResidentialPaymentConfirmationActivity extends AppCompatActivity {
    TextView tv_number, tv_date, tv_your_cite, tv_your_entry_fee,tv_your_additional_details_of_the_site_old, tv_your_your_cite, tv_your_additional_details_of_the_site, tv_your_the_group;
    TextView tv_your_Neighborhood;
    ImageView my_location, my_phone;
    String qr;
    Button btn_confarm;
    String mobile;
    public static final int viewDatele = 1;
    public static final int activDeteles = 1;
    public final int REQUESTPERMASSIN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residential_payment_confirmation);
        find();


        Toolbar toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        getData();

    }

    public void btnConfarm(View view) {
        Intent intent = new Intent(this, QrActivity.class);
        intent.putExtra("qr", qr);
        startActivity(intent);
    }

    public void find() {
        tv_your_Neighborhood = findViewById(R.id.tv_your_Neighborhood);
        tv_number = findViewById(R.id.tv_number_old);
        tv_date = findViewById(R.id.date_old);
        tv_your_cite = findViewById(R.id.tv_your_cite_old);
        tv_your_entry_fee = findViewById(R.id.tv_your_entry_fee_old);
        tv_your_your_cite = findViewById(R.id.tv_your_type_cite_old);
        my_location = findViewById(R.id.my_location);
        my_phone = findViewById(R.id.my_phone);
        btn_confarm = findViewById(R.id.btn_confarm);
        tv_your_the_group = findViewById(R.id.tv_your_the_group);
        tv_your_additional_details_of_the_site = findViewById(R.id.tv_your_additional_details_of_the_site_old);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTPERMASSIN) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getData() {
        Intent intent = getIntent();
        if (intent.hasExtra("flageView")) {
            my_location.setVisibility(View.GONE);
            my_phone.setVisibility(View.GONE);
            btn_confarm.setVisibility(View.GONE);
        }
        String cityname = intent.getStringExtra("cityName");
        int id = intent.getIntExtra("id", -1);
        String date = intent.getStringExtra("date");
        String buildingCatName = intent.getStringExtra("buildingCatName");
        String addressDetails = intent.getStringExtra("addressDetails");
        String latitude = intent.getStringExtra("latitude");
        String longitude = intent.getStringExtra("longitude");
        String groupid = intent.getStringExtra("groupid");
        mobile = intent.getStringExtra("mobile");
        String neighborhoodName = intent.getStringExtra("neighborhoodName");
        qr = intent.getStringExtra("Qr_code");
        int entrFee = intent.getIntExtra("amount", -1);


        tv_number.setText( getString(R.string.number_of_vistor)+ id);
        tv_date.setText(date);
        tv_your_cite.setText(cityname);
        tv_your_the_group.setText(groupid);
        tv_your_entry_fee.setText(entrFee + getString(R.string.r_s));
        tv_your_your_cite.setText(buildingCatName);
        tv_your_Neighborhood.setText(neighborhoodName);
        tv_your_additional_details_of_the_site.setText(addressDetails);
        my_location.setOnClickListener(v -> {
            Intent intentMap = new Intent(ResidentialPaymentConfirmationActivity.this, MapsActivity.class);
            intentMap.putExtra("latit", latitude);
            intentMap.putExtra("longit", longitude);  //onRequestPermissionsResult()
            intentMap.putExtra("cityNamehi", cityname);
            intentMap.putExtra("neighborhoodNamehi", neighborhoodName);
            startActivity(intentMap);


        });
        my_phone.setOnClickListener(v -> {
            String uri = "tel:" + mobile;

            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                Intent intentCall = new Intent(Intent.ACTION_CALL);
                intentCall.setData(Uri.parse(uri));
                startActivity(intentCall);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUESTPERMASSIN);
            }

        });


    }


}
