package webserver.controller;

import db.Database;
import model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserServiceImpl;
import webserver.http.HttpMethod;
import webserver.http.request.Request;
import webserver.http.StatusCode;
import webserver.http.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LoginControllerTest {

    @BeforeEach
    void clear(){
        Database.clear();
    }

    @Test
    @DisplayName("login 성공 시 redirect 주소와 cookie, status code가 잘 설정되었는지 확인 ")
    void loginSuccessTest() throws IOException {

        Controller controller = new LoginController(new UserServiceImpl());
        Database.loadUser();
        Map<String,String> params = new HashMap<>();
        Map<String,String> headers = new HashMap<>();
        String body = "userId=user&password=pw&name=name&email=email";
        Request request = new Request(HttpMethod.GET,"/user/login",params,headers,body,"Http/1.1");

        Response response = controller.process(request);


        Assertions.assertThat(response.getHeaders().get("Location")).isEqualTo("/index.html");
        Assertions.assertThat(response.getHeaders().get("Set-Cookie")).isEqualTo("logined=true; Path=/");
        Assertions.assertThat(response.getStatusCode()).isEqualTo(StatusCode.FOUND);
    }

    /*
    @Test
    @DisplayName("login 실패 시 redirect 주소와 cookie, status code가 잘 설정되었는지 확인 ")
    void loginFailureTest() throws IOException {

        Controller controller = new LoginController(new UserServiceImpl());
        Database.loadUser();
        Map<String,String> params = new HashMap<>();
        Map<String,String> headers = new HashMap<>();
        String body = "userId=user1&password=pw&name=name&email=email";
        Request request = new Request(HttpMethod.GET,"/user/login",params,headers,body,"Http/1.1");


        Response response = controller.process(request);


        Assertions.assertThat(response.getHeaders().get("Location")).isEqualTo("/user/login_failed.html");
        Assertions.assertThat(response.getHeaders().get("Set-Cookie")).isEqualTo("logined=false");
        Assertions.assertThat(response.getStatusCode()).isEqualTo(StatusCode.FOUND);
    }
    */

}
