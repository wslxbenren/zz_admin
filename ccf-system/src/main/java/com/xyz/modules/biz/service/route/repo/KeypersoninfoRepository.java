package com.xyz.modules.biz.service.route.repo;

import com.xyz.modules.biz.service.route.entity.Keypersoninfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
public interface KeypersoninfoRepository extends JpaRepository<Keypersoninfo, String>, JpaSpecificationExecutor {
}