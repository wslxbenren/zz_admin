package com.xyz.modules.system.repository;

import com.xyz.modules.system.domain.DictDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

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

    @Procedure(procedureName = "proAddrParentList")
    void proAddrParentList(long v_id, String v_value);

    @Query(value = "select label from pro_addrparentlist order by grage asc", nativeQuery = true)
    String[] upRecursiveDict();
    @Query(value = "SELECT GROUP_CONCAT(d.label ) AS labels  FROM dict_detail  d WHERE dict_id = ?1 AND d.value  in(?2) ", nativeQuery = true)
    String getLabelByValues(long dictId, List<String> joinManager);
}