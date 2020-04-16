package com.xyz.modules.biz.service.route;

import com.xyz.modules.biz.service.route.entity.Keypersoninfo;
import com.xyz.modules.biz.service.route.dto.KeypersoninfoDTO;
import com.xyz.modules.biz.service.route.qo.KeypersoninfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Keypersoninfo")
public interface KeypersoninfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(KeypersoninfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(KeypersoninfoQueryCriteria criteria);

    /**
     * findById
     * @param keyId
     * @return
     */
    //@Cacheable(key = "#p0")
    KeypersoninfoDTO findById(String keyId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    KeypersoninfoDTO create(Keypersoninfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Keypersoninfo resources);

    /**
     * delete
     * @param keyId
     */
    //@CacheEvict(allEntries = true)
    void delete(String keyId);
}