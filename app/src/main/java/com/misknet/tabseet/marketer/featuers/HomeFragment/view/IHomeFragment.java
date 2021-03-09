package com.misknet.tabseet.marketer.featuers.HomeFragment.view;

import com.misknet.tabseet.marketer.featuers.HomeFragment.model.DataToMvisits;

import java.util.ArrayList;

public interface IHomeFragment {


    void getDataSuccee(ArrayList<DataToMvisits> data);

    void sendDataToActivity(DataToMvisits data);

    void onGetDataFail(String message);

    void hideLoading();

    void showLoading();




}
