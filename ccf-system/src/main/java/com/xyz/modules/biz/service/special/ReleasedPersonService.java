package com.xyz.modules.biz.service.special;

import com.xyz.modules.biz.service.special.entity.ReleasedPerson;
import com.xyz.modules.biz.service.special.dto.ReleasedPersonDTO;
import com.xyz.modules.biz.service.special.qo.ReleasedPersonQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "ReleasedPerson")
public interface ReleasedPersonService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(ReleasedPersonQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ReleasedPersonQueryCriteria criteria);

    /**
     * findById
     * @param releasedId
     * @return
     */
    //@Cacheable(key = "#p0")
    ReleasedPersonDTO findById(String releasedId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    ReleasedPersonDTO create(ReleasedPerson resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(ReleasedPerson resources);

    /**
     * delete
     * @param releasedId
     */
    //@CacheEvict(allEntries = true)
    void delete(String releasedId);
}