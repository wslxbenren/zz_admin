package com.xyz.modules.biz.service.org.repo;

import com.xyz.modules.biz.service.org.entity.ManageleadresponsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-04-05
*/
public interface ManageleadresponsInfoRepository extends JpaRepository<ManageleadresponsInfo, String>, JpaSpecificationExecutor {
}