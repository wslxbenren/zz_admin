package com.xyz.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;

import com.xyz.annotation.Query;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Data
public class T2socialpatrolWorkingdayQueryCriteria{

    // 精确
    @Query
    private String deptCode;

    // 精确
    @Query
    private String deptName;

    // 精确
    @Query
    private BigDecimal patrolPolice;

    // 精确
    @Query
    private BigDecimal patrolAuxiliary;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String mobile;

    // 模糊
    @Query(type = Query.Type.BETWEEN)
    private List<String> fillTime;
    // 精确
    @Query
    private String issueName;
}