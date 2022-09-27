package db;

import com.google.common.collect.Maps;

import model.User;
import webserver.http.util.UserParser;

import java.io.*;
import java.util.Collection;
import java.util.Map;

public class Database {
    private static Map<String, User> users = Maps.newHashMap();

    public static void addUser(User user) throws IOException {
        users.put(user.getUserId(), user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static Collection<User> findAll() {
        return users.values();
    }

    public static void clear() { users.clear();}


}
