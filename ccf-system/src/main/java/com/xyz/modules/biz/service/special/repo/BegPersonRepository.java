package com.xyz.modules.biz.service.special.repo;

import com.xyz.modules.biz.service.special.entity.BegPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
public interface BegPersonRepository extends JpaRepository<BegPerson, String>, JpaSpecificationExecutor {

    /**
     * findByIdentityNum
     * @param identity_num
     * @return
     */
    BegPerson findByIdentityNum(String identity_num);
}