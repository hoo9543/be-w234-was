package webserver.controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import util.UserParser;
import webserver.http.*;
import webserver.http.request.Request;
import webserver.http.response.responseBody.DefaultResponseBody;
import webserver.http.response.Response;

import java.io.IOException;
import java.util.Map;

import static util.HttpRequestUtils.parseQueryString;

public class LoginController implements Controller{

    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    private UserService userService = UserService.getInstance();

    @Override
    public Response process(Request request) throws IOException {

        User user = UserParser.getUserFromRequestBody(request.getBody());
        Response response = new Response(request.getHttpVersion(),StatusCode.FOUND,new DefaultResponseBody());

        try {
            userService.login(user);
            response.getHeaders().put("Location","/index.html");
            response.getHeaders().put("Set-Cookie","logined=true; Path=/");
        }catch(RuntimeException e){
            logger.debug(e.getMessage());
            response.getHeaders().put("Location","/user/login_failed.html");
            response.getHeaders().put("Set-Cookie","logined=false");
        }

        return response;
    }
}
