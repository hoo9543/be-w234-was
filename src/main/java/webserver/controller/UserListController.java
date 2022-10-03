package webserver.controller;

import exception.NoAuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import webserver.http.StatusCode;
import webserver.http.request.Request;
import webserver.http.response.Response;
import webserver.http.response.responseBody.TextResponseBody;

import java.io.IOException;
import java.util.HashMap;

import static webserver.http.util.UserParser.getUserStringFrom;

public class UserListController implements Controller{

    private static final Logger logger = LoggerFactory.getLogger(UserListController.class);
    private UserService userService;

    public UserListController(UserService userService){this.userService = userService;}

    @Override
    public Response process(Request request) throws IOException {
        if (!request.logined()) {
            throw new NoAuthorizationException("No Authorization");
        }
        return new Response(request.getHttpVersion(), StatusCode.OK, new HashMap<>(),new TextResponseBody(getUserStringFrom(userService.findAll())));
    }



}
