package webserver.exceptionResponseFactory;

import webserver.http.Constants;
import webserver.http.StatusCode;
import webserver.http.response.Response;

import java.util.HashMap;
import java.util.Map;

public class NoAuthorizationExceptionResponseFactory implements ExceptionResponseFactory {

    @Override
    public Map<String,String> getHeaders() {
        Map<String,String> headers = new HashMap<>();
        headers.put(Constants.SET_LOCATION,Constants.LOGIN_PATH);
        return headers;
    }
    @Override
    public StatusCode getStatusCode() {
        return StatusCode.FOUND;
    }
}
