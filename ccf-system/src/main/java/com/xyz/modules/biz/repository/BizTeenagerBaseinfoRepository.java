package com.xyz.modules.biz.repository;

import com.xyz.modules.biz.domain.BizTeenagerBaseinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
public interface BizTeenagerBaseinfoRepository extends JpaRepository<BizTeenagerBaseinfo, String>, JpaSpecificationExecutor {

    /**
     * findByIdentityNum
     * @param identity_num
     * @return
     */
    BizTeenagerBaseinfo findByIdentityNum(String identity_num);
}