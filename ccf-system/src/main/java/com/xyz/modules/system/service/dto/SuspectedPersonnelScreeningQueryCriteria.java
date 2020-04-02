package com.xyz.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import com.xyz.annotation.Query;

/**
* @author dadovicn
* @date 2020-02-07
*/
@Data
public class SuspectedPersonnelScreeningQueryCriteria{

    @Query
    private String id;

    // 精确
    @Query
    private String pId;

    // 精确
    @Query
    private String entryName;

    // 精确
    @Query
    private BigDecimal ifHubei;

    // 精确
    @Query
    private BigDecimal ifContact;

    // 精确
    @Query
    private String entryUnit;

    @Query
    private BigDecimal applyStatus;
}