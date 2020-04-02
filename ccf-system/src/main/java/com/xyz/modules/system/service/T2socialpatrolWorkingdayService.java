package com.xyz.modules.system.service;

import com.xyz.modules.system.domain.T2socialpatrolWorkingday;
import com.xyz.modules.system.service.dto.T2socialpatrolWorkingdayDTO;
import com.xyz.modules.system.service.dto.T2socialpatrolWorkingdayQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author dadovicn
* @date 2020-02-02
*/
//@CacheConfig(cacheNames = "t2socialpatrolWorkingday")
public interface T2socialpatrolWorkingdayService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(T2socialpatrolWorkingdayQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(T2socialpatrolWorkingdayQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    T2socialpatrolWorkingdayDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    T2socialpatrolWorkingdayDTO create(T2socialpatrolWorkingday resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(T2socialpatrolWorkingday resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(String id);
}