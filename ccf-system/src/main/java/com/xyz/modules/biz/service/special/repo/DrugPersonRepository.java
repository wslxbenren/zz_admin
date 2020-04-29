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

    @Query(value = "SELECT COUNT(1) FROM biz_special_drug_person t WHERE t.identity_num = ?1",nativeQuery = true)
    Long validateIdentityNum(String identityNum);

    @Query(value = "SELECT COUNT(1) FROM biz_special_drug_person t WHERE t.drug_id = ?1 and t.identity_num = ?2  ",nativeQuery = true)
    Long validateIdentityNumById(String id,String identityNum);
}