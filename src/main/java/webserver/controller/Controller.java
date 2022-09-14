package webserver;

import util.ModelAndView;
import util.Request;
import util.Response;

public interface Controller {
    ModelAndView process(Request request, Response response);
}
