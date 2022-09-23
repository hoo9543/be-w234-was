package webserver.controller;

import db.Database;
import exception.NoAuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestHandler;
import webserver.exceptionController.ExceptionHandlerMapper;
import webserver.http.request.Request;
import webserver.http.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FrontController implements Controller{
    private static final Logger logger = LoggerFactory.getLogger(FrontController.class);
    private static ArrayList<String> WHITE_LIST = new ArrayList<>(Arrays.asList("/index.html","/user/create","/user/login"));
    private static FrontController instance = new FrontController();

    public static FrontController getInstance() {
        return instance;
    }

    private ControllerMapper controllerMapper;
    private ExceptionHandlerMapper exceptionHandlerMapper;

    private FrontController(){
        controllerMapper = new ControllerMapper();
        exceptionHandlerMapper = new ExceptionHandlerMapper();
    }
    @Override
    public Response process(Request request) throws IOException{

        try {
            checkAuthorization(request);
            Controller controller = controllerMapper.getController(request.getHttpMethod(), request.getUrl());
            return controller.process(request);
        }catch(RuntimeException e) {
            logger.error(e.getMessage());
            Controller exceptionController = exceptionHandlerMapper.getController(e.getClass());
            return exceptionController.process(request);
        }

    }

    private void checkAuthorization(Request request){
        if (!WHITE_LIST.contains(request.getUrl())) {
            Map<String, String> cookie = request.getCookies();
            if (!cookie.get("logined").equals("true")){
                throw new NoAuthorizationException("Not login");
            }
        }
    }
}
