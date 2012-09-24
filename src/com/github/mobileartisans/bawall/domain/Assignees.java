package com.github.mobileartisans.bawall.domain;

import java.util.ArrayList;
import java.util.List;

public class Assignees {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public String[] getUserNames() {
        String[] userNames = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            userNames[i] = users.get(i).getDisplayName();
        }
        return userNames;
    }
}
