package webserver.http.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.StatusCode;
import webserver.http.response.responseBody.ResponseBody;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static webserver.http.StatusCode.OK;

public class Response {
    private String httpVersion ="";
    private StatusCode statusCode=OK;
    private Map<String,String> headers=new HashMap<>();
    private ResponseBody body;

    private static final Logger logger = LoggerFactory.getLogger(Response.class);
    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public byte[] getBody() {
        return body.getBody();
    }

    public void setBody(ResponseBody body) {
        this.body = body;
    }



    public Response(String httpVersion, StatusCode statusCode, ResponseBody body) {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.body = body;
        if (body.getContentLength() != 0){
            headers.put("Content-Type", body.getContentType().getDescription());
            headers.put("Content-Length",String.valueOf(body.getContentLength()));
        }
    }

    public Response(){}

    public void sendResponse(DataOutputStream dos) throws IOException {
        try{
            dos.writeBytes(httpVersion +" "+
                    statusCode.getValue()+" "+
                    statusCode.getDescription() + "\r\n");

            headers.forEach((key,value)-> {
                try {
                    dos.writeBytes(key+": "+value+" "+"\r\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            dos.writeBytes("\r\n");
            dos.write(body.getBody(), 0, body.getContentLength());
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


}
