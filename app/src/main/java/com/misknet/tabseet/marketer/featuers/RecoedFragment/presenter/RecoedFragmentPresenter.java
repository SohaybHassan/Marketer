package com.misknet.tabseet.marketer.featuers.RecoedFragment.presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.asp.models.TabseetApp;
import com.misknet.tabseet.marketer.Network.utiles.PagingRequestListener;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.LoginActivity;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.model.MyoldItemData;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.view.IRecordFragment;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.view.RecordFragment;

import java.util.ArrayList;

public class RecoedFragmentPresenter {

    RecordFragment fragment;
    IRecordFragment view;
    Activity activity;

    public RecoedFragmentPresenter(RecordFragment fragment, IRecordFragment view, Activity activity) {
        this.fragment = fragment;
        this.view = view;
        this.activity=activity;

    }

    public void getData(int page, LinearLayout noData) {
        view.showLoading();
        GenerlNetworking.getInstance().getMyOldeVisits(page, new PagingRequestListener<ArrayList<MyoldItemData>>() {
            @Override
            public void onSuccess(int page, int pageCount, ArrayList<MyoldItemData> data) {
                if (data.size() > 0) {
                    view.onGetDataSuccee(data, page, pageCount);
                    view.hideLoading();
                    view.getPage(page);
                    view.getPagecount(pageCount);
                } else {
                    noData.setVisibility(View.VISIBLE);
                    view.hideLoading();
                }
            }

            @Override
            public void onFail(String message, int code) {
                Log.e("sohaib", message + "_" + code);


                if (code == 401) {
                    Intent intent401 = new Intent(activity, LoginActivity.class);
                    TabseetApp.getIstant().startActivity(intent401);
                    activity.finish();
                    view.onGetDataFail(message);
                    view.hideLoading();

                }


                view.onGetDataFail(message);
                view.hideLoading();

            }
        });

    }
}
