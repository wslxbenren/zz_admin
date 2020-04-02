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
public class T5keypersonScreeningworkQueryCriteria{

    // 精确
    @Query
    private String deptCode;

    // 精确
    @Query
    private String deptName;

    // 精确
    @Query
    private String fillPerson;

    // 模糊   填写时间
    @Query(type = Query.Type.BETWEEN)
    private List<String> fillTime;

    // 模糊 核实时间
    @Query(type = Query.Type.INNER_LIKE)
    private Timestamp checkTime;
}