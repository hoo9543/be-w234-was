package webserver.http;

public enum ContentType {
    CSS("css","text/css"),
    HTML("html","text/html;charset=utf-8"),
    PLAIN_TEXT(".txt","text/plain");


    private final String extension;
    private final String description;

    ContentType(String extension,String description) {
        this.extension = extension;
        this.description = description;
    }

    public String getExtension(){
        return extension;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Content-Type: " + description;
    }

    public static ContentType getContentTypeFromPath(String path){
        String[] splitPath = path.split("\\.");
        for(ContentType contentType:ContentType.values()){
            if(contentType.extension.equals(splitPath[splitPath.length-1])){
                return contentType;
            }
        }
        return ContentType.PLAIN_TEXT;
    }
}
