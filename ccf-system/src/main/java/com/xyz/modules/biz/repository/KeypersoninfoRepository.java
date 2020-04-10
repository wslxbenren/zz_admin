package com.xyz.modules.biz.repository;

import com.xyz.modules.biz.domain.Keypersoninfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
public interface KeypersoninfoRepository extends JpaRepository<Keypersoninfo, String>, JpaSpecificationExecutor {
}