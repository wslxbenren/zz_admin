package com.xyz.modules.biz.service;

import com.xyz.modules.biz.domain.Processmonitor;
import com.xyz.modules.biz.service.dto.ProcessmonitorDTO;
import com.xyz.modules.biz.service.dto.ProcessmonitorQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Processmonitor")
public interface ProcessmonitorService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(ProcessmonitorQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ProcessmonitorQueryCriteria criteria);

    /**
     * findById
     * @param processId
     * @return
     */
    //@Cacheable(key = "#p0")
    ProcessmonitorDTO findById(String processId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    ProcessmonitorDTO create(Processmonitor resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Processmonitor resources);

    /**
     * delete
     * @param processId
     */
    //@CacheEvict(allEntries = true)
    void delete(String processId);
}