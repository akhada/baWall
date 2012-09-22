package com.github.mobileartisans.bawall.domain;

import com.google.gson.Gson;

import java.util.List;

public class AkhadaClient {

    public static final String TAG = AkhadaClient.class.getName();
    private UserPreference.Preference preference;

    public AkhadaClient(UserPreference.Preference preference) {
        this.preference = preference;
    }

    public Issue getIssue(String issueKey) {
        String url = String.format("%s/%s/issue/%s", preference.serviceSite, preference.projectSite, toIssue(issueKey));
        SimpleHttpClient simpleHttpClient = new SimpleHttpClient(preference.username, preference.password, url);

        String json = simpleHttpClient.get();
        Gson gson = new Gson();
        return gson.fromJson(json, Issue.class);
    }

    public Assignees getAssignees(String issueKey) {
        String url = String.format("%s/%s/issue/%s/assignable", preference.serviceSite, preference.projectSite, toIssue(issueKey));
        SimpleHttpClient simpleHttpClient = new SimpleHttpClient(preference.username, preference.password, url);

        String json = simpleHttpClient.get();
        Gson gson = new Gson();
        return gson.fromJson(json, Assignees.class);
    }

    public void updateStatus(String issueKey, Transition status) {
        String url = String.format("%s/%s/issue/%s/transition", preference.serviceSite, preference.projectSite, toIssue(issueKey));
        SimpleHttpClient simpleHttpClient = new SimpleHttpClient(preference.username, preference.password, url);
        simpleHttpClient.post(String.format("{\"transition_id\":\"%s\"}", status.getId()));
    }

    public List<String> getProjects() {
        String url = String.format("%s/%s/projects", preference.serviceSite, preference.projectSite);
        SimpleHttpClient simpleHttpClient = new SimpleHttpClient(preference.username, preference.password, url);

        String json = simpleHttpClient.get();
        Gson gson = new Gson();
        return gson.fromJson(json, List.class);
    }

    private String toIssue(String issueKey) {
        return String.format("%s-%s", preference.defaultProject, issueKey);
    }
}
