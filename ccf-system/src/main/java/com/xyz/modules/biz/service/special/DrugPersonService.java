package com.xyz.modules.biz.service.special;

import com.xyz.modules.biz.service.special.entity.DrugPerson;
import com.xyz.modules.biz.service.special.dto.DrugPersonDTO;
import com.xyz.modules.biz.service.special.qo.DrugPersonQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "DrugPerson")
public interface DrugPersonService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(DrugPersonQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(DrugPersonQueryCriteria criteria);

    /**
     * findById
     * @param drugId
     * @return
     */
    //@Cacheable(key = "#p0")
    DrugPersonDTO findById(String drugId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    DrugPersonDTO create(DrugPerson resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(DrugPerson resources);

    /**
     * delete
     * @param drugId
     */
    //@CacheEvict(allEntries = true)
    void delete(String drugId);

    Boolean validateIdentityNum(String identityNum);
}