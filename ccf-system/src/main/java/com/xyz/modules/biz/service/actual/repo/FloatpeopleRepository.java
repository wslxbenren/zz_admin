package com.xyz.modules.biz.service.actual.repo;

import com.xyz.modules.biz.service.actual.entity.Floatpeople;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
* @author lx
* @date 2020-04-09
*/
public interface FloatpeopleRepository extends JpaRepository<Floatpeople, String>, JpaSpecificationExecutor {

    /**
     * findByIdentityNum
     * @param identity_num
     * @return
     */
    Floatpeople findByIdentityNum(String identity_num);

    @Query(value = "SELECT t.identity_num FROM biz_actual_floatpeople t WHERE t.identity_num = ?1",nativeQuery = true)
    String validateIdentityNum(String identityNum);
}