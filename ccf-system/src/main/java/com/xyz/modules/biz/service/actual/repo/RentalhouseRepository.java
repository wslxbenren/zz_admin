package com.xyz.modules.biz.service.actual.repo;

import com.xyz.modules.biz.service.actual.entity.Rentalhouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author lx
* @date 2020-04-09
*/
public interface RentalhouseRepository extends JpaRepository<Rentalhouse, String>, JpaSpecificationExecutor {
    Rentalhouse findByCardNo(String cardNo);
}