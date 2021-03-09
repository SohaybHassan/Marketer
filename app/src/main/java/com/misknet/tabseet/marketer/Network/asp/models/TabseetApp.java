package com.misknet.tabseet.marketer.Network.asp.models;

import android.app.Application;

public class  TabseetApp extends Application {

    private static TabseetApp instans;

    public static TabseetApp getIstant(){
        return  instans;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instans=this;
    }
}
