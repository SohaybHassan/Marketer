package com.misknet.tabseet.marketer.featuers.ParentOfNavigationActivity.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.asp.models.PreferinceHelper;
import com.misknet.tabseet.marketer.Network.utiles.NetworkingParent;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.Notifications.model.NotifcationCounter;
import com.misknet.tabseet.marketer.featuers.Notifications.view.NotificationsActivity;


public class ParentOfNavigationActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
        TextView textView;

    ConstraintLayout constraintLayout;
    RelativeLayout no_inter_net;
    Button btn_refresh;
    LinearLayout linearLayout;
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_of_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        textView = findViewById(R.id.myTitle);
        constraintLayout = findViewById(R.id.contecerAllFragment);
        no_inter_net = findViewById(R.id.layout_no_internet);
        btn_refresh = findViewById(R.id.btn_refresh);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.homeFragment:
                    textView.setText(getResources().getString(R.string.visits));
                    break;
                case R.id.recordFragment:
                    textView.setText(getResources().getString(R.string.record));
                    break;
                case R.id.settingFragment:
                    textView.setText(getResources().getString(R.string.stting));
                    break;
            }
        });
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());


        //  btn_refresh.setOnClickListener(v -> checkNoetWork());
        GenerlNetworking.getInstance().unReadeNotification(new RequesListiner<NotifcationCounter>() {
            @Override
            public void onSuccess(NotifcationCounter data) {

                PreferinceHelper.setNOTIFICATION_COUNTER(data.getCountUnread());
            }

            @Override
            public void onFiler(String massage, int Code) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.notification);
        menuItem.setIcon(buildCounterDrawable(PreferinceHelper.getNOTIFICATION_COUNTER(), R.drawable.ic_alarm));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.notification:
                startActivity(new Intent(ParentOfNavigationActivity.this, NotificationsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private BitmapDrawable buildCounterDrawable(int count, int ic_notification) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.counter_menu_item_layout, null);
        view.setBackgroundResource(ic_notification);
        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.BadgeRelativeLayout);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) view.findViewById(R.id.BadgeCount);
            textView.setText("" + count);
        }
        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);

        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return new BitmapDrawable(getResources(), bitmap);
    }

}
