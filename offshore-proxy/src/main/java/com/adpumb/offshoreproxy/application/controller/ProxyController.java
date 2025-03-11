package com.adpumb.offshoreproxy.application.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adpumb.offshoreproxy.application.model.RequestWrapper;
import com.adpumb.offshoreproxy.application.service.ProxyService;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    private final ProxyService proxyService;

    public ProxyController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @PostMapping
    public ResponseEntity<?> forwardRequest(@RequestBody RequestWrapper request) {
        return proxyService.forwardRequest(request);
    }
}
