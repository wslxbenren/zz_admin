package com.xyz.modules.system.repository;

import com.xyz.modules.system.domain.T3commandcenterIndustry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-02-02
*/
public interface T3commandcenterIndustryRepository extends JpaRepository<T3commandcenterIndustry, String>, JpaSpecificationExecutor {
}