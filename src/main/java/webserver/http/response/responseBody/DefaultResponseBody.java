package webserver.http.response.responseBody;

import webserver.http.ContentType;

public class DefaultResponseBody implements ResponseBody {

    @Override
    public byte[] getBody() {
        return null;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.PLAIN_TEXT;
    }

    @Override
    public int getContentLength() {
        return 0;
    }
}
