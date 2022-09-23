package webserver.controller;

import exception.NoAuthorizationException;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerMapper {
    private Map<Class<?>,Controller> exceptionHandlerMap = new HashMap<>();

    private static ExceptionHandlerMapper instance = new ExceptionHandlerMapper();
    public static ExceptionHandlerMapper getInstance(){ return instance;}

    private ExceptionHandlerMapper(){
        exceptionHandlerMap.put(NoAuthorizationException.class,new NoAuthorizationResponseController());

    }

    public Controller getController(Class<?> c){
        return exceptionHandlerMap.get(c);
    }

}
