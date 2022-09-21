package util;

import db.Database;
import model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.controller.Controller;
import webserver.controller.LoginController;
import webserver.http.HttpMethod;
import webserver.http.request.Request;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserParserTest {

    @Test
    @DisplayName("Request Body의 user data가 invalid한 경우 ")
    void InvalidBodyUserDataTest(){

        String body = "password=pw&name=name&email=email";

        assertThatThrownBy(()-> UserParser.getUserFromRequestBody(body))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Invalid user data");
    }

    @Test
    @DisplayName("Query param의 user data가 invalid한 경우 ")
    void InvalidUserTest(){

        Map<String,String> params = new HashMap<>();
        params.put("password","pw");
        params.put("name","name");
        params.put("email","email");


        assertThatThrownBy(()-> UserParser.getUserFrom(params))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Invalid user data");
    }

    @Test
    @DisplayName("user data가 valid한 경우 ")
    void userParserTest(){
        String body = "userId=user&password=pw&name=name&email=email";

        User user = UserParser.getUserFromRequestBody(body);
        Assertions.assertThat(user.getUserId()).isEqualTo("user");
    }
}
