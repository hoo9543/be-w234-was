package service;

import db.Database;
import model.User;

import static db.Database.addUser;
import static db.Database.findUserById;

public class UserService {
    //private static Database repository = Database.getDatabase();

    public void signUp(User user){
        if (findUserById(user.getUserId()) != null){
            throw new IllegalArgumentException("This userId already exists");
        }
        addUser(user);
    }
}
