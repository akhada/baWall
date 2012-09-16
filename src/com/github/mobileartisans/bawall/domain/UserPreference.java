package com.github.mobileartisans.bawall.domain;

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
        return getPreference().isValid();
    }

    public void save(Preference preference) {
        SharedPreferences preferences = getPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME, preference.username);
        editor.putString(PASSWORD, preference.password);
        editor.putString(PROJECT_SITE, preference.projectSite);
        editor.commit();
    }

    private SharedPreferences getPreferences() {
        return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public Preference getPreference() {
        SharedPreferences preferences = getPreferences();
        String username = preferences.getString(USERNAME, null);
        String password = preferences.getString(PASSWORD, null);
        String projectSite = preferences.getString(PROJECT_SITE, null);
        return new Preference(username, password, projectSite);
    }

    public static class Preference {
        public final String username;
        public final String password;
        public final String projectSite;
        public final String serviceSite = "https://bawall.herokuapp.com";

        public Preference(String username, String password, String projectSite) {
            this.username = username;
            this.password = password;
            this.projectSite = projectSite;
        }

        public boolean isValid() {
            return username != null && password != null & projectSite != null;
        }
    }
}
