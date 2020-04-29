package com.xyz.modules.biz.service.special;

import com.xyz.modules.biz.service.special.entity.AidsPerson;
import com.xyz.modules.biz.service.special.dto.AidsPersonDTO;
import com.xyz.modules.biz.service.special.qo.AidsPersonQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
//@CacheConfig(cacheNames = "AidsPerson")
public interface AidsPersonService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(AidsPersonQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AidsPersonQueryCriteria criteria);

    /**
     * findById
     * @param aidsId
     * @return
     */
    //@Cacheable(key = "#p0")
    AidsPersonDTO findById(String aidsId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    AidsPersonDTO create(AidsPerson resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(AidsPerson resources);

    /**
     * delete
     * @param aidsId
     */
    //@CacheEvict(allEntries = true)
    void delete(String aidsId);

    void export(HttpServletResponse response , AidsPersonQueryCriteria criteria) throws IOException;

    Boolean validateIdentityNum(String identityNum);
}