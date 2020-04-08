package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.Rentalhouse;
import com.xyz.modules.biz.service.dto.RentalhouseDTO;
import com.xyz.modules.biz.service.dto.RentalhouseQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author dadovicn
* @date 2020-04-08
*/
//@CacheConfig(cacheNames = "Rentalhouse")
public interface RentalhouseService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(RentalhouseQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(RentalhouseQueryCriteria criteria);

    /**
     * findById
     * @param rentId
     * @return
     */
    //@Cacheable(key = "#p0")
    RentalhouseDTO findById(String rentId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    RentalhouseDTO create(Rentalhouse resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Rentalhouse resources);

    /**
     * delete
     * @param rentId
     */
    //@CacheEvict(allEntries = true)
    void delete(String rentId);
}