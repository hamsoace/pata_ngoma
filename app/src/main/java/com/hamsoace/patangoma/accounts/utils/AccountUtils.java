package com.hamsoace.patangoma.accounts.utils;

import android.content.Context;

import com.hamsoace.patangoma.aob.networks.RequestParameter;
import com.hamsoace.patangoma.models.User;

import okhttp3.Request;

public class AccountUtils {
    public static final String TAG = "AccountUtils";

    private User user;

    public AccountUtils(Context context) {
        user = new User(context);
    }

    public void sendFCMTokenToDb(final String token){
        RequestParameter requestParam = new RequestParameter();
        requestParam.put(User.email, user.getEmail());
        requestParam.put(User.fcmtoken, token);
    }
}
