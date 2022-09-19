package webserver.http;

public enum ContentType {
    CSS("css","text/css"),
    HTML("html","text/html;charset=utf-8");

    private final String extension;
    private final String contentType;

    ContentType(String extension,String contentType) {
        this.extension = extension;
        this.contentType = contentType;
    }

    public String getExtension(){
        return extension;
    }
    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "Content-Type: " + contentType;
    }

    public static String getContentTypeFromPath(String path){
        String[] splitPath = path.split("\\.");
        for(ContentType contentType:ContentType.values()){
            if(contentType.extension.equals(splitPath[splitPath.length-1])){
                return contentType.contentType;
            }
        }
        return null;
    }
}
