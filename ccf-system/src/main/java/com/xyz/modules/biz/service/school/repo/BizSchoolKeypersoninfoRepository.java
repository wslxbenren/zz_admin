package com.xyz.modules.biz.service.school.repo;

import com.xyz.modules.biz.service.school.entity.BizSchoolKeypersoninfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
public interface BizSchoolKeypersoninfoRepository extends JpaRepository<BizSchoolKeypersoninfo, String>, JpaSpecificationExecutor {
}