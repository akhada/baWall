package com.github.mobileartisans.bawall.domain;

import java.util.ArrayList;
import java.util.List;

public class Assignees {
    private List<User> users;

    public List<String> getUsers() {
        List<String> result = new ArrayList<String>();
        for (User user : users) {
            result.add(user.getDisplayName());
        }
        return result;
    }
}
