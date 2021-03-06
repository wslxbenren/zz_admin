package com.xyz.modules.biz.service.school;

import com.xyz.modules.biz.service.school.entity.BizSchoolCaseinfo;
import com.xyz.modules.biz.service.school.dto.BizSchoolCaseinfoDTO;
import com.xyz.modules.biz.service.school.qo.BizSchoolCaseinfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "bizSchoolCaseinfo")
public interface BizSchoolCaseinfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(BizSchoolCaseinfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(BizSchoolCaseinfoQueryCriteria criteria);

    /**
     * findById
     * @param caseId
     * @return
     */
    //@Cacheable(key = "#p0")
    BizSchoolCaseinfoDTO findById(String caseId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    BizSchoolCaseinfoDTO create(BizSchoolCaseinfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(BizSchoolCaseinfo resources);

    /**
     * delete
     * @param caseId
     */
    //@CacheEvict(allEntries = true)
    void delete(String caseId);
}