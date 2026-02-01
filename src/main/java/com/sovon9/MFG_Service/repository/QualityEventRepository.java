package com.sovon9.MFG_Service.repository;

import com.sovon9.MFG_Service.entity.QualityEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityEventRepository extends JpaRepository<QualityEvent, String> {
    List<QualityEvent> findByRunId(String runId);
}
