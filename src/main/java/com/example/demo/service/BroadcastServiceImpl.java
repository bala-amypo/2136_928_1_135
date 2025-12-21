package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BroadcastLog;
import com.example.demo.repository.BroadcastLogRepository;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final BroadcastLogRepository broadcastLogRepository;

   
    public BroadcastServiceImpl(BroadcastLogRepository broadcastLogRepository) {
        this.broadcastLogRepository = broadcastLogRepository;
    }

    @Override
    public BroadcastLog save(BroadcastLog log) {
        
        return broadcastLogRepository.save(log);
    }

    @Override
    public List<BroadcastLog> getAll() {
        return broadcastLogRepository.findAll();
    }
}
