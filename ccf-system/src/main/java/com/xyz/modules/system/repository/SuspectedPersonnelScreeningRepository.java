package com.xyz.modules.system.repository;

import com.xyz.modules.system.domain.SuspectedPersonnelScreening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-02-07
*/
public interface SuspectedPersonnelScreeningRepository extends JpaRepository<SuspectedPersonnelScreening, String>, JpaSpecificationExecutor {
}