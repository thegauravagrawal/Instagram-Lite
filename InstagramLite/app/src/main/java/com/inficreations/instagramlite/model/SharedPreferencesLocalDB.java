package com.inficreations.instagramlite.model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import dev.niekirk.com.instagram4android.requests.payload.InstagramLoggedUser;

public class SharedPreferencesLocalDB {

    private static final String USER_DATABASE = "USER_DATABASE";

    private static final String pkId = "user_pk_id";
    private static final String profilePic = "user_profile_pic";
    private static final String username = "user_username";
    private static final String fullName = "user_full_name";
    private static final String password = "user_password";

    public static void setUserData(Context context, InstagramLoggedUser loggedUser, String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DATABASE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong(pkId,loggedUser.getPk());
        editor.putString(profilePic,loggedUser.getProfile_pic_url());
        editor.putString(username,loggedUser.getUsername());
        editor.putString(fullName,loggedUser.getFull_name());
        editor.putString(SharedPreferencesLocalDB.password,password);

        editor.apply();
        //editor.commit();

        AppConstants.USER_PK_ID = loggedUser.getPk();
        AppConstants.USER_PROFILE_PIC = loggedUser.getProfile_pic_url();
        AppConstants.USER_USERNAME = loggedUser.getUsername();
        AppConstants.USER_FULL_NAME = loggedUser.getFull_name();
        AppConstants.USER_PASSWORD = password;
    }

    public static void getUserData(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(USER_DATABASE, Context.MODE_PRIVATE);
        AppConstants.USER_PK_ID = sharedpreferences.getLong(pkId, 0);
        AppConstants.USER_PROFILE_PIC = sharedpreferences.getString(profilePic, null);
        AppConstants.USER_USERNAME = sharedpreferences.getString(username, null);
        AppConstants.USER_FULL_NAME = sharedpreferences.getString(fullName, null);
        AppConstants.USER_PASSWORD = sharedpreferences.getString(password, null);
    }

    public static void logoutUser(DialogInterface.OnClickListener onClickListener, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences(USER_DATABASE, Context.MODE_PRIVATE);
        sharedpreferences.edit().clear().apply();

        AppConstants.USER_PK_ID=0;
    }
}
