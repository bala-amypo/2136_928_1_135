package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.BroadcastLog;

import java.util.List;

public interface BroadcastLogRepository extends JpaRepository<BroadcastLog, Long> {

    List<BroadcastLog> findBySubscriber_Id(Long subscriberId);

    List<BroadcastLog> findByEventUpdate_Id(Long eventUpdateId);
}
