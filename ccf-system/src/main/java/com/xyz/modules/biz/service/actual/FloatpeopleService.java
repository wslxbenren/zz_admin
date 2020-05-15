package com.xyz.modules.biz.service.actual;

import com.xyz.modules.biz.audit.mongo.ModifyRecords;
import com.xyz.modules.biz.service.actual.entity.Floatpeople;
import com.xyz.modules.biz.service.actual.dto.FloatpeopleDTO;
import com.xyz.modules.biz.service.actual.qo.FloatpeopleQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
* @author lx
* @date 2020-04-09
*/
//@CacheConfig(cacheNames = "Floatpeople")
public interface FloatpeopleService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(FloatpeopleQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(FloatpeopleQueryCriteria criteria);

    /**
     * findById
     * @param floatId
     * @return
     */
    //@Cacheable(key = "#p0")
    FloatpeopleDTO findById(String floatId);


    List<ModifyRecords> findModifyRecordsById(String floatId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    FloatpeopleDTO create(Floatpeople resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Floatpeople resources);

    /**
     * delete
     * @param floatId
     */
    //@CacheEvict(allEntries = true)
    void delete(String floatId);

    Boolean validateIdentityNum(String id,String identityNum);
}