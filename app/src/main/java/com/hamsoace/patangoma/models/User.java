package com.hamsoace.patangoma.models;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hamsoace.patangoma.settings.SettingsManager;

public class User {

    public static final String id = "user_id";
    public static final String fcmtoken = "user_token";
    public static final String name = "name";
    public static final String email = "email";
    public static final String phone = "phone";
    public static final String wallet_balance = "balance";

    private SettingsManager settingsManager;

    private boolean isLoggedIn;

    public User(Context context) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        settingsManager = new SettingsManager(context);

        this.isLoggedIn = user != null;
    }

    public static String getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPhone() {
        return phone;
    }

    public static String getWallet_balance() {
        return wallet_balance;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void logout() {
        settingsManager.logout();
    }

}
