package com.sovon9.MFG_Service.repository;

import com.sovon9.MFG_Service.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, String> {
}
