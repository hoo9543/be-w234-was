package webserver.exceptionController;

import exception.*;
import webserver.controller.Controller;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerMapper {
    private Map<Class<?>, Controller> exceptionHandlerMap = new HashMap<>();

    public ExceptionHandlerMapper(){
        NoAuthorizationController noAuthorizationController = new NoAuthorizationController();
        DuplicatedUserController duplicatedUserController = new DuplicatedUserController();
        LoginFailureController loginFailureController = new LoginFailureController();

        exceptionHandlerMap.put(NoAuthorizationException.class,noAuthorizationController);
        exceptionHandlerMap.put(DuplicatedUserIdException.class,duplicatedUserController);
        exceptionHandlerMap.put(NoSuchUserException.class,loginFailureController);
        exceptionHandlerMap.put(BadCredentialsException.class,loginFailureController);
    }

    public Controller getController(Class<?> c){
        Controller controller = exceptionHandlerMap.get(c);
        if (controller == null){
            throw new NotFoundExceptionHandlerException("");
        }
        return controller;
    }

}
