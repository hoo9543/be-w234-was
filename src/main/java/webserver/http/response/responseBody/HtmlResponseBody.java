package webserver.http.response.responseBody;

import webserver.http.ContentType;

import java.io.IOException;

public class HtmlResponseBody implements ResponseBody {
    ContentType contentType;
    int contentLength;
    String body;

    public HtmlResponseBody(String text) throws IOException {
        this.body = text;
        this.contentType = ContentType.HTML;
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
