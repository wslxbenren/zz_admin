package com.xyz.modules.biz.service.special;

import com.xyz.modules.biz.service.special.entity.BegPerson;
import com.xyz.modules.biz.service.special.dto.BegPersonDTO;
import com.xyz.modules.biz.service.special.qo.BegPersonQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "BegPerson")
public interface BegPersonService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(BegPersonQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(BegPersonQueryCriteria criteria);

    /**
     * findById
     * @param begId
     * @return
     */
    //@Cacheable(key = "#p0")
    BegPersonDTO findById(String begId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    BegPersonDTO create(BegPerson resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(BegPerson resources);

    /**
     * delete
     * @param begId
     */
    //@CacheEvict(allEntries = true)
    void delete(String begId);
}