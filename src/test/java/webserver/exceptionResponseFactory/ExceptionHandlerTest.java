package webserver.exceptionResponseFactory;

import exception.BadCredentialsException;
import exception.DuplicatedUserIdException;
import exception.NoAuthorizationException;
import exception.NoSuchUserException;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.http.StatusCode;
import webserver.http.response.Response;

import java.io.IOException;

public class ExceptionHandlerTest {

    private ExceptionHandler exceptionHandler=null;
    private String httpVersion = "Http/1.1";

    @BeforeEach
    void setUp(){
        String httpVersion = "Http/1.1";

        exceptionHandler = new ExceptionHandler();
        exceptionHandler.addExceptionResponseFactory(DuplicatedUserIdException.class, new DuplicatedUserExceptionResponseFactory());
        exceptionHandler.addExceptionResponseFactory(BadCredentialsException.class, new LoginFailureExceptionResponseFactory());
        exceptionHandler.addExceptionResponseFactory(NoSuchUserException.class, new LoginFailureExceptionResponseFactory());
        exceptionHandler.addExceptionResponseFactory(NoAuthorizationException.class, new NoAuthorizationExceptionResponseFactory());
    }

    @Test
    @DisplayName("duplicatedUserIdException 처리 response 생성 확인")
    void duplicatedUserIdExceptionHandlerSuccessTest() throws IOException {
        RuntimeException e = new DuplicatedUserIdException("This userId already exists");

        Response response = exceptionHandler.createResponse(httpVersion,e);

        Assertions.assertThat(response.getBody()).isEqualTo("This userId already exists".getBytes());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(StatusCode.BAD_REQUEST);
    }
}
