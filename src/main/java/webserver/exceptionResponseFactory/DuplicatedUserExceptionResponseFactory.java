package webserver.exceptionResponseFactory;

import webserver.http.Constants;
import webserver.http.StatusCode;
import webserver.http.response.Response;

import java.util.HashMap;
import java.util.Map;

public class DuplicatedUserExceptionResponseFactory implements ExceptionResponseFactory {

    @Override
    public Map<String,String> getHeaders() {
        return new HashMap<>();
    }
    @Override
    public StatusCode getStatusCode() {
        return StatusCode.BAD_REQUEST;
    }
}
