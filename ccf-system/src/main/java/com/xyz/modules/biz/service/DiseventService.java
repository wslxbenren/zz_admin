package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.Disevent;
import com.xyz.modules.biz.service.dto.DiseventDTO;
import com.xyz.modules.biz.service.dto.DiseventQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Disevent")
public interface DiseventService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(DiseventQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(DiseventQueryCriteria criteria);

    /**
     * findById
     * @param eventId
     * @return
     */
    //@Cacheable(key = "#p0")
    DiseventDTO findById(String eventId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    DiseventDTO create(Disevent resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Disevent resources);

    /**
     * delete
     * @param eventId
     */
    //@CacheEvict(allEntries = true)
    void delete(String eventId);
}