package com.xyz.modules.biz.service.special.repo;

import com.xyz.modules.biz.service.special.entity.DrugPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

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

    @Query(value = "SELECT t.identity_num FROM biz_special_drug_person t WHERE t.identity_num = ?1",nativeQuery = true)
    String validateIdentityNum(String identityNum);
}