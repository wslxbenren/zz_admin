package com.xyz.modules.biz.repository;

import com.xyz.modules.biz.domain.BuildheadInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-04-05
*/
public interface BuildheadInfoRepository extends JpaRepository<BuildheadInfo, String>, JpaSpecificationExecutor {
}