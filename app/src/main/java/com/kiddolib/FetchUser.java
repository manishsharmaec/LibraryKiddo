package com.kiddolib;

import com.kiddolib.model.MemberDetails;

public interface FetchUser {
     void onSuccess(MemberDetails memberDetails);
     void onFailure(String errorMessage);
}
