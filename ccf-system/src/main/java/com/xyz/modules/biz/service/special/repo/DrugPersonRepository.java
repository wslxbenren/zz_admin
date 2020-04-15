package com.xyz.modules.biz.service.special.repo;

import com.xyz.modules.biz.service.special.entity.DrugPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
public interface DrugPersonRepository extends JpaRepository<DrugPerson, String>, JpaSpecificationExecutor {

    /**
     * findByIdentityNum
     * @param identity_num
     * @return
     */
    DrugPerson findByIdentityNum(String identity_num);
}