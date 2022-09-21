package webserver.http.request;

import webserver.http.HttpMethod;

import java.util.Map;

public class Request {
    private HttpMethod httpMethod;
    private String url;
    private Map<String,String> params;
    private Map<String,String> headers;
    private String body;

    private String httpVersion;

    public Request(HttpMethod httpMethod, String url, Map<String,String> params,Map<String,String> headers,String body,String httpVersion){
        this.httpMethod = httpMethod;
        this.url = url;
        this.params = params;
        this.headers= headers;
        this.body = body;
        this.httpVersion = httpVersion;
    }

    public HttpMethod getHttpMethod() {return httpMethod;}
    public String getUrl(){ return url; }
    public Map<String,String> getParams() { return params; }
    public Map<String,String> getHeaders() { return headers; }
    public String getBody() { return body; }
    public String getHttpVersion() { return httpVersion; }


}
