package com.xyz.modules.system.service;

import com.xyz.modules.system.domain.Dict;
import com.xyz.modules.system.service.dto.DictDTO;
import com.xyz.modules.system.service.dto.DictQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@CacheConfig(cacheNames = "dict")
public interface DictService {

    /**
     * 查询
     * @param dict
     * @param pageable
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(DictQueryCriteria dict, Pageable pageable);

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    DictDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    DictDTO create(Dict resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Dict resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * 查询指定字典集合
     * @param className 指定类全限定名
     * @return
     */
    List<Dict> buildDict(String className);

    /**
     * 获取二级字典数据
     * @return
     */
    List<Dict> get2LevelDict();
}