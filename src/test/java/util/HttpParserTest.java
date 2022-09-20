package util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.http.HttpMethod;
import webserver.http.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.HttpParser.stringDivideAndCheckNum;


public class HttpParserTest {
    @Test
    @DisplayName("request parsing header, queryString 확인")
    void httpParserTest() throws IOException {

        String requestLine = ("GET /user/create?userId=user&password=pw&name=name&email=email Http/1.1"+"\r\n"+
                "Accept: */*"+"\r\n"+"\r\n");
        InputStream in = new StringBufferInputStream(requestLine);
        Request request = HttpParser.getProcessedRequestFromHttpRequest(in);

        Assertions.assertThat(request.getHttpMethod()).isEqualTo(HttpMethod.GET);
        Assertions.assertThat(request.getUrl()).isEqualTo("/user/create");
        Assertions.assertThat(request.getParams().get("userId")).isEqualTo("user");
        Assertions.assertThat(request.getHttpVersion()).isEqualTo("Http/1.1");

    }

    @Test
    @DisplayName("request parsing body data 확인")
    void httpParserPostTest() throws IOException {

        String requestLine = ("POST /user/create Http/1.1"+"\r\n"+
                "Content-Length: 11"+"\r\n"+"\r\n"+ "userId=user");
        InputStream in = new StringBufferInputStream(requestLine);
        Request request = HttpParser.getProcessedRequestFromHttpRequest(in);

        Assertions.assertThat(request.getHttpMethod()).isEqualTo(HttpMethod.POST);
        Assertions.assertThat(request.getUrl()).isEqualTo("/user/create");
        Assertions.assertThat(request.getBody()).isEqualTo("userId=user");
        Assertions.assertThat(request.getHttpVersion()).isEqualTo("Http/1.1");
    }

    @Test
    @DisplayName("String split 할 때, String이 나누어진 개수와 입력한 숫자의 개수가 다를때 error 발생 ")
    void splitCheckErrorTest(){
        assertThatThrownBy(()-> stringDivideAndCheckNum("GET /index.html http/1.1"," ",2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Split string and CheckNumber do not match");
    }

    @Test
    @DisplayName("String split 할 때 String이 나누어진 개수와 입력한 숫자가 같음 ")
    void splitTest(){
        //Assertions.assertThrows(NotFoundUrlException.class,()->urlFromRequestLine("get"));
        assertThat(stringDivideAndCheckNum("GET /index.html http1.1"," ",3)[1])
                .isEqualTo("/index.html");

    }
}
