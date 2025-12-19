package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.BroadcastLog;

public interface BroadcastService {
    BroadcastLog save(BroadcastLog log);
    List<BroadcastLog> getAll();
}
