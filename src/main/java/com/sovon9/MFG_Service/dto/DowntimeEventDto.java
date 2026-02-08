package com.sovon9.MFG_Service.dto;

import java.time.LocalDateTime;

public record DowntimeEventDto(
        String id,
        String runId,
        String lineId,
        String machineId,
        String reason,
        String category,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Integer duration
) {}
