package webserver.controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import webserver.http.ModelAndView;
import webserver.http.Request;
import webserver.http.Response;
import webserver.http.StatusCode;

import java.util.HashMap;
import java.util.Map;

import static util.HttpRequestUtils.parseQueryString;

public class PostUserSaveController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(UserSaveController.class);
    private UserService userService = new UserService();
    @Override
    public ModelAndView process(Request request, Response response){
        Map<String,String> userData = parseQueryString(request.getBody());
        User user = new User(userData.get("userId"),userData.get("password"),userData.get("name"),userData.get("email"));

        userService.signUp(user);

        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.getParams().put("User",user);
        response.setStatusCode(StatusCode.FOUND);

        logger.debug("userId: " + user.getUserId());

        return modelAndView;
    }
}
