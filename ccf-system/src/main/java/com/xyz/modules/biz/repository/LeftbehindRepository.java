package com.xyz.modules.biz.repository;

import com.xyz.modules.biz.domain.Leftbehind;
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