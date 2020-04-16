package com.xyz.modules.biz.service.actual.repo;

import com.xyz.modules.biz.service.actual.entity.Floatpeople;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
}