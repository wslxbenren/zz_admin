package com.xyz.modules.system.service;

import com.xyz.modules.system.domain.T1beginepidemicPrevention;
import com.xyz.modules.system.service.dto.T1beginepidemicPreventionDTO;
import com.xyz.modules.system.service.dto.T1beginepidemicPreventionQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author dadovicn
* @date 2020-02-02
*/
//@CacheConfig(cacheNames = "t1beginepidemicPrevention")
public interface T1beginepidemicPreventionService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(T1beginepidemicPreventionQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(T1beginepidemicPreventionQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    T1beginepidemicPreventionDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    T1beginepidemicPreventionDTO create(T1beginepidemicPrevention resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(T1beginepidemicPrevention resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(String id);
}