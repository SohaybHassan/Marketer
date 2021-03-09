package com.misknet.tabseet.marketer.featuers.Notifications.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.misknet.tabseet.marketer.Network.utiles.NetworkingParent;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.Notifications.model.NotificationsItem;
import com.misknet.tabseet.marketer.featuers.Notifications.presenter.NotificationPresenter;
import com.misknet.tabseet.marketer.featuers.settingFragment.adapter.NotificationAdapter;


import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity implements INotificationActivity {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    LinearLayoutManager linearLayoutManager;
    ConstraintLayout constraintLayout;
    RelativeLayout no_inter_net;
    Button btn_refresh;


    ArrayList<NotificationsItem> items;
    NotificationAdapter notificationAdapter;
    private final String TAG = NotificationsActivity.class.getSimpleName();
    private boolean isLoading = false;
    private int page = 1;
    private int page_count;
    private ProgressBar progres;
    private NotificationPresenter presenter;
    private INotificationActivity view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        Toolbar toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        progres = findViewById(R.id.avi);
        recyclerView = findViewById(R.id.notification_rv);
        linearLayout = findViewById(R.id.no_data);
        constraintLayout = findViewById(R.id.notification);
        no_inter_net = findViewById(R.id.layout_no_internet);
        btn_refresh = findViewById(R.id.btn_refresh);


        view = this;
        presenter = new NotificationPresenter(this, view);

        showLoading();

        test();


        btn_refresh.setOnClickListener(v -> {
            showLoading();
            test();
        });


    }


    public void init() {

        items = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(items);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (!isLoading && page <= page_count) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && totalItemCount >= 0) {
                        getPage(page++);
                        isLoading = true;
                    }
                }
            });


        }
        recyclerView.setAdapter(notificationAdapter);

    }

    @Override
    public void onGetDataSuccee(ArrayList<NotificationsItem> data, int page, int pageCount) {
        isLoading = false;
        if (page == 2) {
            items.clear();
            items.addAll(data);
            notificationAdapter.notifyDataSetChanged();
        } else {
            items.addAll(data);
            notificationAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onGetDataFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
    public void getPage(int page) {
        this.page = page;
    }

    @Override
    public void getPagecount(int pageConte) {
        this.page_count = pageConte;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void test() {
        if (NetworkingParent.myConnection) {
            recyclerView.setVisibility(View.VISIBLE);
            no_inter_net.setVisibility(View.GONE);
            init();
            presenter.getNotification(page, linearLayout);
            hideLoading();
        } else {
            constraintLayout.setBackground(getResources().getDrawable(R.drawable.splash_logo, null));
            recyclerView.setVisibility(View.GONE);
            no_inter_net.setVisibility(View.VISIBLE);
            hideLoading();

        }
    }


}

