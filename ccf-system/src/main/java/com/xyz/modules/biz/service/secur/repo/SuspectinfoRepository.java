package com.xyz.modules.biz.service.secur.repo;

import com.xyz.modules.biz.service.secur.entity.Suspectinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
public interface SuspectinfoRepository extends JpaRepository<Suspectinfo, String>, JpaSpecificationExecutor {
}