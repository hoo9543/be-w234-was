package webserver.exceptionController;

import webserver.controller.Controller;
import webserver.http.StatusCode;
import webserver.http.request.Request;
import webserver.http.response.Response;
import webserver.http.response.responseBody.TextResponseBody;

import java.io.IOException;

public class DuplicatedUserResponseController implements Controller {

    @Override
    public Response process(Request request) throws IOException {
        return new Response(request.getHttpVersion(), StatusCode.BAD_REQUEST, new TextResponseBody("This userId already exists"));
    }
}
