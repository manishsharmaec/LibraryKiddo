package com.kiddolib.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UnlockEverything {

//    This will tell the status of Membership either Active, Expired, None

    @SerializedName("purchase_date")
    String purchase_date;

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getExpires_date() {
        return expires_date;
    }

    public void setExpires_date(String expires_date) {
        this.expires_date = expires_date;
    }

    @SerializedName("expires_date")
    String expires_date;

    public UnlockEverything(String purchase_date, String expires_date){
        this.expires_date = expires_date;
        this.purchase_date = purchase_date;
    }

}
