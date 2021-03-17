package com.kiddolib.model;

import com.google.gson.annotations.SerializedName;

public class Data {

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @SerializedName("subscriber")
    Subscriber subscriber;
}
