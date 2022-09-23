package webserver.controller;

import com.google.errorprone.annotations.DoNotMock;
import db.Database;
import exception.DuplicatedUserIdException;
import model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import service.UserService;
import service.UserServiceImpl;
import webserver.http.HttpMethod;
import webserver.http.StatusCode;
import webserver.http.request.Request;
import webserver.http.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class PostUserSaveControllerTest {



    @BeforeEach
    void clear(){
        Database.clear();
    }

    @Test
    @DisplayName("user 생성 성공 시 redirect 주소와 statuscode 등을 확인")
    void userSaveSuccessTest() throws IOException {

        UserService userService = mock(UserService.class);
        doNothing().when(userService).signUp(any(User.class));

        Controller controller = new PostUserSaveController(userService);

        Map<String,String> params = new HashMap<>();
        String body = "userId=user1&password=pw&name=name&email=email";

        Map<String,String> headers = new HashMap<>();
        Request request = new Request(HttpMethod.POST,"/user/create",params,headers,body,"Http/1.1");

        Response response = controller.process(request);

        verify(userService).signUp(any(User.class));
        Assertions.assertThat(response.getHeaders().get("Location")).isEqualTo("/index.html");
        Assertions.assertThat(response.getStatusCode()).isEqualTo(StatusCode.FOUND);
    }
    /*
    @Test
    @DisplayName("user 생성 실패 시 400 status code와 error message 전달")
    void userSaveFailureTest() throws IOException {

        Controller controller = new PostUserSaveController(new UserServiceImpl());

        Map<String,String> params = new HashMap<>();
        String body = "userId=user1&password=pw&name=name&email=email";

        Map<String,String> headers = new HashMap<>();
        Request request = new Request(HttpMethod.POST,"/user/create",params,headers,body,"Http/1.1");

        User user = new User("user","pw","name","email");
        Database.addUser(user);

        Response response = controller.process(request);

        Assertions.assertThat(new String(response.getBody())).isEqualTo("This userId already exists");
        Assertions.assertThat(response.getStatusCode()).isEqualTo(StatusCode.BAD_REQUEST);
    }
    */
}
