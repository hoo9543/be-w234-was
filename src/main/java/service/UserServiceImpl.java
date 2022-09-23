package service;

import db.Database;
import exception.BadCredentialsException;
import exception.DuplicatedUserIdException;
import exception.NoSuchUserException;
import model.LoginData;
import model.User;

import java.io.IOException;

import static db.Database.addUser;
import static db.Database.findUserById;

public class UserService {
    private UserService(){}
    private static UserService instance = new UserService();
    public static UserService getInstance(){ return instance;}


    public void signUp(User user) throws IOException {
        if (findUserById(user.getUserId()) != null){
            throw new DuplicatedUserIdException("This userId already exists");
        }
        addUser(user);
    }

    public void login(LoginData loginUser){
        User user = findUserById(loginUser.getUserId());
        if (user == null){
            throw new NoSuchUserException("Failed to find User with id[" +loginUser.getUserId()+"]");
        }
        if (!user.getPassword().equals(loginUser.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

    }
}
