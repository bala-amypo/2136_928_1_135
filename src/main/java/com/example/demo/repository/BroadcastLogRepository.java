package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.BroadcastLog;

public interface BroadcastLogRepository extends JpaRepository<BroadcastLog, Integer> {
}
