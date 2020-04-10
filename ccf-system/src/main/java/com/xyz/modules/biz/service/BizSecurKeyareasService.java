package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.BizSecurKeyareas;
import com.xyz.modules.biz.service.dto.BizSecurKeyareasDTO;
import com.xyz.modules.biz.service.dto.BizSecurKeyareasQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "bizSecurKeyareas")
public interface BizSecurKeyareasService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(BizSecurKeyareasQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(BizSecurKeyareasQueryCriteria criteria);

    /**
     * findById
     * @param keyId
     * @return
     */
    //@Cacheable(key = "#p0")
    BizSecurKeyareasDTO findById(String keyId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    BizSecurKeyareasDTO create(BizSecurKeyareas resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(BizSecurKeyareas resources);

    /**
     * delete
     * @param keyId
     */
    //@CacheEvict(allEntries = true)
    void delete(String keyId);
}