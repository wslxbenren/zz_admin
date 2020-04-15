package com.xyz.modules.biz.service.route;

import com.xyz.modules.biz.service.route.entity.Convenientinfo;
import com.xyz.modules.biz.service.route.dto.ConvenientinfoDTO;
import com.xyz.modules.biz.service.route.qo.ConvenientinfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Convenientinfo")
public interface ConvenientinfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(ConvenientinfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ConvenientinfoQueryCriteria criteria);

    /**
     * findById
     * @param conId
     * @return
     */
    //@Cacheable(key = "#p0")
    ConvenientinfoDTO findById(String conId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    ConvenientinfoDTO create(Convenientinfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Convenientinfo resources);

    /**
     * delete
     * @param conId
     */
    //@CacheEvict(allEntries = true)
    void delete(String conId);
}