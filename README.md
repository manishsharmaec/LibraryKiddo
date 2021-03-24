# LibraryKiddo
Library created for consuming api and returning subscription details

First we need to call method configure method to authorize http requests

RegisterApp.configureApp(key);//this key is Authorisation bearer token

We have utility class to for some utility functions for date conversion and date comparisions
We have used Retrofit2 library to call apis
From API response we check whether it is fresh user creation or fetching an existing user based on status code. 200 is for existing user and 201 is for created user successfully.
From response we created some model classed to parse the response and then based on entitlements data we checked whether subscription is Active, Expired or None. 

As per the requirements we were supposed to expose two class methods, so for the class RegisterApp we have two methods one is configureApp with parameter apiKey and other method fetchMember with parameters as userid and a completion block which returns three items
1. isMember: Yes/No
2. memberSince: Date
3. expiryDate: Date


Above three items were determined based on “UnlockEverything” entitlement data.

To use this lib make below changes in your app gradle file:
    // retrofit and KiddoLib
    implementation 'com.squareup.retrofit2:retrofit:2.1.0'
    implementation 'com.google.code.gson:gson:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.2.1'
    implementation(name:'kiddoLib', ext:'aar')
    
How to create a Github package or package dependency you can follow my medium article from here: 
https://manishsharma007.medium.com/create-android-dependency-package-for-your-library-d6bf92fc220f
