package webserver.http.response;

import webserver.http.ContentType;
import webserver.http.response.ResponseBody;

import java.io.*;

public class TextResponseBody implements ResponseBody {
    ContentType contentType;
    int contentLength;
    String body;

    public TextResponseBody(String text) throws IOException {
        this.body = text;
        this.contentType = ContentType.PLAIN_TEXT;
        this.contentLength = text.length();
    }

    @Override
    public byte[] getBody(){
        return body.getBytes();
    }

    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public int getContentLength() {
        return contentLength;
    }
}
