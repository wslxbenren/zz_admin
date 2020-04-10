package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.PsychosisPerson;
import com.xyz.modules.biz.service.dto.PsychosisPersonDTO;
import com.xyz.modules.biz.service.dto.PsychosisPersonQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "PsychosisPerson")
public interface PsychosisPersonService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(PsychosisPersonQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PsychosisPersonQueryCriteria criteria);

    /**
     * findById
     * @param psychosisId
     * @return
     */
    //@Cacheable(key = "#p0")
    PsychosisPersonDTO findById(String psychosisId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    PsychosisPersonDTO create(PsychosisPerson resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(PsychosisPerson resources);

    /**
     * delete
     * @param psychosisId
     */
    //@CacheEvict(allEntries = true)
    void delete(String psychosisId);
}