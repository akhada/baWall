package com.github.mobileartisans.bawall.domain;

import java.util.HashMap;
import java.util.Map;

public class AkhadaClient {

    private UserPreference.Preference preference;

    public AkhadaClient(UserPreference.Preference preference) {
        this.preference = preference;
    }

    public Issue getIssue(String issueKey) {
        String url = String.format("%s/%s/issue/%s", preference.serviceSite, preference.projectSite, "TEST-1");
        SimpleHttpClient simpleHttpClient = new SimpleHttpClient(preference.username, preference.password, url);

        String response = simpleHttpClient.get();

        Map<String, String> transitions = new HashMap<String, String>();
        transitions.put("1", "Ready for Dev");
        transitions.put("2", "In QA");
        transitions.put("3", "On hold");
        return new Issue("TEST-1", "Bob", "As a user I want to view the card status on my phone", transitions);
    }

}
