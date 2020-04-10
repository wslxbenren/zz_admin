package com.xyz.modules.biz.repository;

import com.xyz.modules.biz.domain.Suspectinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
public interface SuspectinfoRepository extends JpaRepository<Suspectinfo, String>, JpaSpecificationExecutor {
}