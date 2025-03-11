package com.adpumb.shipproxy.application.service;



import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adpumb.shipproxy.application.model.RequestWrapper;

import jakarta.annotation.PostConstruct;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ProxyService {

    private final BlockingQueue<RequestWrapper> requestQueue = new LinkedBlockingQueue<>();
    private final RestTemplate restTemplate = new RestTemplate();
    private final String OFFSHORE_PROXY_URL = "http://localhost:8081/proxy";

    @PostConstruct
    public void startProcessingQueue() {
        new Thread(() -> {
            while (true) {
                try {
                    RequestWrapper request = requestQueue.take();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    headers.setAll(request.getHeaders());

                    HttpEntity<String> entity = new HttpEntity<>(request.getBody(), headers);
                    ResponseEntity<String> response = restTemplate.exchange(OFFSHORE_PROXY_URL,
                          HttpMethod.valueOf(request.getMethod()), entity, String.class);
//                              HttpMethod.POST, entity, String.class);


                    System.out.println("Processed Request: " + request.getUrl() + " | Response: " + response.getStatusCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public ResponseEntity<String> addRequestToQueue(RequestWrapper request) throws InterruptedException {
        requestQueue.put(request);
        return ResponseEntity.ok("Request added to queue and will be processed sequentially.");
    }
}



//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.adpumb.shipproxy.application.model.RequestWrapper;
//
//@Service
//public class ProxyService {
//    
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String OFFSHORE_PROXY_URL = "http://localhost:8081/proxy";  // Ensure correct URL
//
//    public ResponseEntity<String> forwardRequest(RequestWrapper request) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);  // Ensures JSON format
//        headers.setAll(request.getHeaders());  // Include additional headers
//
//        // Ensure request body is correctly wrapped
//        HttpEntity<RequestWrapper> entity = new HttpEntity<>(request, headers);
//
//        // Send as POST request
//        ResponseEntity<String> response = restTemplate.exchange(OFFSHORE_PROXY_URL, 
//                                      HttpMethod.POST, entity, String.class);
//        return response;
//    }
//}
//
