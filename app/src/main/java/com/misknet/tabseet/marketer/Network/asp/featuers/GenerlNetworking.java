package com.misknet.tabseet.marketer.Network.asp.featuers;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.misknet.tabseet.marketer.Network.asp.models.PreferinceHelper;
import com.misknet.tabseet.marketer.Network.asp.models.TabseetApp;
import com.misknet.tabseet.marketer.Network.utiles.PagingRequestListener;
import com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.model.ChangePasswordResult;
import com.misknet.tabseet.marketer.featuers.HomeFragment.model.DataToMvisits;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.model.Items;
import com.misknet.tabseet.marketer.Network.utiles.AppResponse;
import com.misknet.tabseet.marketer.Network.utiles.NetworkingParent;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.LoginActivity;
import com.misknet.tabseet.marketer.featuers.Notifications.model.NotifcationCounter;
import com.misknet.tabseet.marketer.featuers.Notifications.model.NotificationsItem;
import com.misknet.tabseet.marketer.featuers.Notifications.model.ReadNotification;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.model.MyoldItemData;
import com.misknet.tabseet.marketer.featuers.settingFragment.model.Changelang;
import com.misknet.tabseet.marketer.featuers.settingFragment.model.ContactUsReslet;
import com.misknet.tabseet.marketer.featuers.settingFragment.model.Logout;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerlNetworking {
    PreferinceHelper preferinceHelper;
    NetworkingParent AfterLoginnetworkingParent;
    NetworkingParent BeforLoginnetworkingParent;
    static GenerlNetworking instance;

    public static GenerlNetworking getInstance() {
        return (instance == null) ? new GenerlNetworking() : instance;
    }

    private GenerlNetworking() {
        this.BeforLoginnetworkingParent = new NetworkingParent(true);
        this.AfterLoginnetworkingParent = new NetworkingParent(false);


    }

    public void LoginMarketer(String password, String mobile, RequesListiner<Items> requesListiner) {
        JsonObject req = new JsonObject();
        req.addProperty("client_id", 1);
        req.addProperty("client_secret", "UK1eSdr8YjItuu6wk7wxkObt6mGQvzqYpr5bYIiC");
        req.addProperty("grant_type", "password");
        req.addProperty("password", password);
        req.addProperty("mobile", mobile);
        req.addProperty("device_type", "android");
        req.addProperty("device_token", PreferinceHelper.getTokenfcm());
        Log.d("MYTOKEN", PreferinceHelper.getTokenfcm());

        RequestBody requestBody = RequestBody.create(String.valueOf(req), MediaType.parse("application/json"));

        Call<AppResponse> call = AfterLoginnetworkingParent.getRetrofitApis().loginMarketer(requestBody);
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, @NotNull Response<AppResponse> response) {
                if (response.code() == 401) {
                    Intent intent = new Intent(TabseetApp.getIstant(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TabseetApp.getIstant().startActivity(intent);

                } else {
                    if (response.body().getStatus()) {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<Items>() {
                        }.getType();
                        Items data = gson.fromJson(gson.toJson(response.body().getItems()), listType);
                        PreferinceHelper preferinceHelper = new PreferinceHelper();
                        String token = data.getAccessToken();
                        preferinceHelper.saveData(token);
                        NetworkingParent.TOKEN = token;
                        Log.e("Sohaib", token);
                        Log.e("Sohaib", response.body().getCode() + " test code");

                        requesListiner.onSuccess(data);
                    } else {
                        requesListiner.onFiler(response.body().getMessage(), response.body().getCode());
                        Log.e("Sohaib123123", response.body().getCode() + " test code");

                    }
                }
                Log.e("Sohaib", response.body().getCode() + "test code");

            }

            @Override
            public void onFailure(@NotNull Call<AppResponse> call, @NotNull Throwable t) {

                requesListiner.onFiler(t.getMessage(), 0);
                Log.e("Sohaib", t.getMessage());
            }
        });


    }

    public void changePass(String oldPass, String newpass, RequesListiner<ChangePasswordResult> requesListiner) {
        JsonObject req = new JsonObject();
        req.addProperty("old_password", oldPass);
        req.addProperty("new_password", newpass);
        RequestBody requestBody = RequestBody.create(String.valueOf(req), MediaType.parse("application/json"));
        Call<AppResponse> call = BeforLoginnetworkingParent.getRetrofitApis().changePassword(requestBody);
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, @NotNull Response<AppResponse> response) {

                if (response.code() == 401) {
                    Intent intent = new Intent(TabseetApp.getIstant(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TabseetApp.getIstant().startActivity(intent);

                } else {
                    if (response.body().getStatus()) {

                        Gson gson = new Gson();
                        Type lis = new TypeToken<ChangePasswordResult>() {
                        }.getType();
                        ChangePasswordResult changePasswordResult = gson.fromJson(gson.toJson(response.body().getItems()), lis);

                        requesListiner.onSuccess(changePasswordResult);
                    } else {
                        requesListiner.onFiler(response.body().getMessage(), response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(Call<AppResponse> call, Throwable t) {
                requesListiner.onFiler(t.getMessage(), 0);
            }
        });
    }

    public void getMyNewPaymant(RequesListiner<ArrayList<DataToMvisits>> listner) {

        Call<AppResponse> call = BeforLoginnetworkingParent.getRetrofitApis().getMyVisitsData();
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, @NotNull Response<AppResponse> response) {
                //    Log.d("response", response.toString());
                if (response.code() == 401) {
//                    Intent intent = new Intent(TabseetApp.getIstant(), LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    TabseetApp.getIstant().startActivity(intent);

                } else {
                    if (response.body().getStatus()) {
                        Log.d("sohaib", "Error");
                        Gson gson = new Gson();
                        Type list = new TypeToken<ArrayList<DataToMvisits>>() {
                        }.getType();
                        ArrayList<DataToMvisits> data = gson.fromJson(gson.toJson(response.body().getItems()), list);
                        listner.onSuccess(data);

                    } else {
                        listner.onFiler(response.body().getMessage(), response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<AppResponse> call, @NotNull Throwable t) {
                Log.d("Sohaib", t.getLocalizedMessage());
                listner.onFiler(t.getMessage(), 0);
            }
        });
    }

    public void getMyOldeVisits(int paget, PagingRequestListener<ArrayList<MyoldItemData>> requesListiner) {


        Call<AppResponse> call = BeforLoginnetworkingParent.getRetrofitApis().getOldMyVisitsData(paget);
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, @NotNull Response<AppResponse> response) {
                if (response.code() == 401) {
                    Intent intent = new Intent(TabseetApp.getIstant(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TabseetApp.getIstant().startActivity(intent);

                } else {
                    if (response.body().getStatus()) {
                        Gson gson = new Gson();
                        Type list = new TypeToken<ArrayList<MyoldItemData>>() {
                        }.getType();
                        ArrayList<MyoldItemData> data = gson.fromJson(gson.toJson(response.body().getItems()), list);
                        requesListiner.onSuccess(response.body().getPage(), response.body().getPageCount(), data);
                    } else {
                        requesListiner.onFail(response.body().getMessage(), response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<AppResponse> call, @NotNull Throwable t) {
                requesListiner.onFail(t.getMessage(), 0);
            }
        });
    }

    public void contactus(String text, RequesListiner<ContactUsReslet> requesListiner) {

        JsonObject req = new JsonObject();
        req.addProperty("text", text);
        RequestBody requestBody = RequestBody.create(String.valueOf(req), MediaType.parse("application/json"));
        Call<AppResponse> call = BeforLoginnetworkingParent.getRetrofitApis().contactUs(requestBody);
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, @NotNull Response<AppResponse> response) {
                if (response.code() == 401) {
                    Intent intent = new Intent(TabseetApp.getIstant(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TabseetApp.getIstant().startActivity(intent);

                } else {

                    if (response.body().getStatus()) {
                        Gson gson = new Gson();
                        Type lis = new TypeToken<ContactUsReslet>() {
                        }.getType();
                        ContactUsReslet contactUs = gson.fromJson(gson.toJson(response.body().getItems()), lis);

                        requesListiner.onSuccess(contactUs);
                    } else {
                        requesListiner.onFiler(response.body().getMessage(), response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<AppResponse> call, @NotNull Throwable t) {
                requesListiner.onFiler(t.getMessage(), 0);
                Log.e("ContactUsDialogee", t.getMessage());
            }
        });
    }

    public void changLang(RequesListiner<Changelang> requesListiner) {
        Call<AppResponse> call = BeforLoginnetworkingParent.getRetrofitApis().changLanguge();
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, @NotNull Response<AppResponse> response) {
                if (response.code() == 401) {
                    Intent intent = new Intent(TabseetApp.getIstant(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TabseetApp.getIstant().startActivity(intent);

                } else {

                    if (response.body().getStatus()) {
                        Gson gson = new Gson();
                        Type lis = new TypeToken<Changelang>() {
                        }.getType();
                        Changelang changelang = gson.fromJson(gson.toJson(response.body().getItems()), lis);
                        requesListiner.onSuccess(changelang);
                    } else {
                        requesListiner.onFiler(response.body().getMessage(), response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<AppResponse> call, @NotNull Throwable t) {
                requesListiner.onFiler(t.getMessage(), 0);
            }
        });
    }

    public void notifications(int page, PagingRequestListener<ArrayList<NotificationsItem>> pagingRequestListener) {

        Call<AppResponse> call = BeforLoginnetworkingParent.getRetrofitApis().notifications(page);
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, @NotNull Response<AppResponse> response) {
                if (response.code() == 401) {
                    Intent intent = new Intent(TabseetApp.getIstant(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TabseetApp.getIstant().startActivity(intent);

                } else {
                    if (response.body().getStatus()) {
                        Gson gson = new Gson();
                        Type list = new TypeToken<ArrayList<NotificationsItem>>() {
                        }.getType();
                        ArrayList<NotificationsItem> data = gson.fromJson(gson.toJson(response.body().getItems()), list);
                        pagingRequestListener.onSuccess(response.body().getPage(), response.body().getPageCount(), data);
                    } else {
                        pagingRequestListener.onFail(response.body().getMessage(), response.body().getCode());
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call<AppResponse> call, @NotNull Throwable t) {
                pagingRequestListener.onFail(t.getMessage(), 0);
            }
        });
    }

    public void readNotifications(RequesListiner<ReadNotification> requesListiner) {

        JsonObject req = new JsonObject();
        req.addProperty("notification_id", new NotificationsItem().getId());
        RequestBody requestBody = RequestBody.create(String.valueOf(req), MediaType.parse("application/json"));

        Call<AppResponse> call = AfterLoginnetworkingParent.getRetrofitApis().readnotification(requestBody);
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, Response<AppResponse> response) {
                if (response.code() == 401) {
                    Intent intent = new Intent(TabseetApp.getIstant(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TabseetApp.getIstant().startActivity(intent);

                } else {
                    if (response.body().getStatus()) {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<ReadNotification>() {
                        }.getType();
                        ReadNotification data = gson.fromJson(gson.toJson(response.body().getItems()), listType);
                        requesListiner.onSuccess(data);
                    } else {
                        requesListiner.onFiler(response.body().getMessage(), response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(Call<AppResponse> call, @NotNull Throwable t) {
                requesListiner.onFiler(t.getMessage(), 0);
            }
        });

    }

    public void readAllNotification() {

        Call<AppResponse> call = AfterLoginnetworkingParent.getRetrofitApis().readAll();
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, @NotNull Response<AppResponse> response) {

            }

            @Override
            public void onFailure(@NotNull Call<AppResponse> call, @NotNull Throwable t) {

            }
        });
    }

    public void unReadeNotification(RequesListiner<NotifcationCounter> requesListiner) {
        Call<AppResponse> call = BeforLoginnetworkingParent.getRetrofitApis().unReade();
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, @NotNull Response<AppResponse> response) {
                if (response.code() == 401) {
                    Intent intent = new Intent(TabseetApp.getIstant(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    TabseetApp.getIstant().startActivity(intent);
                } else {
                    if (response.body().getStatus()) {
                        Gson gson = new Gson();
                        Type list = new TypeToken<NotifcationCounter>() {
                        }.getType();
                        NotifcationCounter data = gson.fromJson(gson.toJson(response.body().getItems()), list);
                        requesListiner.onSuccess(data);
                    } else {
                        requesListiner.onFiler(response.body().getMessage(), response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<AppResponse> call, @NotNull Throwable t) {
                requesListiner.onFiler(t.getMessage(), 0);
            }
        });


    }

    public void Logout() {

        Call<AppResponse> call = BeforLoginnetworkingParent.getRetrofitApis().Logout();
        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppResponse> call, @NotNull Response<AppResponse> response) {
                if (response.body().getStatus()) {

                } else {

                }
            }

            @Override
            public void onFailure(@NotNull Call<AppResponse> call, @NotNull Throwable t) {

            }
        });
    }


    public void ifUnathentecated(int cood) {
        if (cood == 401) {
            preferinceHelper.clerData();
        } else {

        }
    }
}
