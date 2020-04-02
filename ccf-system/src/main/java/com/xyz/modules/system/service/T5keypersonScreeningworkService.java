package com.xyz.modules.system.service;

import com.xyz.modules.system.domain.T5keypersonScreeningwork;
import com.xyz.modules.system.service.dto.T5keypersonScreeningworkDTO;
import com.xyz.modules.system.service.dto.T5keypersonScreeningworkQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author dadovicn
* @date 2020-02-02
*/
//@CacheConfig(cacheNames = "t5keypersonScreeningwork")
public interface T5keypersonScreeningworkService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(T5keypersonScreeningworkQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(T5keypersonScreeningworkQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    T5keypersonScreeningworkDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    T5keypersonScreeningworkDTO create(T5keypersonScreeningwork resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(T5keypersonScreeningwork resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(String id);
}