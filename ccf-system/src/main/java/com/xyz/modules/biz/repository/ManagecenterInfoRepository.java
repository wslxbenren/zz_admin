package com.xyz.modules.biz.repository;

import com.xyz.modules.biz.domain.ManagecenterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author lx
* @date 2020-04-07
*/
public interface ManagecenterInfoRepository extends JpaRepository<ManagecenterInfo, String>, JpaSpecificationExecutor {
}