package com.xyz.modules.system.service;

import com.xyz.modules.system.domain.SuspectedPersonnelInfo;
import com.xyz.modules.system.service.dto.SuspectedPersonnelInfoDTO;
import com.xyz.modules.system.service.dto.SuspectedPersonnelInfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author dadovicn
* @date 2020-02-07
*/
//@CacheConfig(cacheNames = "suspectedPersonnelInfo")
public interface SuspectedPersonnelInfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(SuspectedPersonnelInfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SuspectedPersonnelInfoQueryCriteria criteria);

    /**
     * findById
     * @param pId
     * @return
     */
    //@Cacheable(key = "#p0")
    SuspectedPersonnelInfoDTO findById(String pId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    SuspectedPersonnelInfoDTO create(SuspectedPersonnelInfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(SuspectedPersonnelInfo resources);

    /**
     * delete
     * @param pId
     */
    //@CacheEvict(allEntries = true)
    void delete(String pId);
}