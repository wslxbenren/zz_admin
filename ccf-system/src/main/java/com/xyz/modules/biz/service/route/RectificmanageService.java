package com.xyz.modules.biz.service.route;

import com.xyz.modules.biz.service.route.entity.Rectificmanage;
import com.xyz.modules.biz.service.route.dto.RectificmanageDTO;
import com.xyz.modules.biz.service.route.qo.RectificmanageQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Rectificmanage")
public interface RectificmanageService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(RectificmanageQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(RectificmanageQueryCriteria criteria);

    /**
     * findById
     * @param rectId
     * @return
     */
    //@Cacheable(key = "#p0")
    RectificmanageDTO findById(String rectId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    RectificmanageDTO create(Rectificmanage resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Rectificmanage resources);

    /**
     * delete
     * @param rectId
     */
    //@CacheEvict(allEntries = true)
    void delete(String rectId);
}