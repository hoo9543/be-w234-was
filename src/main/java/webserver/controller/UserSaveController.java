package webserver.controller;

import exception.DuplicatedUserIdException;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import util.UserParser;
import webserver.http.*;
import webserver.http.request.Request;
import webserver.http.response.responseBody.DefaultResponseBody;
import webserver.http.response.Response;
import webserver.http.response.responseBody.TextResponseBody;

import java.io.IOException;
import java.util.Map;

public class UserSaveController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(UserSaveController.class);
    private UserService userService = UserService.getInstance();
    @Override
    public Response process(Request request) throws IOException {

        User user = UserParser.getUserFrom(request.getParams());
        Response response=null;

        try {
            userService.signUp(user);
            response = new Response(request.getHttpVersion(), StatusCode.FOUND, new DefaultResponseBody());
            response.getHeaders().put("Location", "/index.html");
        }catch(DuplicatedUserIdException e){
            response = new Response(request.getHttpVersion(), StatusCode.BAD_REQUEST, new TextResponseBody(e.getMessage()));
        }

        return response;
    }

}
