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
public class T1beginepidemicPreventionQueryCriteria{

    // 精确
    @Query
    private String deptCode;

    // 精确
    @Query
    private String deptName;

    // 精确
    @Query
    private BigDecimal designHospitals;

    // 精确
    @Query
    private BigDecimal designPolice;

    // 精确
    @Query
    private BigDecimal feverClinics;

    // 精确
    @Query
    private BigDecimal feverPolice;

    // 精确
    @Query
    private BigDecimal focusCommunity;

    // 精确
    @Query
    private BigDecimal focuscommPolice;

    // 精确
    @Query
    private BigDecimal focusRural;

    // 精确
    @Query
    private BigDecimal focusruralPolice;

    // 精确
    @Query
    private BigDecimal policeNo;

    // 精确
    @Query
    private BigDecimal auxiliaryNo;

    // 精确
    @Query
    private BigDecimal checkNo;

    // 精确
    @Query
    private BigDecimal casenoPrices;

    // 精确
    @Query
    private BigDecimal casenoMedicine;

    // 精确
    @Query
    private BigDecimal policenoInfection;

    // 精确
    @Query
    private BigDecimal auxiliarynoInfection;

    // 精确
    @Query
    private String fillPerson;

    // 模糊
    @Query(type = Query.Type.BETWEEN)
    private List<String> fillTime;

    // 精确
    @Query
    private String issueName;
}
