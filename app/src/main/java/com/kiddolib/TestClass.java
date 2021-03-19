package com.kiddolib;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.kiddolib.model.MemberDetails;
import com.kiddolib.retrofit.MyHttpRequestTask;

public class TestClass extends Activity{
    EditText editText;
    Button fetchUser;
    private String key=  "JIUNSdylLYUbLieJqYuFJpTnLgpZeAtr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_actvity);


         editText = (EditText)findViewById(R.id.inputId);
         fetchUser = (Button)findViewById(R.id.fetchUser);

         RegisterApp registerApp =    RegisterApp.singleton();
        registerApp.init(TestClass.this);
        RegisterApp.configureApp(key);


        //§  Test ID1: rc.test1 (will return a valid membership)
        //§  Test ID2: rc.test2 (will return an expired membership)
        //§  Test ID2: rc.test3


        fetchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId =  editText.getText().toString();
                if(userId.equals("")||userId==null){
                    editText.setError("Please enter user id");
                }
                else{
                    callApi(userId);
//                    callHTTP(userId,key);
                }

            }
        });


    }

   /* private void callHTTP(String userId,String key) {
        String BASE_URL = "https://api.revenuecat.com";
        String my_url = "/v1/subscribers/";// Replace this with your own url
        new MyHttpRequestTask(TestClass.this,this).execute(BASE_URL+my_url,userId,key);
    }*/



    private void    callApi(String userID) {

        RegisterApp.fetchMember(userID, new FetchUser() {
            @Override
            public void onSuccess(MemberDetails memberDetails) {
                Log.e("callApi method success",memberDetails.getExpiryDate().toString());

                if(memberDetails.getExpiryDate()!=null){
                    Toast.makeText(TestClass.this,"Expiry date: "+memberDetails.getExpiryDate(),Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(TestClass.this,"User not registered",Toast.LENGTH_LONG).show();
                }
                if(memberDetails.getMemberSince()!=null){
                    Toast.makeText(TestClass.this,"Member since: "+memberDetails.getMemberSince(),Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(TestClass.this,"User not registered",Toast.LENGTH_LONG).show();
                }
                if(memberDetails.isMember()){
                    Toast.makeText(TestClass.this,"Active Subscription",Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(TestClass.this,"User not registered",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("error",errorMessage);

            }
        });
    }


}
