package com.xyz.modules.system.service;

import com.xyz.modules.system.domain.T3commandcenterIndustry;
import com.xyz.modules.system.service.dto.T3commandcenterIndustryDTO;
import com.xyz.modules.system.service.dto.T3commandcenterIndustryQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author dadovicn
* @date 2020-02-02
*/
//@CacheConfig(cacheNames = "t3commandcenterIndustry")
public interface T3commandcenterIndustryService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(T3commandcenterIndustryQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(T3commandcenterIndustryQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    T3commandcenterIndustryDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    T3commandcenterIndustryDTO create(T3commandcenterIndustry resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(T3commandcenterIndustry resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(String id);
}