package com.xyz.modules.system.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import com.xyz.annotation.Query;
import lombok.NoArgsConstructor;


/**
* @author dadovicn
* @date 2020-02-07
*/
@Data
public class SuspectedPersonnelInfoQueryCriteria{

    // 精确
    @Query
    private String peId;

    // 精确
    @Query
    private String peName;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String peIdno;

    // 精确
    @Query
    private String physicalInfo;

    // 精确
    @Query
    private BigDecimal ifOk;

    // 精确
    @Query
    private String entryName;

    // 精确
    @Query
    private String entryUnit;

    // 精确
    @Query
    private String screeningName;

    // 精确
    @Query
    private String headName;

    // 精确
    @Query
    private String leaderName;

    @Query
    private BigDecimal applyStatus;
}