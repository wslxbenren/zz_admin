package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.Foreigners;
import com.xyz.modules.biz.service.dto.ForeignersDTO;
import com.xyz.modules.biz.service.dto.ForeignersQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author dadovicn
* @date 2020-04-08
*/
//@CacheConfig(cacheNames = "Foreigners")
public interface ForeignersService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(ForeignersQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ForeignersQueryCriteria criteria);

    /**
     * findById
     * @param foreId
     * @return
     */
    //@Cacheable(key = "#p0")
    ForeignersDTO findById(String foreId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    ForeignersDTO create(Foreigners resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Foreigners resources);

    /**
     * delete
     * @param foreId
     */
    //@CacheEvict(allEntries = true)
    void delete(String foreId);
}