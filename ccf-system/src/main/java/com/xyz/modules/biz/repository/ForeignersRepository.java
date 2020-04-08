package com.xyz.modules.biz.repository;

import com.xyz.modules.biz.domain.Foreigners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-04-08
*/
public interface ForeignersRepository extends JpaRepository<Foreigners, String>, JpaSpecificationExecutor {
}