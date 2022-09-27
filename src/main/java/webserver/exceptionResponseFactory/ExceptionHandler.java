package webserver.exceptionResponseFactory;

import exception.*;
import webserver.http.StatusCode;
import webserver.http.response.Response;
import webserver.http.response.responseBody.TextResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {
    private Map<Class<?>, ExceptionResponseFactory> exceptionHandlerMap = new HashMap<>();

    public ExceptionHandler(){}

    public Response createResponse(String httpVersion,Exception e) throws IOException {

        ExceptionResponseFactory exceptionResponseFactory = getExceptionResponseFactory(e.getClass());
        StatusCode statusCode = exceptionResponseFactory.getStatusCode();
        Map<String,String> headers = exceptionResponseFactory.getHeaders();

        return new Response(httpVersion,statusCode,headers,new TextResponseBody(e.getMessage()));
    }

    private ExceptionResponseFactory getExceptionResponseFactory(Class<?> c){
        ExceptionResponseFactory handler = exceptionHandlerMap.get(c);
        if (handler == null){
            throw new NotFoundExceptionHandlerException("");
        }
        return handler;
    }

    public void addExceptionResponseFactory(Class<?> c, ExceptionResponseFactory exceptionResponseFactory){
        exceptionHandlerMap.put(c, exceptionResponseFactory);
    }
}
