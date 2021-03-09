package com.misknet.tabseet.marketer.featuers.RecoedFragment.view;

import com.misknet.tabseet.marketer.featuers.RecoedFragment.model.MyoldItemData;

import java.util.ArrayList;

public interface IRecordFragment {


    void onGetDataSuccee(ArrayList<MyoldItemData> data, int page, int pageCount);

    void onGetDataFail(String message);

    void sendDataToActivity(MyoldItemData data);

    void hideLoading();

    void showLoading();

    void getPage(int page);

    void getPagecount(int pageConte);
}
