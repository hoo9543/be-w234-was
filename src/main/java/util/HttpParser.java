package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.HttpMethod;
import webserver.http.Request;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class HttpParser {

    private static final Logger logger = LoggerFactory.getLogger(HttpParser.class);


    public Request getProcessedRequestFromHttpRequest(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line = br.readLine();
        logger.debug("request line : {}", line);
        RequestLineData requestLineData = new RequestLineData(line);
        Map<String, String> headers = getHeadersFromInputStream(br);
        String body = getBodyFromInputStream(br);
        return new Request(requestLineData.getHttpMethod(), requestLineData.getUrl(), requestLineData.getParams(), headers, body, requestLineData.getProtocol());
    }


    public static Map<String, String> getParamsFrom(String str) {
        Map<String, String> params = new HashMap<>();
        String[] keyValues = str.split("&");
        for (String keyValue : keyValues) {
            String[] value = stringDivideAndCheckNum(keyValue,"=",2);
            params.put(value[0], value[1]);
        }
        return params;
    }

    public Map<String, String> getHeadersFromInputStream(BufferedReader br) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String line = br.readLine();
        while (!"".equals(line)) {
            if (line == null) {
                logger.error("header error");
                throw new IllegalArgumentException("");
            }
            logger.debug("header : {}", line);
            String[] header = line.split(":", 2);
            headers.put(header[0].trim(), header[1].trim());
            line = br.readLine();
        }
        return headers;
    }

    public String getBodyFromInputStream(BufferedReader br) throws IOException {
        return "";
    }

    public static String[] stringDivideAndCheckNum(String str, String delimiter, int CheckNum){
        String[] splitStr = str.split(delimiter);
        if (splitStr.length != CheckNum) {
            throw new IllegalArgumentException("Split string and CheckNumber do not match");
        }
        return splitStr;
    }

    private class RequestLineData {
        private HttpMethod httpMethod;
        private String url;
        private Map<String,String> params;
        private String protocol;

        private RequestLineData(String line){
            String[] request = stringDivideAndCheckNum(line,"\\s",3);
            if (request[1].contains("?")) {
                String[] req = request[1].split("\\?");

                url = req[0];
                params = getParamsFrom(req[1]);
            }
            else {
                url = request[1];
                params = new HashMap<>();
            }
            httpMethod = HttpMethod.valueOf(request[0]);
            protocol = request[2];
        }

        private HttpMethod getHttpMethod() {return httpMethod;}
        private String getUrl(){ return url; }
        private Map<String,String> getParams() { return params; }
        private String getProtocol(){ return protocol;}
    }
}
