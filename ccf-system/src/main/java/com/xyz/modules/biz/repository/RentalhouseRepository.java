package com.xyz.modules.biz.repository;

import com.xyz.modules.biz.domain.Rentalhouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author lx
* @date 2020-04-09
*/
public interface RentalhouseRepository extends JpaRepository<Rentalhouse, String>, JpaSpecificationExecutor {
}