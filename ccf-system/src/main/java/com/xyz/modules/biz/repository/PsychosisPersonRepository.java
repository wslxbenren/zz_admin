package com.xyz.modules.biz.repository;

import com.xyz.modules.biz.domain.PsychosisPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
public interface PsychosisPersonRepository extends JpaRepository<PsychosisPerson, String>, JpaSpecificationExecutor {

    /**
     * findByIdentityNum
     * @param identity_num
     * @return
     */
    PsychosisPerson findByIdentityNum(String identity_num);
}