package com.xyz.modules.biz.service.org.repo;

import com.xyz.modules.biz.service.org.entity.MajorcaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-04-05
*/
public interface MajorcaseInfoRepository extends JpaRepository<MajorcaseInfo, String>, JpaSpecificationExecutor {
}