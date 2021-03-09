package com.misknet.tabseet.marketer.featuers.HomeFragment.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.asp.models.TabseetApp;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.featuers.HomeFragment.model.DataToMvisits;
import com.misknet.tabseet.marketer.featuers.HomeFragment.view.IHomeFragment;
import com.misknet.tabseet.marketer.featuers.HomeFragment.view.homeFragment;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.LoginActivity;

import java.util.ArrayList;

public class HomeFragmentPrsenter {
    IHomeFragment view;
    homeFragment fragment;
    Activity activity;

    public HomeFragmentPrsenter(homeFragment fragment, IHomeFragment view,Activity activity){
        this.fragment=fragment;
        this.view=view;
        this.activity=activity;

    }
    public void  getData(LinearLayout noData){
        GenerlNetworking.getInstance().getMyNewPaymant(new RequesListiner<ArrayList<DataToMvisits>>() {
            @Override
            public void onSuccess(ArrayList<DataToMvisits> data) {

                if (data.size()>0){
                    view.getDataSuccee(data);
                    view.hideLoading();
                }else{
                    noData.setVisibility(View.VISIBLE);
                    view.hideLoading();
                }
            }

            @Override
            public void onFiler(String massage, int Code) {
                if (Code==401){
                    Intent intent401= new Intent(TabseetApp.getIstant(), LoginActivity.class);
                    TabseetApp.getIstant().startActivity(intent401);
                    activity.finish();
                    view.onGetDataFail(massage);
                    view.hideLoading();
                   }else{
                    TabseetApp.getIstant().startActivity(new Intent(TabseetApp.getIstant(), LoginActivity.class));
                    view.onGetDataFail(massage);
                    view.hideLoading();
                }



            }
        });

    }

}
