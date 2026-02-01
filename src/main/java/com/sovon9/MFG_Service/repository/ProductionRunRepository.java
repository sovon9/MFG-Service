package com.sovon9.MFG_Service.repository;

import com.sovon9.MFG_Service.entity.ProductionRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductionRunRepository extends JpaRepository<ProductionRun, String> {

    @Query(nativeQuery = true, value = "select * from production_run where line_id=:id and start_time<:to and end_time>:from")
    List<ProductionRun> findByIdFromAndTo(@Param("id") String id, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    Optional<List<ProductionRun>> findByLineId(String lineId);
}
