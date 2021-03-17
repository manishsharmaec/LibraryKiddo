package com.kiddolib.model;

import com.google.gson.annotations.SerializedName;

public class Entitlements {

    public UnlockEverything getUnlockEverything() {
        return unlockEverything;
    }

    public void setUnlockEverything(UnlockEverything unlockEverything) {
        this.unlockEverything = unlockEverything;
    }

    @SerializedName("UnlockEverything")
    UnlockEverything unlockEverything;

    public Entitlements(UnlockEverything unlockEverything){
        this.unlockEverything = unlockEverything;
    }
}
