package com.misknet.tabseet.marketer.featuers.Notifications.presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.asp.models.TabseetApp;
import com.misknet.tabseet.marketer.Network.utiles.PagingRequestListener;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.LoginActivity;
import com.misknet.tabseet.marketer.featuers.Notifications.model.NotificationsItem;
import com.misknet.tabseet.marketer.featuers.Notifications.view.INotificationActivity;
import com.misknet.tabseet.marketer.featuers.Notifications.view.NotificationsActivity;

import java.util.ArrayList;

public class NotificationPresenter {

    NotificationsActivity notificationsActivity;
    INotificationActivity view;

    public NotificationPresenter(NotificationsActivity notificationsActivity,INotificationActivity view) {
    this.notificationsActivity=notificationsActivity;
    this.view=view;

    }

    public void getNotification(int page , LinearLayout linearLayout) {
        GenerlNetworking.getInstance().notifications(page,new PagingRequestListener<ArrayList<NotificationsItem>>() {
            @Override
            public void onSuccess(int page, int pageCount, ArrayList<NotificationsItem> data) {
               if (data.size()>0){
                   view.onGetDataSuccee(data,page,pageCount);
                   view. hideLoading();
                   view.getPage(page);
                   view.getPagecount(pageCount);
               }else{
                   linearLayout.setVisibility(View.VISIBLE);
                   view.hideLoading();
               }

            }

            @Override
            public void onFail(String message, int code) {

                if (code==401){
                    Intent intent401= new Intent(TabseetApp.getIstant(), LoginActivity.class);
                    notificationsActivity.startActivity(intent401);
                    notificationsActivity.finish();
                    view.onGetDataFail(message);
                    view.hideLoading();
                    }
                view.hideLoading();
                view.onGetDataFail(message);
            }
        });
    }

}
