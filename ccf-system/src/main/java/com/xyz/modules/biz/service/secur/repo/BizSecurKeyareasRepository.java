package com.xyz.modules.biz.service.secur.repo;

import com.xyz.modules.biz.service.secur.entity.BizSecurKeyareas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
public interface BizSecurKeyareasRepository extends JpaRepository<BizSecurKeyareas, String>, JpaSpecificationExecutor {
}