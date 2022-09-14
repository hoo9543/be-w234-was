package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ModelAndView;
import util.Request;
import util.Response;

public class DefaultController implements Controller{
    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Override
    public ModelAndView process(Request request, Response response) {
        ModelAndView modelAndView = new ModelAndView(request.getUrl());

        return modelAndView;
    }

}
