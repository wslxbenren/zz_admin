package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.CorrectPerson;
import com.xyz.modules.biz.service.dto.CorrectPersonDTO;
import com.xyz.modules.biz.service.dto.CorrectPersonQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "CorrectPerson")
public interface CorrectPersonService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(CorrectPersonQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CorrectPersonQueryCriteria criteria);

    /**
     * findById
     * @param correctId
     * @return
     */
    //@Cacheable(key = "#p0")
    CorrectPersonDTO findById(String correctId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    CorrectPersonDTO create(CorrectPerson resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(CorrectPerson resources);

    /**
     * delete
     * @param correctId
     */
    //@CacheEvict(allEntries = true)
    void delete(String correctId);
}