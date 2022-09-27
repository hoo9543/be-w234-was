package webserver.exceptionResponseFactory;

import webserver.http.Constants;
import webserver.http.StatusCode;
import webserver.http.response.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginFailureExceptionResponseFactory implements ExceptionResponseFactory {

    @Override
    public Map<String,String> getHeaders() {
        Map<String,String> headers = new HashMap<>();
        headers.put(Constants.SET_LOCATION,Constants.LOGIN_FAILURE_PATH);
        headers.put(Constants.SET_COOKIE,Constants.LOGIN_FAILURE_COOKIE);
        return headers;
    }
    @Override
    public StatusCode getStatusCode() {
        return StatusCode.FOUND;
    }
}
