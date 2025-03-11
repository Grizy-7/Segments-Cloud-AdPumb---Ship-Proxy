package com.adpumb.offshoreproxy.application.model;



public class ResponseWrapper {
    private String responseBody;
    private int statusCode;

    // Constructor
    public ResponseWrapper(String responseBody, int statusCode) {
        this.responseBody = responseBody;
        this.statusCode = statusCode;
    }

    // Getters
    public String getResponseBody() { return responseBody; }
    public int getStatusCode() { return statusCode; }
}
