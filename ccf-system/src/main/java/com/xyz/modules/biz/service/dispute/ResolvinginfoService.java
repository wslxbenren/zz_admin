package com.xyz.modules.biz.service.dispute;

import com.xyz.modules.biz.service.dispute.entity.Resolvinginfo;
import com.xyz.modules.biz.service.dispute.dto.ResolvinginfoDTO;
import com.xyz.modules.biz.service.dispute.qo.ResolvinginfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Resolvinginfo")
public interface ResolvinginfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(ResolvinginfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ResolvinginfoQueryCriteria criteria);

    /**
     * findById
     * @param resolvId
     * @return
     */
    //@Cacheable(key = "#p0")
    ResolvinginfoDTO findById(String resolvId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    ResolvinginfoDTO create(Resolvinginfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Resolvinginfo resources);

    /**
     * delete
     * @param resolvId
     */
    //@CacheEvict(allEntries = true)
    void delete(String resolvId);
}