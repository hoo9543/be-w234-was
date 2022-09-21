package service;

import db.Database;
import exception.BadCredentialsException;
import exception.DuplicatedUserIdException;
import exception.NoSuchUserException;
import model.LoginData;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceTest {

    @BeforeEach
    void clear(){
        Database.clear();
    }
    @Test
    @DisplayName("동일한 Id의 유저 가입실패 테스트")
    void signupTest() throws IOException {
        UserService userService = UserService.getInstance();

        User user1 = new User("user1","pw1","name1","email");
        User user2 = new User("user1","pw2","name2","email2");

        userService.signUp(user1);

        assertThatThrownBy(()-> userService.signUp(user2))
                .isInstanceOf(DuplicatedUserIdException.class)
                .hasMessage("This userId already exists");
    }

    @Test
    @DisplayName("login 시 user가 회원가입 되지 않은 경우 NoSuchUserException 발생 ")
    void loginNotFoundUserTest(){
        UserService userService = UserService.getInstance();

        LoginData user1 = new LoginData("user1","pw1");

        assertThatThrownBy(()-> userService.login(user1))
                .isInstanceOf(NoSuchUserException.class)
                .hasMessage("Failed to find User with id[" +user1.getUserId()+"]");
    }

    @Test
    @DisplayName("login 시 user의 비밀번호가 틀린 경우 BadCredentialsException 발생")
    void loginInvalidPasswordException() throws IOException {
        UserService userService = UserService.getInstance();
        User user1 = new User("user1","pw1","name1","email");
        LoginData user2 = new LoginData("user1","pw2");

        userService.signUp(user1);

        assertThatThrownBy(()-> userService.login(user2))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Invalid password");
    }
}
