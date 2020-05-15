package com.xyz.modules.biz.service.actual;

import com.xyz.modules.biz.audit.mongo.ModifyRecords;
import com.xyz.modules.biz.service.actual.entity.Rentalhouse;
import com.xyz.modules.biz.service.actual.dto.RentalhouseDTO;
import com.xyz.modules.biz.service.actual.qo.RentalhouseQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @author lx
* @date 2020-04-09
*/
//@CacheConfig(cacheNames = "Rentalhouse")
public interface RentalhouseService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(RentalhouseQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(RentalhouseQueryCriteria criteria);

    /**
     * findById
     * @param rentId
     * @return
     */
    //@Cacheable(key = "#p0")
    RentalhouseDTO findById(String rentId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    RentalhouseDTO create(Rentalhouse resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Rentalhouse resources);

    /**
     * delete
     * @param rentId
     */
    //@CacheEvict(allEntries = true)
    void delete(String rentId);

    List<ModifyRecords> findModifyRecordsById(String foreId)  ;
}