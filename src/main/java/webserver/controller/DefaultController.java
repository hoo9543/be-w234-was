package webserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.*;
import webserver.http.response.responseBody.FileResponseBody;
import webserver.http.response.Response;
import webserver.http.response.responseBody.ResponseBody;

import java.io.IOException;

public class DefaultController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Override
    public Response process(Request request) throws IOException {
        ResponseBody fileResponseBody = new FileResponseBody(request.getUrl());
        return new Response(request.getHttpVersion(), StatusCode.OK,fileResponseBody);
    }

}