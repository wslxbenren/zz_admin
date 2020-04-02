package com.xyz.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import com.xyz.annotation.Query;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Data
public class T3commandcenterIndustryQueryCriteria{

    // 精确
    @Query
    private String alarmCall;

    // 精确
    @Query
    private String userid;

    // 精确
    @Query
    private String username;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String content;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String feedback;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private BigDecimal ifIsolation;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String isolationAddr;

    // 精确
    @Query
    private String deptCode;

    // 精确
    @Query
    private String deptName;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private Timestamp updateDate;
}