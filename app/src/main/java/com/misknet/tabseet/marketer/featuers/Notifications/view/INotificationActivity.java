package com.misknet.tabseet.marketer.featuers.Notifications.view;

import com.misknet.tabseet.marketer.featuers.Notifications.model.NotificationsItem;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.model.MyoldItemData;

import java.util.ArrayList;

public interface INotificationActivity {
    void onGetDataSuccee(ArrayList<NotificationsItem> data, int page, int pageCount);
    void onGetDataFail(String message);
    void hideLoading();
    void showLoading();
    void getPage(int page);
    void getPagecount(int pageConte);
}
