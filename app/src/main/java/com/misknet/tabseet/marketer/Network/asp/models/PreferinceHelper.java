package com.misknet.tabseet.marketer.Network.asp.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class PreferinceHelper {


    //save data Key
    public static final String TOKEN = "TOKEN";
    public static final String ACCEPT_LANGUAGE = "AcceptLanguage";
    public static final String CONTER = "conter";
    public static final String TOKENFCM = "TOKENFCM";
    private String SAVE_DATA_ADDRESS = "com.misknet.tabseet.marketer.Network.asp.models.SAVE_DATA_ADDRESS";
    private SharedPreferences saveDataAddress;

    public PreferinceHelper() {
        saveDataAddress = TabseetApp.getIstant().getSharedPreferences(SAVE_DATA_ADDRESS, Context.MODE_PRIVATE);
    }

    public static int getNOTIFICATION_COUNTER() {
        int appLanguge;

        appLanguge = TabseetApp.getIstant()
                .getSharedPreferences(CONTER, Context.MODE_PRIVATE)
                .getInt(CONTER, 0);


        return appLanguge;
    }

    public static void setNOTIFICATION_COUNTER(int counter) {

        TabseetApp.getIstant().getSharedPreferences(CONTER, Context.MODE_PRIVATE)
                .edit().putInt(ACCEPT_LANGUAGE, counter).apply();

    }
//
//    public void deletData() {
//        SharedPreferences.Editor editor = saveDataAddress.edit();
//        editor.clear().apply();
//
//    }

    public void saveData(String token) {
        SharedPreferences.Editor editor = saveDataAddress.edit();
        Log.e("Prefrins", "saveData TOKEN in preferins helper");
        editor.putString(TOKEN, token);
        editor.apply();

    }

    public String getToken() {
// TODO I will to send  token  like   this ""
        String Token = saveDataAddress.getString(TOKEN, "");
        return Token;
    }

    public   void clerData() {
        SharedPreferences.Editor editor = saveDataAddress.edit();
        editor.clear().apply();
    }

    public static void setAppLanguge(String appLanguge) {
        TabseetApp.getIstant().getSharedPreferences(ACCEPT_LANGUAGE, Context.MODE_PRIVATE)
                .edit().putString(ACCEPT_LANGUAGE, appLanguge).apply();
    }

    public static String getAppLanguge() {
        String appLanguge = TabseetApp.getIstant()
                .getSharedPreferences(ACCEPT_LANGUAGE, Context.MODE_PRIVATE)
                .getString(ACCEPT_LANGUAGE, "ar");
        return appLanguge;
    }

    public static void setTokenfcm(String token) {
        TabseetApp.getIstant().getSharedPreferences(TOKENFCM, Context.MODE_PRIVATE)
                .edit().putString(TOKENFCM, token).apply();
    }

    public static String getTokenfcm() {
        String tokenFcm = TabseetApp.getIstant().getSharedPreferences(TOKENFCM, Context.MODE_PRIVATE).getString(TOKENFCM, "");
        return tokenFcm;
    }
}