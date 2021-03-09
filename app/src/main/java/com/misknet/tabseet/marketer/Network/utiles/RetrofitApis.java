package com.misknet.tabseet.marketer.Network.utiles;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApis {

    @POST("/api/v1/loginmarketer")
     Call<AppResponse> loginMarketer(@Body RequestBody p);

    @POST("/api/v1/changepassword")
    Call<AppResponse> changePassword (@Body RequestBody p);

    @GET("/api/v1/mypaymentvisits")
    Call<AppResponse> getMyVisitsData();


    @GET("/api/v1/oldpaymentvisits")
    Call<AppResponse> getOldMyVisitsData(@Query("page") int page);

    @POST("/api/v1/contactus")
    Call<AppResponse> contactUs  (@Body RequestBody p);

    @GET("/api/v1/changelang")
    Call<AppResponse> changLanguge();


    @GET("/api/v1/notifications")
    Call<AppResponse> notifications(@Query("page") int page);


    @POST("/api/v1/readnotification")
    Call<AppResponse> readnotification(@Body RequestBody p);


    @POST("/api/v1/readall")
    Call<AppResponse> readAll();


    @GET("/api/v1/notread")
    Call<AppResponse> unReade();


    @POST("/api/v1/logout")
    Call<AppResponse> Logout();

}
