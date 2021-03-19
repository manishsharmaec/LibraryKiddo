package com.kiddolib.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.kiddolib.FetchUser;
import com.kiddolib.model.Data;
import com.kiddolib.model.MemberDetails;
import com.kiddolib.util.UtilityClass;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MyHttpRequestTask extends AsyncTask<String, Integer, String> {
    final ProgressDialog progress;
    Context context;
    FetchUser fetchUser;

    public MyHttpRequestTask(Context ct, FetchUser fetchUser) {
        context = ct;
        progress = new ProgressDialog(context);
        this.fetchUser = fetchUser;
    }

    @Override
    protected String doInBackground(String... params) {
        String BASE_URL = "https://api.revenuecat.com";
        String apiPath = "/v1/subscribers/";
        String userId = params[0];
        String my_key = params[1];
        try {
            URL url = new URL(BASE_URL + apiPath + userId);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + my_key);

            try {

                int responseCode = httpURLConnection.getResponseCode();
                String responseMessage = httpURLConnection.getResponseMessage();
                if (responseCode == 200 || responseCode == 201) {
                    //successful
                    System.out.print(responseCode);
                    BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String output;

                    StringBuffer response = new StringBuffer();
                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }

                    in.close();

                    String jsonInString = response.toString();
                    Log.e("Response:-", jsonInString);

                    return jsonInString;

                } else {
                    //something went wrong with the api call
                    fetchUser.onFailure(responseMessage);
                    System.out.print("StatusCode failure:" + responseCode);
                    return null;
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // this is done so that there are no open connections left when this task is going to complete
                httpURLConnection.disconnect();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    @Override
    protected void onPostExecute(String jsonInString) {
        super.onPostExecute(jsonInString);
        if (progress != null && progress.isShowing())
            progress.dismiss();
//        Log.e("onPostExecute",s);


        Gson gson = new Gson();
        Data data = gson.fromJson(jsonInString, Data.class);
        MemberDetails memberDetails = new MemberDetails();
        UtilityClass ucl = new UtilityClass(data);
        if (progress != null && progress.isShowing())
            progress.dismiss();
        if (ucl.isValidUser()) {
            memberDetails.setExpiryDate(ucl.setexpiryDate());
            memberDetails.setMemberSince(ucl.isMemberSince());
            memberDetails.setMember(ucl.isMember());
            fetchUser.onSuccess(memberDetails);
        } else {
            memberDetails.setExpiryDate(null);
            memberDetails.setMemberSince(null);
            memberDetails.setMember(false);
            fetchUser.onSuccess(memberDetails);
        }

    }
}