package webserver.http.response;

import webserver.http.ContentType;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface ResponseBody {
    public byte[] getBody();
    public ContentType getContentType();
    public int getContentLength();

}
