package com.misknet.tabseet.marketer.Network.utiles;

public interface RequesListiner<T> {


    void onSuccess(T data);

    void onFiler(String massage, int Code);
}
