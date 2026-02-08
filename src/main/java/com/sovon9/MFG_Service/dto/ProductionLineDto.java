package com.sovon9.MFG_Service.dto;

public record ProductionLineDto(
        String id,
        String plantId,
        String name,
        String status
) {}
