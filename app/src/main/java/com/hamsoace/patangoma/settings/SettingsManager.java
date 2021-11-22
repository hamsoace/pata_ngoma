package com.hamsoace.patangoma.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String pref_name = "patangoma";

    private static final String user_id = "userId";
    private static final String user_full_name = "userFullName";
    private static final String user_email = "userEmail";
    private static final String user_phone = "userPhone";
    private static final String user_birthday = "userBirthday";

    public SettingsManager(Context context) {
        this.context = context;

        int PRIVATE_MODE = 0;

        sharedPreferences = this.context.getSharedPreferences(pref_name, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public String get(String pref, String defaultValue) {
        return sharedPreferences.getString(pref, defaultValue);
    }

    public boolean get(String pref, boolean defaultValue) {
        return sharedPreferences.getBoolean(pref, defaultValue);
    }

    public void set(String pref, String value) {
        editor.putString(pref, value);
        editor.commit();
    }

    public void set(String pref, boolean value) {
        editor.putBoolean(pref, value);
        editor.commit();
    }

    public String getUser_id() {
        return get(user_id, "");
    }

    public void setUser_id(String user_id) {
        set(user_id, user_id);
    }

    public String getUser_full_name() {
        return get(user_full_name, "");
    }

    public void setFullName(String user_full_name) {
        set(user_full_name, user_full_name);
    }

    public String getUser_email() {
        return get(user_email, "");
    }

    public void setUser_email(String user_email) {
        set(user_email, user_email);
    }

    public String getUser_phone() {
        return get(user_phone, "");
    }

    public void setUser_phone(String user_phone) {
        set(user_phone, user_phone);
    }

    public String getUser_birthday() {
        return get(user_birthday, "");
    }

    public void setUser_birthday(String user_birthday) {
        set(user_birthday, user_birthday);
    }

    public void logout(){
        editor.remove(user_id);
        editor.remove(user_full_name);
        editor.remove(user_email);
        editor.remove(user_phone);
        editor.remove(user_birthday);

        editor.apply();
    }
}
