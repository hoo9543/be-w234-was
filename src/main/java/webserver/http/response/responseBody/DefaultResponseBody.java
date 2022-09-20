package webserver.http.response.responseBody;

import webserver.http.ContentType;

public class DefaultResponseBody implements ResponseBody {

    @Override
    public byte[] getBody() {
        return null;
    }

    @Override
    public ContentType getContentType() {
        return null;
    }

    @Override
    public int getContentLength() {
        return 0;
    }
}
