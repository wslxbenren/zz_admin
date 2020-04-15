package com.xyz.modules.biz.service.org.repo;

import com.xyz.modules.biz.service.org.entity.ManagecenterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author lx
* @date 2020-04-07
*/
public interface ManagecenterInfoRepository extends JpaRepository<ManagecenterInfo, String>, JpaSpecificationExecutor {
}