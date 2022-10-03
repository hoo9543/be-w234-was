package webserver;

import java.io.*;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.controller.Controller;
import webserver.controller.FrontController;
import webserver.http.request.Request;
import webserver.http.response.Response;

import static webserver.http.util.HttpParser.getProcessedRequestFrom;


public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    private Controller frontController;

    public RequestHandler(Socket connectionSocket, Controller frontController) throws IOException {
        this.connection = connectionSocket;
        this.frontController = frontController;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            Request request = getProcessedRequestFrom(in);
            Response response = frontController.process(request);
            response.sendResponse(out);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
