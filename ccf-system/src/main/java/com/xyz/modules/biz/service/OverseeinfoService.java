package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.Overseeinfo;
import com.xyz.modules.biz.service.dto.OverseeinfoDTO;
import com.xyz.modules.biz.service.dto.OverseeinfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Overseeinfo")
public interface OverseeinfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(OverseeinfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(OverseeinfoQueryCriteria criteria);

    /**
     * findById
     * @param overseeId
     * @return
     */
    //@Cacheable(key = "#p0")
    OverseeinfoDTO findById(String overseeId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    OverseeinfoDTO create(Overseeinfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Overseeinfo resources);

    /**
     * delete
     * @param overseeId
     */
    //@CacheEvict(allEntries = true)
    void delete(String overseeId);
}