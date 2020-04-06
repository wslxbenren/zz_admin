package com.xyz.modules.system.repository;

import com.xyz.modules.system.domain.DictDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
public interface DictDetailRepository extends JpaRepository<DictDetail, Long>, JpaSpecificationExecutor {
    /**
     *
     * @param dictId
     * @return
     */
    List<DictDetail> findByDictId(long dictId);
}