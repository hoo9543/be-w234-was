package webserver.controller;

import exception.NoAuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.exceptionResponseFactory.ExceptionHandler;
import webserver.http.Constants;
import webserver.http.request.Request;
import webserver.http.response.Response;

import java.io.IOException;
import java.util.Map;

public class FrontController implements Controller{
    private static final Logger logger = LoggerFactory.getLogger(FrontController.class);
    //private static ArrayList<String> WHITE_LIST = new ArrayList<>(Arrays.asList(Constants.INDEX_PATH,"/user/create","/user/login"));

    private ControllerMapper controllerMapper;
    private ExceptionHandler exceptionHandler;

    public FrontController(ControllerMapper controllerMapper, ExceptionHandler exceptionHandler){
        this.controllerMapper = controllerMapper;
        this.exceptionHandler = exceptionHandler;
    }
    @Override
    public Response process(Request request) throws IOException{

        try {
            checkAuthorization(request);
            Controller controller = controllerMapper.getController(request.getHttpMethod(), request.getUrl());
            return controller.process(request);
        }catch(RuntimeException e) {
            logger.error(e.getMessage());
            return exceptionHandler.createResponse(request.getHttpVersion(),e);
        }

    }

    private void checkAuthorization(Request request){
        Map<String, String> cookie = request.getCookies();
        if (!request.logined()) {
            throw new NoAuthorizationException("No Authorization");
        }
    }
}
