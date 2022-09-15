package webserver.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static webserver.http.StatusCode.OK;

public class Response {
    private String httpVersion ="";
    private StatusCode statusCode=OK;
    private Map<String,String> headers=new HashMap<>();
    private byte[] body;

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
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }



    public Response(String httpVersion, StatusCode statusCode, Map<String, String> headers, byte[] body) {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public Response(){}

    public void setResponseFromModelAndRequest(ModelAndView model, Request request) throws IOException {
        setHttpVersion(request.getHttpVersion());
        setContentType(model.getPath());
        byte[] body = Files.readAllBytes(new File("./webapp" + model.getPath()).toPath());
        setBody(body);
        setContentLength();
    }

    private void setContentType(String path){
        if (path.contains("css")){
            headers.put("Content-Type", "text/css");
            return;
        }

        headers.put("Content-Type","text/html;charset=utf-8");
    }

    private void setContentLength(){
        headers.put("content-Length", String.valueOf(body.length));
    }

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
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


}
