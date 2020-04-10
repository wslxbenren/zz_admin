package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.Caseinfo;
import com.xyz.modules.biz.service.dto.CaseinfoDTO;
import com.xyz.modules.biz.service.dto.CaseinfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Caseinfo")
public interface CaseinfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(CaseinfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CaseinfoQueryCriteria criteria);

    /**
     * findById
     * @param caseId
     * @return
     */
    //@Cacheable(key = "#p0")
    CaseinfoDTO findById(String caseId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    CaseinfoDTO create(Caseinfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Caseinfo resources);

    /**
     * delete
     * @param caseId
     */
    //@CacheEvict(allEntries = true)
    void delete(String caseId);
}