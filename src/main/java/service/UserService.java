package service;

import model.LoginData;
import model.User;

import java.io.IOException;
import java.util.Collection;

public interface UserService {
    public void signUp(User user) throws IOException;
    public void login(LoginData loginData);

    public Collection<User> findAll();
}
