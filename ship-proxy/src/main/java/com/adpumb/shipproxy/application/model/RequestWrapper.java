package com.adpumb.shipproxy.application.model;


import java.util.Map;

public class RequestWrapper {
    private String url;
    private String method;
    private String body;
    private Map<String, String> headers;

    // Getters and Setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public Map<String, String> getHeaders() { return headers; }
    public void setHeaders(Map<String, String> headers) { this.headers = headers; }
}
