package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.BizSchoolKeypersoninfo;
import com.xyz.modules.biz.service.dto.BizSchoolKeypersoninfoDTO;
import com.xyz.modules.biz.service.dto.BizSchoolKeypersoninfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "bizSchoolKeypersoninfo")
public interface BizSchoolKeypersoninfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(BizSchoolKeypersoninfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(BizSchoolKeypersoninfoQueryCriteria criteria);

    /**
     * findById
     * @param keyId
     * @return
     */
    //@Cacheable(key = "#p0")
    BizSchoolKeypersoninfoDTO findById(String keyId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    BizSchoolKeypersoninfoDTO create(BizSchoolKeypersoninfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(BizSchoolKeypersoninfo resources);

    /**
     * delete
     * @param keyId
     */
    //@CacheEvict(allEntries = true)
    void delete(String keyId);
}