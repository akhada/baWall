package com.github.mobileartisans.bawall.domain;

import java.util.List;

public class Issue {
    private String key;
    private String assignee;
    private String summary;
    private String status;
    private List<Transition> transitions;

    public String getKey() {
        return key;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getSummary() {
        return summary;
    }

    public String getStatus() {
        return status;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }
}
