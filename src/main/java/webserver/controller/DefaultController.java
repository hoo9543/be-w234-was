package webserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.ModelAndView;
import webserver.http.Request;
import webserver.http.Response;

public class DefaultController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Override
    public ModelAndView process(Request request, Response response) {
        ModelAndView modelAndView = new ModelAndView(request.getUrl());

        return modelAndView;
    }

}