package com.misknet.tabseet.marketer.Network.utiles;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.androidstudy.networkmanager.Monitor;
import com.androidstudy.networkmanager.Tovuti;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.misknet.tabseet.marketer.Network.asp.models.PreferinceHelper;
import com.misknet.tabseet.marketer.Network.asp.models.TabseetApp;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.model.Items;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.model.LoginMarketerClass;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.model.LoginMarketergstData;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.LoginActivity;
import com.misknet.tabseet.marketer.featuers.ParentOfNavigationActivity.view.ParentOfNavigationActivity;
import com.misknet.tabseet.marketer.featuers.splashActivtiy.view.MainActivity;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.misknet.tabseet.marketer.Network.utiles.ApiConstent.ACCEPT;
import static com.misknet.tabseet.marketer.Network.utiles.ApiConstent.ACCEPT_LANGUAGE;
import static com.misknet.tabseet.marketer.Network.utiles.ApiConstent.CONTENT_TYPE;
import static com.misknet.tabseet.marketer.Network.utiles.ApiConstent.HEADER_ACCEPT_LANGUAGE;
import static com.misknet.tabseet.marketer.Network.utiles.ApiConstent.HEADER_CONTENT_TYPE;
import static com.misknet.tabseet.marketer.Network.utiles.ApiConstent.PASEURL;


public class NetworkingParent {


    private static NetworkingParent instans;
    private RetrofitApis retrofitApis;
    public static String TOKEN = "";
    Items items = new Items();
    PreferinceHelper preferinceHelper;
    public static boolean myConnection;


    public NetworkingParent(Boolean isLogin) {
        preferinceHelper = new PreferinceHelper();
        //Access to server useing Header
        Interceptor interceptor;

        if (isLogin) {
            interceptor = chain -> {
                Request.Builder builder = chain.request().newBuilder()
                        .addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE)
                        .addHeader(HEADER_ACCEPT_LANGUAGE, ACCEPT_LANGUAGE)
                        .addHeader(ACCEPT, CONTENT_TYPE)
                        .addHeader(ApiConstent.TOKEN, "Bearer " + preferinceHelper.getToken());
                return chain.proceed(builder.build());
            };
        } else {
            interceptor = chain -> {
                Request.Builder builder = chain.request().newBuilder()
                        .addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE)
                        .addHeader(HEADER_ACCEPT_LANGUAGE, ACCEPT_LANGUAGE)
                        .addHeader(ACCEPT, CONTENT_TYPE);

                return chain.proceed(builder.build());
            };
            Log.e("Sohaib", TOKEN + "");
        }
        init(interceptor);
    }

    public RetrofitApis getRetrofitApis() {
        return retrofitApis;


    }

    public void init(Interceptor interceptorToHeaderData) {

        //Show Data body
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptorToHeaderData)
                .readTimeout(6000, TimeUnit.SECONDS)
                .connectTimeout(6000, TimeUnit.SECONDS)
                .build();

        //init Retrofit and add client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)  //Show Data body
                .build();
        retrofitApis = retrofit.create(RetrofitApis.class);
    }

    public static void isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) TabseetApp.getIstant().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        connectivityManager.registerNetworkCallback(
                builder.build(),
                new ConnectivityManager.NetworkCallback() {

                    @Override
                    public void onAvailable(Network network) {


                        myConnection = true;
                    }


                    @Override
                    public void onLost(Network network) {



                        myConnection = false;
                    }
                }

        );

    }
}


