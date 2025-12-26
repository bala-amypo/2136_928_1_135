package com.example.demo.repository;

import com.example.demo.entity.BroadcastLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBroadcastLogRepository extends JpaRepository<BroadcastLog, Long>, BroadcastLogRepository {
    // No changes needed here, it inherits the correct save() now
}