package webserver.http.response;

import webserver.http.ContentType;
import webserver.http.response.ResponseBody;

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
