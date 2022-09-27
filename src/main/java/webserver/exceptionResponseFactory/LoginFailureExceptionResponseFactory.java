package webserver.exceptionResponseFactory;

import webserver.http.Constants;
import webserver.http.StatusCode;
import webserver.http.response.Response;

public class LoginFailureExceptionResponseFactory implements ExceptionResponseFactory {

    @Override
    public void setHeaders(Response response) {
        response.setLocation(Constants.LOGIN_FAILURE_PATH);
        response.setCookie(Constants.LOGIN_FAILURE_COOKIE);
    }
    @Override
    public void setStatusCode(Response response) {
        response.setStatusCode(StatusCode.FOUND);
    }
}
