package com.adrianmensing.controller;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseCommons {
    public static final ResponseEntity<?> STATUS_OK = statusOk();

    private static ResponseEntity<?> statusOk() {
        Map<String, String> body = new HashMap<>();
        body.put("status", "ok");
        return ResponseEntity.ok(body);
    }
}
