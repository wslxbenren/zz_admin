package com.xyz.modules.system.repository;

import com.xyz.modules.system.domain.DictDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
public interface DictDetailRepository extends JpaRepository<DictDetail, Long>, JpaSpecificationExecutor {
    /**
     *
     * @param dictId
     * @return
     */
    List<DictDetail> findByDictId(long dictId);

    @Query(value = "select b.* from dict a join dict_detail b on " +
            "a.id = b.dict_id and a.name = ?1 and b.value = ?2 " , nativeQuery = true)
    DictDetail findByValueAndPName(String pName, String value);

    /**
     * 只返回字典详情名称
     * @param pName 父级name
     * @param value 详情value
     * @return
     */
    @Query(value = "select b.label from dict a join dict_detail b on " +
            "a.id = b.dict_id and a.name = ?1 and b.value = ?2 " , nativeQuery = true)
    String transDict(String pName, String value);

    @Query(value = "select b.label from dict a join dict_detail b on " +
            "a.id = b.dict_id and a.id = ?1 and b.value = ?2 " , nativeQuery = true)
    String transDict(long dictTypeId, String value);

    /**
     * 向上递归查询
     * @param dictTypeId 字典类型id
     * @param value 字典值
     * @return
     */
    @Query(value = "with recursive dept_cte as\n" +
            "    (\n" +
            "       select a.label, a.pid from dict_detail a where a.value = ?2 and a.dict_id = ?1\n" +
            "       UNION ALL\n" +
            "       select b.label, b.pid from dict_detail b inner join dept_cte dcte on b.id = dcte.pid\n" +
            "    )\n" +
            "select group_concat(label order by pid separator '/' ) from dept_cte;", nativeQuery = true)
    String getAddrParentStr(long dictTypeId, String value);

    @Query(value = "SELECT GROUP_CONCAT(d.label ) AS labels  FROM dict_detail  d WHERE dict_id = ?1 AND d.value  in(?2) ", nativeQuery = true)
    String getLabelByValues(long dictId, List<String> joinManager);

    @Query(value = "select value from dict_detail where dict_id = ?2 and value like CONCAT(?1,'%') ", nativeQuery = true)
    List<String> addrWithDownGrade(String prefix, long dictTypeId);

    @Query(value = "select label from dict_detail where dict_id = ?1 and value =?2 ", nativeQuery = true)
    String findByDictIdAndValue(long dictId, Object values);
}