package com.sovon9.MFG_Service.dto;

import java.time.LocalDateTime;

public record ProductionRunDto(
        String id,
        String lineId,
        String productId,
        String shiftId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Integer plannedQuantity,
        Integer actualQuantity
) {}
