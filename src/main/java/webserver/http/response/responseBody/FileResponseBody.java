package webserver.http.response.responseBody;

import webserver.http.ContentType;

import java.io.*;
import java.nio.file.Files;

public class FileResponseBody implements ResponseBody {
    ContentType contentType;
    int contentLength;
    byte[] body;

    public FileResponseBody(String path) throws IOException {
        this.body = Files.readAllBytes(new File("./webapp" + path).toPath());
        this.contentLength = body.length;
        this.contentType = ContentType.getContentTypeFromPath(path);
    }

    @Override
    public byte[] getBody(){ return body;}

    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

}
