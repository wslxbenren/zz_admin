package com.xyz.modules.biz.service.secur;

import com.xyz.modules.biz.service.secur.entity.Victiminfo;
import com.xyz.modules.biz.service.secur.dto.VictiminfoDTO;
import com.xyz.modules.biz.service.secur.dto.VictiminfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Victiminfo")
public interface VictiminfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(VictiminfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(VictiminfoQueryCriteria criteria);

    /**
     * findById
     * @param vicId
     * @return
     */
    //@Cacheable(key = "#p0")
    VictiminfoDTO findById(String vicId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    VictiminfoDTO create(Victiminfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Victiminfo resources);

    /**
     * delete
     * @param vicId
     */
    //@CacheEvict(allEntries = true)
    void delete(String vicId);
}