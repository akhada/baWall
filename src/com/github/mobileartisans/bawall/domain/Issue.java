package com.github.mobileartisans.bawall.domain;

import java.util.Map;

public class Issue {
    public final String key;
    public final String assignee;
    public final String summary;
    public final Map<String, String> transitions;

    public Issue(String key, String assignee, String summary, Map<String, String> transitions) {
        this.key = key;
        this.assignee = assignee;
        this.summary = summary;
        this.transitions = transitions;
    }
}
