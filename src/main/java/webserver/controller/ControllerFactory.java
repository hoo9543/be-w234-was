package webserver.controller;

import webserver.http.HttpMethod;

public class ControllerFactory {

    public Controller getController(HttpMethod httpMethod, String url){
        if (httpMethod == HttpMethod.GET && url.equals("/user/create")){
            return new UserSaveController();
        }
        return new DefaultController();
    }
}
