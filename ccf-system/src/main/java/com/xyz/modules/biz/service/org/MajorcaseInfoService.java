package com.xyz.modules.biz.service.org;

import com.xyz.modules.biz.service.org.entity.MajorcaseInfo;
import com.xyz.modules.biz.service.org.dto.MajorcaseInfoDTO;
import com.xyz.modules.biz.service.org.qo.MajorcaseInfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author dadovicn
* @date 2020-04-05
*/
//@CacheConfig(cacheNames = "MajorcaseInfo")
public interface MajorcaseInfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(MajorcaseInfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MajorcaseInfoQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    MajorcaseInfoDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    MajorcaseInfoDTO create(MajorcaseInfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(MajorcaseInfo resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(String id);
}