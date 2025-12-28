


package com.example.demo.repository;

import com.example.demo.entity.BroadcastLog;
import java.util.List;

public interface BroadcastLogRepository {
    List<BroadcastLog> findByEventUpdateId(Long updateId);
    
   
    <S extends BroadcastLog> S save(S entity);
}