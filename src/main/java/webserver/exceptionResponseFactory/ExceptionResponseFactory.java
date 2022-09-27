package webserver.exceptionResponseFactory;

import webserver.http.StatusCode;
import webserver.http.response.Response;

import java.util.Map;

public interface ExceptionResponseFactory {

    public abstract Map<String,String> getHeaders();
    public abstract StatusCode getStatusCode();
}
