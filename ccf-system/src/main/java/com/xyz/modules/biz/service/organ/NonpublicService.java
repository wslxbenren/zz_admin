package com.xyz.modules.biz.service.organ;

import com.xyz.modules.biz.service.organ.entity.Nonpublic;
import com.xyz.modules.biz.service.organ.dto.NonpublicDTO;
import com.xyz.modules.biz.service.organ.qo.NonpublicQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "Nonpublic")
public interface NonpublicService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(NonpublicQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(NonpublicQueryCriteria criteria);

    /**
     * findById
     * @param nonId
     * @return
     */
    //@Cacheable(key = "#p0")
    NonpublicDTO findById(String nonId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    NonpublicDTO create(Nonpublic resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Nonpublic resources);

    /**
     * delete
     * @param nonId
     */
    //@CacheEvict(allEntries = true)
    void delete(String nonId);

    Boolean verifyCreditCode(String creditCode);
}