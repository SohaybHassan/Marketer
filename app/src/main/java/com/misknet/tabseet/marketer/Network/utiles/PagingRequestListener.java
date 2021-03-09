package com.misknet.tabseet.marketer.Network.utiles;

public interface PagingRequestListener <T> {

    void onSuccess(int page,int pageCount , T data);

    void onFail(String message, int code);

}
