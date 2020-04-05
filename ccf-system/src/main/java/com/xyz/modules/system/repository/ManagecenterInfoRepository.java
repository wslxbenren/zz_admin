package com.xyz.modules.system.repository;

import com.xyz.modules.system.domain.ManagecenterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-04-05
*/
public interface ManagecenterInfoRepository extends JpaRepository<ManagecenterInfo, String>, JpaSpecificationExecutor {
}