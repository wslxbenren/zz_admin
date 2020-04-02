package com.xyz.modules.system.repository;

import com.xyz.modules.system.domain.T2socialpatrolWorkingday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-02-02
*/
public interface T2socialpatrolWorkingdayRepository extends JpaRepository<T2socialpatrolWorkingday, String>, JpaSpecificationExecutor {
}