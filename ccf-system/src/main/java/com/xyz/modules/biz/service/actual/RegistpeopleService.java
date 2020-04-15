package com.xyz.modules.biz.service.actual;

import com.xyz.modules.biz.service.actual.entity.Registpeople;
import com.xyz.modules.biz.service.actual.dto.RegistpeopleDTO;
import com.xyz.modules.biz.service.actual.qo.RegistpeopleQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author lx
* @date 2020-04-08
*/
//@CacheConfig(cacheNames = "Registpeople")
public interface RegistpeopleService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(RegistpeopleQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(RegistpeopleQueryCriteria criteria);

    /**
     * findById
     * @param regisId
     * @return
     */
    //@Cacheable(key = "#p0")
    RegistpeopleDTO findById(String regisId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    RegistpeopleDTO create(Registpeople resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Registpeople resources);

    /**
     * delete
     * @param regisId
     */
    //@CacheEvict(allEntries = true)
    void delete(String regisId);
}