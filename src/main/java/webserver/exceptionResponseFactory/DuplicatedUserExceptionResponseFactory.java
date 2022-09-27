package webserver.exceptionResponseFactory;

import webserver.http.StatusCode;
import webserver.http.response.Response;

public class DuplicatedUserExceptionResponseFactory implements ExceptionResponseFactory {

    @Override
    public void setHeaders(Response response) {}
    @Override
    public void setStatusCode(Response response) {
        response.setStatusCode(StatusCode.BAD_REQUEST);
    }

}
