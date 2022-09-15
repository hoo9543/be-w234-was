package webserver.http;

public enum ContentType {
    css("text/css"),
    html("text/html;charset=utf-8");

    private final String contentType;

    ContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "Content-Type: " + contentType;
    }
}
