package com.adpumb.shipproxy.application.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adpumb.shipproxy.application.model.RequestWrapper;
import com.adpumb.shipproxy.application.service.ProxyService;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    private final ProxyService proxyService;

    public ProxyController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @PostMapping
    public ResponseEntity<String> handleRequest(@RequestBody RequestWrapper request) throws InterruptedException {
        return proxyService.addRequestToQueue(request);
    }
}
