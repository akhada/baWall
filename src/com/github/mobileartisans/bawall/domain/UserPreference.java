package com.github.mobileartisans.bawall.domain;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    public static final String PREFERENCES = "BA_WALL_PREFERENCES";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String PROJECT_SITE = "projectSite";
    private static final String DEFAULT_PROJECT = "defaultProject";
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
        editor.putString(DEFAULT_PROJECT, preference.defaultProject);
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
        String defaultProject = preferences.getString(DEFAULT_PROJECT, null);
        return new Preference(username, password, projectSite, defaultProject);
    }

    public void clear() {
        SharedPreferences preferences = getPreferences();
        preferences.edit().clear().commit();
    }

    public void update(PreferenceUpdate preferenceUpdate) {
        Preference preference = getPreference();
        save(preferenceUpdate.update(preference));
    }

    public interface PreferenceUpdate {
        Preference update(Preference preference);
    }

    public static class Preference {
        public final String username;
        public final String password;
        public final String projectSite;
        public final String defaultProject;
        public final String serviceSite = "https://bawall.herokuapp.com";

        public Preference(String username, String password, String projectSite, String defaultProject) {
            this.username = username;
            this.password = password;
            this.projectSite = projectSite;
            this.defaultProject = defaultProject;
        }

        public boolean isValid() {
            return username != null && password != null & projectSite != null;
        }

        public Preference withDefaultProject(String selectedProject) {
            return new Preference(username, password, projectSite, selectedProject);
        }
    }
}
