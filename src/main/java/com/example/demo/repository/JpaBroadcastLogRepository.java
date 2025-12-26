package com.example.demo.repository;

import com.example.demo.entity.BroadcastLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaBroadcastLogRepository extends JpaRepository<BroadcastLog, Long>, BroadcastLogRepository {
    
    @Override
    List<BroadcastLog> findByEventUpdateId(Long updateId);
}