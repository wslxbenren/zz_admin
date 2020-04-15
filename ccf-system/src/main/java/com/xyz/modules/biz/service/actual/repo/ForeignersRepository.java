package com.xyz.modules.biz.service.actual.repo;

import com.xyz.modules.biz.service.actual.entity.Foreigners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-04-08
*/
public interface ForeignersRepository extends JpaRepository<Foreigners, String>, JpaSpecificationExecutor {
}