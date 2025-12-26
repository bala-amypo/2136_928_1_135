package com.example.demo.repository;

import com.example.demo.entity.BroadcastLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBroadcastLogRepository extends JpaRepository<BroadcastLog, Long>, BroadcastLogRepository {
    // Inherits save() from JpaRepository and findByEventUpdateId from the bridge
}