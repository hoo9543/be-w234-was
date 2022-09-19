package webserver;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpParser;
import webserver.controller.ControllerFactory;
import webserver.http.ModelAndView;
import webserver.http.Request;
import webserver.http.Response;
import webserver.controller.Controller;
import webserver.controller.DefaultController;
import webserver.controller.UserSaveController;

import static util.HttpParser.getProcessedRequestFromHttpRequest;


public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    private ControllerFactory controllerFactory;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
        controllerFactory = new ControllerFactory();
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            Request request = getProcessedRequestFromHttpRequest(in);
            Controller controller = controllerFactory.getController(request.getHttpMethod(), request.getUrl());

            Response response = new Response();
            ModelAndView modelAndView = controller.process(request,response);
            response.setResponseFromModelAndRequest(modelAndView,request);
            DataOutputStream dos = new DataOutputStream(out);
            response.sendResponse(dos);

        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (RuntimeException e){
            logger.error(e.getMessage());
        }
    }
}
