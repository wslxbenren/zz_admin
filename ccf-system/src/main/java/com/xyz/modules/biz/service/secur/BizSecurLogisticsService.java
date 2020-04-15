package com.xyz.modules.biz.service.secur;

import com.xyz.modules.biz.service.secur.entity.BizSecurLogistics;
import com.xyz.modules.biz.service.secur.dto.BizSecurLogisticsDTO;
import com.xyz.modules.biz.service.secur.dto.BizSecurLogisticsQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "bizSecurLogistics")
public interface BizSecurLogisticsService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(BizSecurLogisticsQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(BizSecurLogisticsQueryCriteria criteria);

    /**
     * findById
     * @param logisId
     * @return
     */
    //@Cacheable(key = "#p0")
    BizSecurLogisticsDTO findById(String logisId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    BizSecurLogisticsDTO create(BizSecurLogistics resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(BizSecurLogistics resources);

    /**
     * delete
     * @param logisId
     */
    //@CacheEvict(allEntries = true)
    void delete(String logisId);
}