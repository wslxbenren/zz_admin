package com.xyz.modules.biz.service.teenager;

import com.xyz.modules.biz.service.teenager.entity.BizTeenagerBaseinfo;
import com.xyz.modules.biz.service.teenager.dto.BizTeenagerBaseinfoDTO;
import com.xyz.modules.biz.service.teenager.dto.BizTeenagerBaseinfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "bizTeenagerBaseinfo")
public interface BizTeenagerBaseinfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(BizTeenagerBaseinfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(BizTeenagerBaseinfoQueryCriteria criteria);

    /**
     * findById
     * @param teenId
     * @return
     */
    //@Cacheable(key = "#p0")
    BizTeenagerBaseinfoDTO findById(String teenId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    BizTeenagerBaseinfoDTO create(BizTeenagerBaseinfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(BizTeenagerBaseinfo resources);

    /**
     * delete
     * @param teenId
     */
    //@CacheEvict(allEntries = true)
    void delete(String teenId);
}