
package com.example.demo.controller;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/broadcast")
public class BroadcastController {
    private final BroadcastService broadcastService;

    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @PostMapping("/{updateId}")
    public ResponseEntity<Void> triggerBroadcast(@PathVariable Long updateId) {
        broadcastService.broadcastUpdate(updateId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/logs/{updateId}")
    public ResponseEntity<List<BroadcastLog>> getLogsForUpdate(@PathVariable Long updateId) {
        return ResponseEntity.ok(broadcastService.getLogsForUpdate(updateId));
    }
}