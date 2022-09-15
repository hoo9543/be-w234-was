package webserver.controller;

import webserver.http.ModelAndView;
import webserver.http.Request;
import webserver.http.Response;

public interface Controller {
    ModelAndView process(Request request, Response response);
}
