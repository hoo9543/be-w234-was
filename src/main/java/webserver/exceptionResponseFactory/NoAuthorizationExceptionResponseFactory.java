package webserver.exceptionController;

import webserver.http.Constants;
import webserver.http.StatusCode;
import webserver.http.response.Response;

public class NoAuthorizationExceptionResponseFactory implements ExceptionResponseFactory {

    @Override
    public void setHeaders(Response response) {
        response.setLocation(Constants.LOGIN_PATH);
    }
    @Override
    public void setStatusCode(Response response) {
        response.setStatusCode(StatusCode.FOUND);
    }
}
