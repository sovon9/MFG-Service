package com.sovon9.MFG_Service.controller;

import com.sovon9.MFG_Service.dto.DowntimeEventDto;
import com.sovon9.MFG_Service.dto.MachineDto;
import com.sovon9.MFG_Service.dto.ProductionRunDto;
import com.sovon9.MFG_Service.dto.QualityEventDto;
import com.sovon9.MFG_Service.service.ProductionDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlantDataController {

    private ProductionDataService productionDataService;

    public PlantDataController(ProductionDataService productionDataService) {
        this.productionDataService = productionDataService;
    }

    /**
     * Fetches production runs for a given production line.
     * @param lineId
     * @param from
     * @param to
     * @return
     */
    @GetMapping("/production/run/line/{lineId}")
    public ResponseEntity<?> productionRun(@PathVariable String lineId, @RequestParam(value = "from", required = false) LocalDateTime from, @RequestParam(value = "to", required = false) LocalDateTime to)
    {
        List<ProductionRunDto> runs = productionDataService.getProductionRuns(lineId, from, to);

        if(runs.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(runs);
    }

    /**
     * Retrieves downtime events for a specific production run.
     * @param run_id
     * @return
     */
    @GetMapping("/production/run/{run_id}/downtime")
    public ResponseEntity<?> downtimeDataForProductionRun(@PathVariable String run_id)
    {
        if(null==run_id)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No runId provided");
        }
        List<DowntimeEventDto> downtimeEvents = productionDataService.getDowntimeEvents(run_id);
        if(downtimeEvents.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(downtimeEvents);
    }

    /**
     * Retrieves quality events associated with a specific production run.
     * @param run_id
     * @return
     */
    @GetMapping("/production/run/{run_id}/quality")
    public ResponseEntity<?> qualityEvent(@PathVariable String run_id)
    {
        if(null==run_id)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No runId provided");
        }
        List<QualityEventDto> qualityEvents = productionDataService.getQualityEvents(run_id);
        if(qualityEvents.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(qualityEvents);
    }

    /**
     * Retrieves detailed information about a machine.
     * @param id
     * @return
     */
    @GetMapping("/machine/{id}")
    public ResponseEntity<?> machineData(@PathVariable String id)
    {
        if(null==id)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No machine id provided");
        }
        Optional<MachineDto> machineOptional = productionDataService.getMachine(id);
        if(machineOptional.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(machineOptional.get());
    }

}
