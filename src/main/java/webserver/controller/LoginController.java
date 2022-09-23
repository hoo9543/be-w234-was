package webserver.controller;

import model.LoginData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import webserver.http.util.UserParser;
import webserver.http.*;
import webserver.http.request.Request;
import webserver.http.response.responseBody.DefaultResponseBody;
import webserver.http.response.Response;

import java.io.IOException;

public class LoginController implements Controller{

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private UserService userService;

    public LoginController(UserService userService){ this.userService = userService;}

    @Override
    public Response process(Request request) throws IOException {

        LoginData loginData = UserParser.getLoginDataFrom(request.getBody());
        Response response = new Response(request.getHttpVersion(),StatusCode.FOUND,new DefaultResponseBody());

        userService.login(loginData);
        response.setLocation(StringConstants.INDEX_PATH);
        response.setCookie(StringConstants.LOGIN_COOKIE);

        return response;
    }
}
