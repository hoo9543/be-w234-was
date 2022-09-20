package webserver.controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import webserver.http.*;
import webserver.http.response.responseBody.DefaultResponseBody;
import webserver.http.response.Response;

import java.util.Map;

public class UserSaveController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(UserSaveController.class);
    private UserService userService = UserService.getInstance();
    @Override
    public Response process(Request request){

        User user = getUserFromRequestFrom(request.getParams());
        Response response = new Response(request.getHttpVersion(),StatusCode.FOUND,new DefaultResponseBody());

        userService.signUp(user);
        response.getHeaders().put("Location","/index.html");

        return response;
    }

    private User getUserFromRequestFrom(Map<String,String> params) {
        if (params.get("userId") == null || params.get("password") == null || params.get("name") == null || params.get("email")== null){
            throw new RuntimeException("Invalid user data");
        }
        return new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
    }
}
