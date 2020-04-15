package com.xyz.modules.biz.service.org.repo;

import com.xyz.modules.biz.service.org.entity.BuildheadInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author lx
* @date 2020-04-06
*/
public interface BuildheadInfoRepository extends JpaRepository<BuildheadInfo, String>, JpaSpecificationExecutor {

}