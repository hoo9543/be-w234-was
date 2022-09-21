package webserver.controller;

import db.Database;
import exception.DuplicatedUserIdException;
import model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.http.HttpMethod;
import webserver.http.request.Request;
import webserver.http.StatusCode;
import webserver.http.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserSaveControllerTest {

    @BeforeEach
    void clear(){
        Database.clear();
    }

    @Test
    @DisplayName("user 생성 성공 시 redirect 주소와 statuscode 등을 확인")
    void userSaveSuccessTest() throws IOException {

        Controller controller = new UserSaveController();

        Map<String,String> params = new HashMap<>();
        params.put("userId","user");
        params.put("password","pw");
        params.put("name","name");
        params.put("email","email");

        Map<String,String> headers = new HashMap<>();
        Request request = new Request(HttpMethod.GET,"/user/create",params,headers,"","Http/1.1");

        Response response = controller.process(request);

        Assertions.assertThat(response.getHeaders().get("Location")).isEqualTo("/index.html");
        Assertions.assertThat(response.getStatusCode()).isEqualTo(StatusCode.FOUND);
    }

    @Test
    @DisplayName("user 생성 실패 시 DuplicatedUserException 발생")
    void userSaveFailureTest() throws IOException {

        Controller controller = new UserSaveController();

        Map<String,String> params = new HashMap<>();
        params.put("userId","user");
        params.put("password","pw");
        params.put("name","name");
        params.put("email","email");

        Map<String,String> headers = new HashMap<>();
        Request request = new Request(HttpMethod.GET,"/user/create",params,headers,"","Http/1.1");

        User user = new User("user","pw","name","email");
        Database.addUser(user);


        assertThatThrownBy(()-> controller.process(request))
                .isInstanceOf(DuplicatedUserIdException.class)
                .hasMessage("This userId already exists");
    }

}
