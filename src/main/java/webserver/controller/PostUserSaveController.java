package webserver.controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import webserver.http.util.UserParser;
import webserver.http.*;
import webserver.http.request.Request;
import webserver.http.response.responseBody.DefaultResponseBody;
import webserver.http.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostUserSaveController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(PostUserSaveController.class);
    private UserService userService;

    public PostUserSaveController(UserService userService){
        this.userService = userService;}
    @Override
    public Response process(Request request) throws IOException {
        User user = UserParser.getUserFromRequestBody(request.getBody());
        userService.signUp(user);

        Map<String,String> headers = new HashMap<>();
        headers.put(Constants.SET_LOCATION,Constants.INDEX_PATH);

        return new Response(request.getHttpVersion(), StatusCode.FOUND, headers, new DefaultResponseBody());
    }


}
