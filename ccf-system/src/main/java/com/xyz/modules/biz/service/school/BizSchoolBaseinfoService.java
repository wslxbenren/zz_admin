package com.xyz.modules.biz.service.school;

import com.xyz.modules.biz.service.school.entity.BizSchoolBaseinfo;
import com.xyz.modules.biz.service.school.dto.BizSchoolBaseinfoDTO;
import com.xyz.modules.biz.service.school.qo.BizSchoolBaseinfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "bizSchoolBaseinfo")
public interface BizSchoolBaseinfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(BizSchoolBaseinfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(BizSchoolBaseinfoQueryCriteria criteria);

    /**
     * findById
     * @param baseId
     * @return
     */
    //@Cacheable(key = "#p0")
    BizSchoolBaseinfoDTO findById(String baseId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    BizSchoolBaseinfoDTO create(BizSchoolBaseinfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(BizSchoolBaseinfo resources);

    /**
     * delete
     * @param baseId
     */
    //@CacheEvict(allEntries = true)
    void delete(String baseId);
}