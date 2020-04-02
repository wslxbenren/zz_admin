package com.xyz.modules.system.service;

import com.xyz.modules.system.domain.SuspectedPersonnelScreening;
import com.xyz.modules.system.service.dto.SuspectedPersonnelScreeningDTO;
import com.xyz.modules.system.service.dto.SuspectedPersonnelScreeningQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author dadovicn
* @date 2020-02-07
*/
//@CacheConfig(cacheNames = "suspectedPersonnelScreening")
public interface SuspectedPersonnelScreeningService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(SuspectedPersonnelScreeningQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SuspectedPersonnelScreeningQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    SuspectedPersonnelScreeningDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    SuspectedPersonnelScreeningDTO create(SuspectedPersonnelScreening resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(SuspectedPersonnelScreening resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(String id);
}