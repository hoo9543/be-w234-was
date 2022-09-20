package webserver.controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import webserver.http.*;
import webserver.http.response.responseBody.DefaultResponseBody;
import webserver.http.response.Response;

import java.util.Map;

import static util.HttpRequestUtils.parseQueryString;

public class PostUserSaveController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(UserSaveController.class);
    private UserService userService = UserService.getInstance();
    @Override
    public Response process(Request request){

        User user = getUserFromRequestBody(request.getBody());
        Response response = new Response(request.getHttpVersion(),StatusCode.FOUND,new DefaultResponseBody());

        userService.signUp(user);
        response.getHeaders().put("Location","/index.html");

        return response;
    }

    private User getUserFromRequestBody(String body){
        Map<String, String> userData = parseQueryString(body);
        if (userData.get("userId") == null || userData.get("password") == null || userData.get("name") == null || userData.get("email")== null){
            throw new RuntimeException("Invalid user data");
        }
        return new User(userData.get("userId"), userData.get("password"), userData.get("name"), userData.get("email"));
    }
}
