package com.github.repository;

import java.util.HashMap;
import java.util.Map;

public class UsersRepository {

    private static final Map<Long, String> startCounter = new HashMap<>();

    public boolean isUserInDb(long id) {
        return startCounter.containsKey(id);
    }

    public void addUser(long id) {
        startCounter.put(id, "New user");
    }
}
