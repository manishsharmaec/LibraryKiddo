package com.kiddolib;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.kiddolib.model.Data;
import com.kiddolib.model.Entitlements;
import com.kiddolib.model.MemberDetails;
import com.kiddolib.retrofit.ApiService;
import com.kiddolib.retrofit.MyHttpRequestTask;
import com.kiddolib.retrofit.RestClient;
import com.kiddolib.util.InternetConnectivity;
import com.kiddolib.util.UtilityClass;


public class RegisterApp{
    private static String APIKEY=null;
    private static RegisterApp registerApp;
    private static Context context;

    public void init(Context context){
        this.context = context;
    }

    private RegisterApp(){
    }
    public static RegisterApp singleton(){
        if(registerApp==null){
            registerApp = new RegisterApp();
        }
        return registerApp;
    }


    public static void configureApp(String key){
        if(!key.equals("")&&key!=null)
            APIKEY = key;
    }

   /* public static void fetchMember(String userId, final FetchUser user){
        if(!APIKEY.equals("")&&APIKEY!=null){

            ApiService apiService = RestClient.getClient().create(ApiService.class);

            Call<Data> call = apiService.fetchSubscriberDetails(userId,APIKEY);

            call.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    int statusCode = response.code();
                    if(statusCode==200||statusCode==201)
                        {
                            Data data = response.body();
                            MemberDetails memberDetails = new MemberDetails();
                            UtilityClass ucl = new UtilityClass(data);
                            if(ucl.isValidUser()){
                                memberDetails.setExpiryDate(ucl.setexpiryDate());
                                memberDetails.setMemberSince(ucl.isMemberSince());
                                memberDetails.setMember(ucl.isMember());
                                user.onSuccess(memberDetails);
                            }
                            else {
                                memberDetails.setExpiryDate(null);
                                memberDetails.setMemberSince(null);
                                memberDetails.setMember(false);
                                user.onSuccess(memberDetails);
                            }


                        }
                        else{
                            user.onFailure(response.message());
                    }

                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {

                }
            });
        }
    }*/

    public static void fetchMember(String userId, final FetchUser user){

        if(InternetConnectivity.isInternetConncted(context)){
            String BASE_URL = "https://api.revenuecat.com";
            String my_url = "/v1/subscribers/";// Replace this with your own url
            new MyHttpRequestTask(context,user).execute(BASE_URL+my_url,userId,APIKEY);
        }else{
            Toast.makeText(context,"There is some issue with your internet",Toast.LENGTH_LONG).show();

        }


    }


}