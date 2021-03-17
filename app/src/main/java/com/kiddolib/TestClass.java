package com.kiddolib;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.kiddolib.model.MemberDetails;

public class TestClass extends Activity {
    EditText editText;
    Button fetchUser;
    private String key=  "JIUNSdylLYUbLieJqYuFJpTnLgpZeAtr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_actvity);


         editText = (EditText)findViewById(R.id.inputId);
         fetchUser = (Button)findViewById(R.id.fetchUser);

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
                }

            }
        });


    }

    private void callApi(String userID) {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        RegisterApp.fetchMember(userID, new FetchUser() {
            @Override
            public void onSuccess(MemberDetails memberDetails) {
                progress.dismiss();
                Log.e("exp date",memberDetails.getExpiryDate().toString());
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
                progress.dismiss();
                Log.e("error",errorMessage);

            }
        });
    }


}
