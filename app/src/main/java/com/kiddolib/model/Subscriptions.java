package com.kiddolib.model;

import com.google.gson.annotations.SerializedName;

class Subscriptions {

    public UnlockEverythingDaily getRc_promo_UnlockEverything_daily() {
        return rc_promo_UnlockEverything_daily;
    }

    public void setRc_promo_UnlockEverything_daily(UnlockEverythingDaily rc_promo_UnlockEverything_daily) {
        this.rc_promo_UnlockEverything_daily = rc_promo_UnlockEverything_daily;
    }

    @SerializedName("rc_promo_UnlockEverything_daily")
    UnlockEverythingDaily rc_promo_UnlockEverything_daily;

    public Subscriptions(UnlockEverythingDaily rc_promo_UnlockEverything_daily){
        this.rc_promo_UnlockEverything_daily = rc_promo_UnlockEverything_daily;
    }
}
