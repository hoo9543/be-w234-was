package webserver.exceptionController;

import webserver.http.response.Response;

public interface ExceptionResponseFactory {

    public abstract void setHeaders(Response response);
    public abstract void setStatusCode(Response response);
}
