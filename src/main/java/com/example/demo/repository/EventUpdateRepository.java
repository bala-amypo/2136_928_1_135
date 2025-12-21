package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.EventUpdate;

import java.util.List;

public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long> {

    List<EventUpdate> findByEvent_Id(Long eventId);
}
