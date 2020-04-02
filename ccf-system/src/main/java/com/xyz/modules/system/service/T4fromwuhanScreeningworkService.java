package com.xyz.modules.system.service;

import com.xyz.modules.system.domain.T4fromwuhanScreeningwork;
import com.xyz.modules.system.service.dto.T4fromwuhanScreeningworkDTO;
import com.xyz.modules.system.service.dto.T4fromwuhanScreeningworkQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @author dadovicn
* @date 2020-02-02
*/
//@CacheConfig(cacheNames = "t4fromwuhanScreeningwork")
public interface T4fromwuhanScreeningworkService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(T4fromwuhanScreeningworkQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(T4fromwuhanScreeningworkQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    T4fromwuhanScreeningworkDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    T4fromwuhanScreeningworkDTO create(T4fromwuhanScreeningwork resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(T4fromwuhanScreeningwork resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(String id);
    /**
     * selectGroupBy
     * 统计查询 group by
     *
     */
    List selectGroupBy(String name);
}