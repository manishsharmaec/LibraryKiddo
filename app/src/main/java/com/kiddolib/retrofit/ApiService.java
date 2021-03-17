package com.kiddolib.retrofit;
import com.kiddolib.model.Data;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @Headers({ "Content-Type: application/json"})
    @GET("/v1/subscribers/{app_user_id}")
    Call<Data> fetchSubscriberDetails(@Path("app_user_id") String app_user_id,@Header("Authorization") String apikey);

}
