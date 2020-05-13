package com.xyz.modules.system.service;

import com.xyz.modules.system.domain.Dict;
import com.xyz.modules.system.service.dto.DictDetailDTO;
import com.xyz.modules.system.service.dto.DictDetailQueryCriteria;
import com.xyz.modules.system.domain.DictDetail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@CacheConfig(cacheNames = "dictDetail")
public interface DictDetailService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    DictDetailDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    DictDetailDTO create(DictDetail resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(DictDetail resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * fixme 缓存是否需要
     * @param criteria
     * @param pageable
     * @return
     */
    Map queryAll(DictDetailQueryCriteria criteria, Pageable pageable);

    /**
     * 根据字典code和字典具体值查找字典具体名称
     * 做字典翻译尽量不要使用这个方法， 更换为 transDict
     * @param pName 字典code-》父表name
     * @param value 字典项的值
     * @return 字典详情
     */
    @Deprecated
    DictDetail findByValueAndPName(String pName, String value);

    /**
     * 根据字典code和字典具体值查找字典具体名称
     * @param pName 字典code-》父表name
     * @param value 字典项的值
     * @return 字典详情名称
     */
    @Deprecated
    String transDict(String pName, String value);

        /**
         * 根据字典类型id和字典详情值查询详情
         * @param dictTypeId
         * @param dictDetailValue
         * @return
         */
    String transDict(long dictTypeId, String dictDetailValue);

    /**
     * 没有分页
     * @param criteria
     * @return
     */
    List<DictDetail> queryAll(DictDetailQueryCriteria criteria);

    /**
     * 向上递归查询父字典名称（翻译）
     * @param dictTypeId
     * @param dictDetailValue
     * @return
     */
    String transMultistage(long dictTypeId, String dictDetailValue);

    String getLabelByValues(long dictId,   String[]  joinManager);

    List<String> addrWithDownGrade(String prefix, long dictTypeId);
}