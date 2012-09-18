package com.github.mobileartisans.bawall.domain;

import com.google.gson.Gson;

public class AkhadaClient {

    public static final String TAG = AkhadaClient.class.getName();
    private UserPreference.Preference preference;

    public AkhadaClient(UserPreference.Preference preference) {
        this.preference = preference;
    }

    public Issue getIssue(String issueKey) {
        String url = String.format("%s/%s/issue/%s", preference.serviceSite, preference.projectSite, "TEST-1");
        SimpleHttpClient simpleHttpClient = new SimpleHttpClient(preference.username, preference.password, url);

        String json = simpleHttpClient.get();
        Gson gson = new Gson();
        return gson.fromJson(json, Issue.class);
    }

    public Assignees getAssignees(String issueKey) {
        String url = String.format("%s/%s/issue/%s/assignable", preference.serviceSite, preference.projectSite, "TEST-1");
        SimpleHttpClient simpleHttpClient = new SimpleHttpClient(preference.username, preference.password, url);

        String json = simpleHttpClient.get();
        Gson gson = new Gson();
        return gson.fromJson(json, Assignees.class);
    }

    public void updateStatus(String issueKey, String status) {
        String url = String.format("%s/%s/issue/%s/transition", preference.serviceSite, preference.projectSite, "TEST-1");
        SimpleHttpClient simpleHttpClient = new SimpleHttpClient(preference.username, preference.password, url);
        simpleHttpClient.post(String.format("{\"transition_id\":\"%s\"}", status));
    }
}
