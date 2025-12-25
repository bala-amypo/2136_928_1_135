package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.BroadcastLog;

public interface BroadcastService {

    // EXISTING (keep)
    BroadcastLog save(BroadcastLog log);
    List<BroadcastLog> getAll();

    // 🔹 ADD THESE (required by controller)
    void broadcastUpdate(Long updateId);

    List<BroadcastLog> getLogsForUpdate(Long updateId);

    List<BroadcastLog> getAllLogs();
}
