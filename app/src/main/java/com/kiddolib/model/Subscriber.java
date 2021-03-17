package com.kiddolib.model;

import com.google.gson.annotations.SerializedName;


public class Subscriber {


    @SerializedName("entitlements")
    Entitlements entitlements;

    @SerializedName("subscriptions")
    Subscriptions subscriptions;

    public Entitlements getEntitlements() {
        return entitlements;
    }

    public void setEntitlements(Entitlements entitlements) {
        this.entitlements = entitlements;
    }

    public Subscriptions getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Subscriptions subscriptions) {
        this.subscriptions = subscriptions;
    }


    public Subscriber(Subscriptions subscriptions, Entitlements entitlements){
        this.subscriptions = subscriptions;
        this.entitlements = entitlements;

    }

}
