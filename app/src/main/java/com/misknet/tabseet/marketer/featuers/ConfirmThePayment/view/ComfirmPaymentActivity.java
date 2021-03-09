package com.misknet.tabseet.marketer.featuers.ConfirmThePayment.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.QrActivity.view.QrActivity;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.view.RecordFragment;

public class ComfirmPaymentActivity extends AppCompatActivity implements IComfirmPayment {
    TextView tv_number_old, date_old, tv_your_cite_old, tv_your_type_cite_old,
            tv_your_additional_details_of_the_site_old, tv_your_entry_fee_old,
            tv_your_first_payment_fees_old, tv_your_the_group,tv_first_payment_fees;
    Button checkWithQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_payment);

        init();

        Toolbar toolbar = findViewById(R.id.toolbar_top);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        intentData();


    }


    @Override
    public void init() {
        tv_number_old = findViewById(R.id.tv_number_old);
        date_old = findViewById(R.id.date_old);
        tv_first_payment_fees=findViewById(R.id.tv_first_payment_fees);
        tv_your_cite_old = findViewById(R.id.tv_your_cite_old);
        tv_your_type_cite_old = findViewById(R.id.tv_your_type_cite_old);
        tv_your_additional_details_of_the_site_old = findViewById(R.id.tv_your_additional_details_of_the_site_old);
        tv_your_entry_fee_old = findViewById(R.id.tv_your_entry_fee_old);
        tv_your_first_payment_fees_old = findViewById(R.id.tv_your_first_payment_fees_old);
        tv_your_the_group = findViewById(R.id.tv_your_the_group);
        checkWithQr = findViewById(R.id.checkWithQr);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void intentData() {
        Intent i = getIntent();
        if (i.hasExtra("flageView")) {
            checkWithQr.setVisibility(View.GONE);
        }
        String dataPayment = i.getStringExtra("dataPayment");
        String cityName = i.getStringExtra("cityName");
        String buildingCatName = i.getStringExtra("buildingCatName");
        String addressDetails = i.getStringExtra("addressDetails");
        String groubid = i.getStringExtra("groupid");
        int id = i.getIntExtra("id", -1);
        int amount = i.getIntExtra("amount", -1);
        int cost = i.getIntExtra("cost", -1);



        tv_number_old.setText(getString(R.string.number_of_vistor) + id);
        tv_your_entry_fee_old.setText(cost + getString(R.string.number_of_entry_fee));
        tv_your_first_payment_fees_old.setText(amount + getString(R.string.r_s));
        tv_your_type_cite_old.setText(buildingCatName);
        date_old.setText(dataPayment);
        tv_your_the_group.setText(groubid);
        tv_your_cite_old.setText(cityName);
        tv_first_payment_fees.setText("");
        tv_your_additional_details_of_the_site_old.setText(addressDetails);


    }


    public void checkWithQr(View view) {
        startActivity(new Intent(this, QrActivity.class));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
