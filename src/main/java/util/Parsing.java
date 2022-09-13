package util;

public class Parsing {
    public static String getUrlFromRequestLine(String requestLine){
        try {
            return requestLine.split(" ")[1];
        } catch(RuntimeException e){
            throw new NotFoundUrlException("Can not find URL");
        }
    }
}
