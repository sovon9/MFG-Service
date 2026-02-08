package com.sovon9.MFG_Service.service;

import com.sovon9.MFG_Service.dto.*;
import com.sovon9.MFG_Service.entity.*;
import com.sovon9.MFG_Service.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductionDataService {

    private ProductionLineRepository productionLineRepository;
    private ProductionRunRepository productionRunRepository;
    private DowntimeEventRepository downtimeEventRepository;
    private QualityEventRepository qualityEventRepository;
    private MachineRepository machineRepository;

    public ProductionDataService(
            ProductionRunRepository productionRunRepository,
            DowntimeEventRepository downtimeEventRepository,
            QualityEventRepository qualityEventRepository,
            MachineRepository machineRepository) {

        this.productionRunRepository = productionRunRepository;
        this.downtimeEventRepository = downtimeEventRepository;
        this.qualityEventRepository = qualityEventRepository;
        this.machineRepository = machineRepository;
    }

    /**
     * Fetch production runs for a line.
     * If from/to is provided, apply time window filtering.
     */
    public List<ProductionRunDto> getProductionRuns(
            String lineId,
            LocalDateTime from,
            LocalDateTime to) {

        List<ProductionRun> runs;

        if (from != null && to != null) {
            runs =  productionRunRepository
                    .findByIdFromAndTo(lineId, from, to);
        }
        else {
            runs = productionRunRepository
                    .findByLineId(lineId)
                    .orElse(Collections.emptyList());
        }
        return runs.stream()
                .map(this::toProductionRunDto)
                .toList();
    }

    /**
     * Fetch downtime events for a production run.
     */
    public List<DowntimeEventDto> getDowntimeEvents(String runId) {
        return downtimeEventRepository.findByRunId(runId).stream()
                .map(this::toDowntimeEventDto)
                .toList();
    }

    /**
     * Fetch quality events for a production run.
     */
    public List<QualityEventDto> getQualityEvents(String runId) {
        return qualityEventRepository.findByRunId(runId)
                .stream()
                .map(this::toQualityEventDto)
                .toList();
    }

    /**
     * Fetch machine details by ID.
     */
    public Optional<MachineDto> getMachine(String machineId) {
        return machineRepository.findById(machineId)
                .map(this::toMachineDto);
    }

    private ProductionRunDto toProductionRunDto(ProductionRun run) {
        return new ProductionRunDto(
                run.getId(),
                run.getLineId(),
                run.getProductId(),
                run.getShiftId(),
                run.getStartTime(),
                run.getEndTime(),
                run.getPlannedQuantity(),
                run.getActualQuantity()
        );
    }

    private DowntimeEventDto toDowntimeEventDto(DowntimeEvent event) {
        return new DowntimeEventDto(
                event.getId(),
                event.getRunId(),
                event.getLineId(),
                event.getMachineId(),
                event.getReason(),
                event.getCategory(),
                event.getStartTime(),
                event.getEndTime(),
                event.getDuration()
        );
    }

    private QualityEventDto toQualityEventDto(QualityEvent event) {
        return new QualityEventDto(
                event.getId(),
                event.getRunId(),
                event.getDefectType(),
                event.getQuantity(),
                event.getMachineId()
        );
    }

    private MachineDto toMachineDto(Machine machine) {
        return new MachineDto(
                machine.getId(),
                machine.getLineId(),
                machine.getName(),
                machine.getType()
        );
    }

    private ProductionLineDto toProductionLineDto(ProductionLine line) {
        return new ProductionLineDto(
                line.getId(),
                line.getPlant_id(),
                line.getName(),
                line.getStatus()
        );
    }

}
