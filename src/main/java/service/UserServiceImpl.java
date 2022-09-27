package service;

import db.Database;
import exception.BadCredentialsException;
import exception.DuplicatedUserIdException;
import exception.NoSuchUserException;
import model.LoginData;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.io.IOException;
import java.util.Collection;

import static db.Database.addUser;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public void signUp(User user) throws IOException {
        if (userRepository.findById(user.getUserId()) != null){
            throw new DuplicatedUserIdException("This userId already exists");
        }
        userRepository.save(user);
    }

    @Override
    public void login(LoginData loginUser){
        User user = userRepository.findById(loginUser.getUserId());
        if (userRepository.findById(loginUser.getUserId()) == null){
            throw new NoSuchUserException("Failed to find User with id[" +loginUser.getUserId()+"]");
        }
        if (!user.getPassword().equals(loginUser.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

    }

    @Override
    public Collection<User> findAll(){
        return userRepository.findAll();

    }
}
