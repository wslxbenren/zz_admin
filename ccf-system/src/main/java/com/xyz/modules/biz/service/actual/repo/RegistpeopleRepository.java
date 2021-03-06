package com.xyz.modules.biz.service.actual.repo;

import com.xyz.modules.biz.service.actual.entity.Registpeople;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
* @author lx
* @date 2020-04-08
*/
public interface RegistpeopleRepository extends JpaRepository<Registpeople, String>, JpaSpecificationExecutor {

    /**
     * findByIdentityNum
     * @param identity_num
     * @return
     */
    Registpeople findByIdentityNum(String identity_num);


    @Query(value = "SELECT COUNT(1) FROM biz_actual_registpeople t WHERE t.identity_num = ?1",nativeQuery = true)
    Long validateIdentityNum(String identityNum);


    @Query(value = "SELECT COUNT(1) FROM biz_actual_registpeople t WHERE t.regis_id = ?1 and t.identity_num = ?2  ",nativeQuery = true)
    Long validateIdentityNumById(String id,String identityNum);
}