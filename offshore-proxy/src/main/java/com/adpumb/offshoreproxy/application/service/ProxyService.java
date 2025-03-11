package com.adpumb.offshoreproxy.application.service;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adpumb.offshoreproxy.application.model.RequestWrapper;
import com.adpumb.offshoreproxy.application.model.ResponseWrapper;

@Service
public class ProxyService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<ResponseWrapper> forwardRequest(RequestWrapper request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(request.getHeaders());

        HttpEntity<String> entity = new HttpEntity<>(request.getBody(), headers);
        ResponseEntity<String> response = restTemplate.exchange(request.getUrl(), 
                        HttpMethod.valueOf(request.getMethod()), entity, String.class);

        return ResponseEntity.ok(new ResponseWrapper(response.getBody(), response.getStatusCodeValue()));
    }
}
