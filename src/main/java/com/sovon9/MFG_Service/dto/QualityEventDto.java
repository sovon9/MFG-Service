package com.sovon9.MFG_Service.dto;

public record QualityEventDto(
        String id,
        String runId,
        String defectType,
        Integer quantity,
        String machineId
) {}
