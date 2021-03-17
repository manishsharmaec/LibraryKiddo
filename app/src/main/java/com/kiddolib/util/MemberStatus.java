package com.kiddolib.util;

public enum MemberStatus {
    ACTIVE("ACTIVE"), EXPIRED("EXPIRED"), NONE("NONE");


    private String status;

    // getter method
    public String getStatus()
    {
        return this.status;
    }

    // enum constructor - cannot be public or protected
    private MemberStatus(String action)
    {
        this.status = action;
    }
}
