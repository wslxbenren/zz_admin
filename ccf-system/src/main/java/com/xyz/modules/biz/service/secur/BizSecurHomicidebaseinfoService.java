package com.xyz.modules.biz.service.secur;

import com.xyz.modules.biz.service.secur.entity.BizSecurHomicidebaseinfo;
import com.xyz.modules.biz.service.secur.dto.BizSecurHomicidebaseinfoDTO;
import com.xyz.modules.biz.service.secur.dto.BizSecurHomicidebaseinfoQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "bizSecurHomicidebaseinfo")
public interface BizSecurHomicidebaseinfoService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(BizSecurHomicidebaseinfoQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(BizSecurHomicidebaseinfoQueryCriteria criteria);

    /**
     * findById
     * @param caseId
     * @return
     */
    //@Cacheable(key = "#p0")
    BizSecurHomicidebaseinfoDTO findById(String caseId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    BizSecurHomicidebaseinfoDTO create(BizSecurHomicidebaseinfo resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(BizSecurHomicidebaseinfo resources);

    /**
     * delete
     * @param caseId
     */
    //@CacheEvict(allEntries = true)
    void delete(String caseId);
}