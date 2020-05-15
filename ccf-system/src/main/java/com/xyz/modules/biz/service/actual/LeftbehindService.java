package com.xyz.modules.biz.service.actual;

import com.xyz.modules.biz.audit.mongo.ModifyRecords;
import com.xyz.modules.biz.service.actual.entity.Leftbehind;
import com.xyz.modules.biz.service.actual.dto.LeftbehindDTO;
import com.xyz.modules.biz.service.actual.qo.LeftbehindQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @author dadovicn
* @date 2020-04-08
*/
//@CacheConfig(cacheNames = "Leftbehind")
public interface LeftbehindService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(LeftbehindQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(LeftbehindQueryCriteria criteria);

    /**
     * findById
     * @param leftId
     * @return
     */
    //@Cacheable(key = "#p0")
    LeftbehindDTO findById(String leftId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    LeftbehindDTO create(Leftbehind resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Leftbehind resources);

    /**
     * delete
     * @param leftId
     */
    //@CacheEvict(allEntries = true)
    void delete(String leftId);

    Boolean validateIdentityNum(String id, String identityNum);

    List<ModifyRecords> findModifyRecordsById(String foreId)  ;
}