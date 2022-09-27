package webserver.exceptionController;

import exception.*;
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
        Response response = new Response(httpVersion,new TextResponseBody(e.getMessage()));
        exceptionResponseFactory.setStatusCode(response);
        exceptionResponseFactory.setHeaders(response);
        return response;
    }

    private ExceptionResponseFactory getExceptionResponseFactory(Class<?> c){
        ExceptionResponseFactory handler = exceptionHandlerMap.get(c);
        if (handler == null){
            throw new NotFoundExceptionHandlerException("");
        }
        return handler;
    }

    public void addHandler(Class<?> c, ExceptionResponseFactory exceptionResponseFactory){
        exceptionHandlerMap.put(c, exceptionResponseFactory);
    }
}
