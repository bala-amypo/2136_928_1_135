package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BroadcastLog;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    List<BroadcastLog> logs = new ArrayList<>();

    @Override
    public BroadcastLog save(BroadcastLog log) {
        logs.add(log);
        return log;
    }

    @Override
    public List<BroadcastLog> getAll() {
        return logs;
    }
}
