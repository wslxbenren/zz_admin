package com.xyz.modules.biz.service.secur.repo;

import com.xyz.modules.biz.service.secur.entity.Victiminfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
public interface VictiminfoRepository extends JpaRepository<Victiminfo, String>, JpaSpecificationExecutor {
}