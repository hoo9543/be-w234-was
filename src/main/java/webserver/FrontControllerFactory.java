package webserver;

import exception.BadCredentialsException;
import exception.DuplicatedUserIdException;
import exception.NoAuthorizationException;
import exception.NoSuchUserException;
import repository.BoardRepository;
import repository.DbBoardRepository;
import repository.DbUserRepository;
import repository.UserRepository;
import service.*;
import webserver.controller.*;
import webserver.exceptionResponseFactory.DuplicatedUserExceptionResponseFactory;
import webserver.exceptionResponseFactory.ExceptionHandler;
import webserver.exceptionResponseFactory.LoginFailureExceptionResponseFactory;
import webserver.exceptionResponseFactory.NoAuthorizationExceptionResponseFactory;
import webserver.http.Constants;
import webserver.http.HttpMethod;

public class FrontControllerFactory implements ControllerFactory{

    @Override
    public Controller create(){

        ControllerMapper controllerMapper = new ControllerMapper();
        setControllers(controllerMapper);

        ExceptionHandler exceptionHandler = new ExceptionHandler();
        setExceptionResponseFactory(exceptionHandler);

        return new FrontController(controllerMapper, exceptionHandler);
    }

    private void setExceptionResponseFactory(ExceptionHandler exceptionHandler) {
        exceptionHandler.addExceptionResponseFactory(DuplicatedUserIdException.class, new DuplicatedUserExceptionResponseFactory());
        exceptionHandler.addExceptionResponseFactory(BadCredentialsException.class, new LoginFailureExceptionResponseFactory());
        exceptionHandler.addExceptionResponseFactory(NoSuchUserException.class, new LoginFailureExceptionResponseFactory());
        exceptionHandler.addExceptionResponseFactory(NoAuthorizationException.class, new NoAuthorizationExceptionResponseFactory());
    }

    private void setControllers(ControllerMapper controllerMapper) {

        controllerMapper.addController(HttpMethod.POST, Constants.USER_CREATE_PATH,ControllerFactoryFactory.getControllerFactory("user_save").create());
        controllerMapper.addController(HttpMethod.POST,Constants.USER_LOGIN_PATH,ControllerFactoryFactory.getControllerFactory("login").create());
        controllerMapper.addController(HttpMethod.GET,Constants.USER_LIST_PATH,ControllerFactoryFactory.getControllerFactory("user_list").create());
        controllerMapper.addController(HttpMethod.POST,Constants.BOARD_CREATE_PATH,ControllerFactoryFactory.getControllerFactory("board_save").create());
        controllerMapper.addController(HttpMethod.GET,Constants.BOARD_LIST_PATH,ControllerFactoryFactory.getControllerFactory("board_list").create());
    }
}
