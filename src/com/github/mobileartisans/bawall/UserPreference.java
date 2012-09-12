package com.github.mobileartisans.bawall;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    public static final String PREFERENCES = "BA_WALL_PREFERENCES";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String PROJECT_SITE = "projectSite";
    private Context context;

    public UserPreference(Context context) {
        this.context = context;
    }

    public boolean isSet() {
        SharedPreferences preferences = getPreferences();
        String username = preferences.getString(USERNAME, null);
        String password = preferences.getString(PASSWORD, null);
        String projectSite = preferences.getString(PROJECT_SITE, null);
        return username != null && password != null & projectSite != null;
    }

    public void save(String username, String password, String projectSite) {
        SharedPreferences preferences = getPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.putString(PROJECT_SITE, projectSite);
        editor.commit();
    }

    private SharedPreferences getPreferences() {
        return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }
}
