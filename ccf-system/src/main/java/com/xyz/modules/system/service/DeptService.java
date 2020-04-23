package com.xyz.modules.system.service;

import com.xyz.modules.system.service.dto.DeptDTO;
import com.xyz.modules.system.service.dto.DeptQueryCriteria;
import com.xyz.modules.system.domain.Dept;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2019-03-25
 */
@CacheConfig(cacheNames = "dept")
public interface DeptService {

    /**
     * queryAll
     *
     * @param criteria
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<DeptDTO> queryAll(DeptQueryCriteria criteria);

    /**
     * findById
     *
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    DeptDTO findById(String id);

    /**
     * create
     *
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    DeptDTO create(Dept resources);

    /**
     * update
     *
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Dept resources);

    /**
     * delete
     *
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(String id);

    /**
     * buildTree
     *
     * @param deptDTOS
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object buildTree(List<DeptDTO> deptDTOS);

    /**
     * findByPid
     *
     * @param pid
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<Dept> findByPid(String pid);

    Set<Dept> findByRoleIds(String id);


    /**
     * @Description: 通过rest获得dept信息
     * @Author: FuckJapan
     * @CreateDate: 2019/11/11 10:31
     * @UpdateUser: FuckJapan
     * @UpdateDate: 2019/11/11 10:31
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */

    List<Dept> getHttpDeptList(String thirdld, String lastTime, String pageNo, String pageSize);


    int saveDeptIncrement();

    /**
     * 获取下级部门编码集合
     *
     * @param deptCode 当前部门 code
     * @return
     */
    @Cacheable(cacheNames = "deptDownGrade", key = "#p0")
    Set<String> getDownGradeDeptCodes(String deptCode);
}
