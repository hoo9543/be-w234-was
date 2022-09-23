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
        userFileWriter(user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static Collection<User> findAll() {
        return users.values();
    }

    public static void clear() { users.clear();}

    public static void loadUser() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./webapp/txt/user.txt"));
        String str;
        while ((str = reader.readLine()) != null) {
            User user = UserParser.getUserFromUserString(str);
            users.put(user.getUserId(), user);
        }
        reader.close();
    }

    private static void userFileWriter(User user) throws IOException {
        File file = new File("./webapp/txt/user.txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(user.toString()+"\r\n");
        writer.close();
    }

}
