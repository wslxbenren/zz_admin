package com.xyz.modules.biz.service.secur;

import com.xyz.modules.biz.service.secur.entity.Suspectinfo;
import com.xyz.modules.biz.service.secur.dto.SuspectinfoDTO;
import com.xyz.modules.biz.service.secur.dto.SuspectinfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Suspectinfo")
public interface SuspectinfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(SuspectinfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SuspectinfoQueryCriteria criteria);

    /**
     * findById
     * @param suspId
     * @return
     */
    //@Cacheable(key = "#p0")
    SuspectinfoDTO findById(String suspId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    SuspectinfoDTO create(Suspectinfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Suspectinfo resources);

    /**
     * delete
     * @param suspId
     */
    //@CacheEvict(allEntries = true)
    void delete(String suspId);
}