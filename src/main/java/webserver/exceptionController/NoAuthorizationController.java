package webserver.exceptionController;

import webserver.controller.Controller;
import webserver.http.Constants;
import webserver.http.StatusCode;
import webserver.http.request.Request;
import webserver.http.response.Response;
import webserver.http.response.responseBody.DefaultResponseBody;

import java.io.IOException;

public class NoAuthorizationResponseController implements Controller {

    @Override
    public Response process(Request request) throws IOException {
        Response response = new Response(request.getHttpVersion(), StatusCode.FOUND,new DefaultResponseBody());
        response.setLocation(Constants.LOGIN_PATH);

        return response;
    }
}
