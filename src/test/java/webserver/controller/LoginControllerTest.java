package webserver.controller;

import db.Database;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.BoardRepository;
import repository.DbBoardRepository;
import repository.DbUserRepository;
import repository.UserRepository;
import service.BoardService;
import service.BoardServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import webserver.http.Constants;
import webserver.http.HttpMethod;
import webserver.http.request.Request;
import webserver.http.StatusCode;
import webserver.http.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LoginControllerTest {

    private ControllerMapper controllerMapper = new ControllerMapper();
    @BeforeAll
    void setUp(){
        UserRepository userRepository = new DbUserRepository();
        BoardRepository boardRepository = new DbBoardRepository();

        UserService userService = new UserServiceImpl(userRepository);
        BoardService boardService = new BoardServiceImpl(boardRepository);


        controllerMapper.addController(HttpMethod.POST, Constants.USER_CREATE_PATH,new PostUserSaveController(userService));
        controllerMapper.addController(HttpMethod.POST,Constants.USER_LOGIN_PATH,new LoginController(userService));
        controllerMapper.addController(HttpMethod.GET,Constants.USER_LIST_PATH,new UserListController(userService));
        controllerMapper.addController(HttpMethod.POST,Constants.BOARD_CREATE_PATH,new BoardSaveController(boardService));
        controllerMapper.addController(HttpMethod.GET,Constants.BOARD_LIST_PATH,new BoardListController(boardService));
    }

    @Test
    @DisplayName("login 성공 시 redirect 주소와 cookie, status code가 잘 설정되었는지 확인 ")
    void loginSuccessTest() throws IOException {


        Controller controller = new LoginController(new UserServiceImpl(new DbUserRepository()));
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
