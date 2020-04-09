package com.xyz.modules.biz.repository;

import com.xyz.modules.biz.domain.Registpeople;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
}