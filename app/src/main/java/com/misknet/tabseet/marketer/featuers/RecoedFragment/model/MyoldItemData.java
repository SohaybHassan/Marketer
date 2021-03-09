package com.misknet.tabseet.marketer.featuers.RecoedFragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyoldItemData {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("scheduled_payment_date")
    @Expose
    private String scheduledPaymentDate;
    @SerializedName("payment_date")
    @Expose
    private String paymentDate;
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("qr_code")
    @Expose
    private String qrCode;
    @SerializedName("subscription_id")
    @Expose
    private int subscriptionId;
    @SerializedName("marketer_id")
    @Expose
    private int marketerId;
    @SerializedName("payment_method_id")
    @Expose
    private int paymentMethodId;
    @SerializedName("subscription")
    @Expose
    private SubscriptionOLd subscription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScheduledPaymentDate() {
        return scheduledPaymentDate;
    }

    public void setScheduledPaymentDate(String scheduledPaymentDate) {
        this.scheduledPaymentDate = scheduledPaymentDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getMarketerId() {
        return marketerId;
    }

    public void setMarketerId(int marketerId) {
        this.marketerId = marketerId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public SubscriptionOLd getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionOLd subscription) {
        this.subscription = subscription;
    }

}