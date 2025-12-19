package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BroadcastLog;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    List<BroadcastLog> logs = new ArrayList<>();

    public BroadcastLog save(BroadcastLog log) {
        logs.add(log);
        return log;
    }

    public List<BroadcastLog> getAll() {
        return logs;
    }
}
