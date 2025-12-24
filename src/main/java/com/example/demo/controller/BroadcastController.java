package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastService;

@RestController
@RequestMapping("/api/broadcasts")
public class BroadcastController {

    private final BroadcastService service;

    public BroadcastController(BroadcastService service) {
        this.service = service;
    }

    // -------------------- TRIGGER BROADCAST FOR AN EVENT UPDATE --------------------
    @PostMapping("/trigger/{updateId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> triggerBroadcast(@PathVariable Long updateId) {
        service.broadcastUpdate(updateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // -------------------- GET BROADCAST LOGS FOR SPECIFIC EVENT UPDATE --------------------
    @GetMapping("/logs/{updateId}")
    @PreAuthorize("hasRole('PUBLISHER') or hasRole('ADMIN') or hasRole('SUBSCRIBER')")
    public ResponseEntity<List<BroadcastLog>> getLogsForUpdate(@PathVariable Long updateId) {
        List<BroadcastLog> logs = service.getLogsForUpdate(updateId);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    // -------------------- GET ALL BROADCAST LOGS --------------------
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BroadcastLog>> getAllLogs() {
        List<BroadcastLog> logs = service.getAllLogs();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }
}
