package com.sovon9.MFG_Service.repository;

import com.sovon9.MFG_Service.entity.DowntimeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DowntimeEventRepository extends JpaRepository<DowntimeEvent, String> {
    List<DowntimeEvent> findByRunId(String runId);
}
