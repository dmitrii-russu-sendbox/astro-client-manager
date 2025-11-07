package com.example.customers.controller.pingcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    private volatile long lastPing = System.currentTimeMillis();

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        lastPing = System.currentTimeMillis();
        return ResponseEntity.ok("pong");
    }

    public long getLastPing() { return lastPing;}
}

