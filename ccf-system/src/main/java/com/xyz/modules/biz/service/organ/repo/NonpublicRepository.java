package com.xyz.modules.biz.service.organ.repo;

import com.xyz.modules.biz.service.organ.entity.Nonpublic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
public interface NonpublicRepository extends JpaRepository<Nonpublic, String>, JpaSpecificationExecutor {
    @Query(value = "SELECT t.credit_code FROM biz_organ_nonpublic t WHERE t.credit_code = ?1",nativeQuery = true)
    String verifyCreditCode(String creditCode);
}