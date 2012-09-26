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

    public String[] getTransitionNames() {
        String[] names = new String[transitions.size()];
        for (int i = 0; i < transitions.size(); i++) {
            names[i] = transitions.get(i).getName();
        }
        return names;
    }

    public static String buildKey(String project, String key) {
        return String.format("%s-%s", project, key);
    }
}
