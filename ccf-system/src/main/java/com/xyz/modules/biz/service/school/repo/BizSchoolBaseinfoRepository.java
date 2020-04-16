package com.xyz.modules.biz.service.school.repo;

import com.xyz.modules.biz.service.school.entity.BizSchoolBaseinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
public interface BizSchoolBaseinfoRepository extends JpaRepository<BizSchoolBaseinfo, String>, JpaSpecificationExecutor {
}