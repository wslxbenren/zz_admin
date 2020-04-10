package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.Socialorgan;
import com.xyz.modules.biz.service.dto.SocialorganDTO;
import com.xyz.modules.biz.service.dto.SocialorganQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Socialorgan")
public interface SocialorganService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(SocialorganQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SocialorganQueryCriteria criteria);

    /**
     * findById
     * @param sociaId
     * @return
     */
    //@Cacheable(key = "#p0")
    SocialorganDTO findById(String sociaId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    SocialorganDTO create(Socialorgan resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Socialorgan resources);

    /**
     * delete
     * @param sociaId
     */
    //@CacheEvict(allEntries = true)
    void delete(String sociaId);
}