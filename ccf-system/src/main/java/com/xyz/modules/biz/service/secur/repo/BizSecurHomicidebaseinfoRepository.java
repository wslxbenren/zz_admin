package com.xyz.modules.biz.service.secur.repo;

import com.xyz.modules.biz.service.secur.entity.BizSecurHomicidebaseinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
public interface BizSecurHomicidebaseinfoRepository extends JpaRepository<BizSecurHomicidebaseinfo, String>, JpaSpecificationExecutor {

    /**
     * findByCaseCode
     * @param case_code
     * @return
     */
    BizSecurHomicidebaseinfo findByCaseCode(String case_code);
}