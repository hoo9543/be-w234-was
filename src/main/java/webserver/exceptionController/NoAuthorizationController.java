package webserver.exceptionController;

import webserver.controller.Controller;
import webserver.http.StringConstants;
import webserver.http.StatusCode;
import webserver.http.request.Request;
import webserver.http.response.Response;
import webserver.http.response.responseBody.DefaultResponseBody;

import java.io.IOException;

public class NoAuthorizationController implements Controller {

    @Override
    public Response process(Request request) throws IOException {
        Response response = new Response(request.getHttpVersion(), StatusCode.FOUND,new DefaultResponseBody());
        response.setLocation(StringConstants.LOGIN_PATH);

        return response;
    }
}
