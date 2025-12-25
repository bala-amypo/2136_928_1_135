package com.example.demo.service;

import com.example.demo.entity.BroadcastLog;
import java.util.List;

public interface BroadcastService {

    // Trigger broadcast for an EventUpdate
    void triggerBroadcast(Long updateId);

    // Fetch broadcast logs for an EventUpdate
    List<BroadcastLog> getLogsForUpdate(Long updateId);
}
