package com.xyz.modules.biz.service.teenager.repo;

import com.xyz.modules.biz.service.teenager.entity.BizTeenagerBaseinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

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

    @Query(value = "SELECT  COUNT(1) FROM biz_teenager_baseinfo t WHERE t.identity_num = ?1",nativeQuery = true)
    Long validateIdentityNum(String identityNum);

    @Query(value = "SELECT COUNT(1) FROM biz_teenager_baseinfo t WHERE t.teen_id = ?1 and t.identity_num = ?2  ",nativeQuery = true)
    Long validateIdentityNumById(String id,String identityNum);
}