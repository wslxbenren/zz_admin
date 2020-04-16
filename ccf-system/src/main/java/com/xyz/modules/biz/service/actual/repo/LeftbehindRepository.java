package com.xyz.modules.biz.service.actual.repo;

import com.xyz.modules.biz.service.actual.entity.Leftbehind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dadovicn
* @date 2020-04-08
*/
public interface LeftbehindRepository extends JpaRepository<Leftbehind, String>, JpaSpecificationExecutor {

    /**
     * findByIdentityNum
     * @param identity_num
     * @return
     */
    Leftbehind findByIdentityNum(String identity_num);
}