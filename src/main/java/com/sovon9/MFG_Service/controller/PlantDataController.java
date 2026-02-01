package com.sovon9.MFG_Service.controller;

import com.sovon9.MFG_Service.entity.*;
import com.sovon9.MFG_Service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlantDataController {

    @Autowired
    private ProductionLineRepository productionLineRepository;
    @Autowired
    private ProductionRunRepository productionRunRepository;
    @Autowired
    private DowntimeEventRepository downtimeEventRepository;
    @Autowired
    private QualityEventRepository qualityEventRepository;
    @Autowired
    private MachineRepository machineRepository;

    @GetMapping("/production/run/line/{lineId}")
    public List<ProductionRun> productionRun(@PathVariable String lineId, @RequestParam(value = "from", required = false) LocalDateTime from, @RequestParam(value = "to", required = false) LocalDateTime to)
    {
        if(null!=from && null!=to)
        {
            return productionRunRepository.findByIdFromAndTo(lineId, from, to);
        }
        return productionRunRepository.findByLineId(lineId).orElse(null);
    }

    @GetMapping("/production/run/{run_id}/downtime")
    public ResponseEntity<?> downtimeDataForProductionRun(@PathVariable String run_id)
    {
        if(null==run_id)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No runId provided");
        }
        List<DowntimeEvent> downtimeEvents = downtimeEventRepository.findByRunId(run_id);
        if(downtimeEvents.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(downtimeEvents);
    }

    @GetMapping("/production/run/{run_id}/quality")
    public ResponseEntity<?> qualityEvent(@PathVariable String run_id)
    {
        if(null==run_id)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No runId provided");
        }
        List<QualityEvent> qualityEvents = qualityEventRepository.findByRunId(run_id);
        if(qualityEvents.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(qualityEvents);
    }

    @GetMapping("/machine/{id}")
    public ResponseEntity<?> machineData(@PathVariable String id)
    {
        if(null==id)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No machine id provided");
        }
        Optional<Machine> machineOptional = machineRepository.findById(id);
        if(machineOptional.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(machineOptional.get());
    }

}
