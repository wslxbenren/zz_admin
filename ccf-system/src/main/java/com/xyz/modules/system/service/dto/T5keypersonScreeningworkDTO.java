package com.xyz.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;


/**
* @author dadovicn
* @date 2020-02-02
*/
@Data
public class T5keypersonScreeningworkDTO implements Serializable {

    // 主键
    private String id;

    // 单位编码
    private String deptCode;

    // 单位名称
    private String deptName;

    // 下发人数
    private BigDecimal issuedNo;

    // 自排人数
    private BigDecimal selfNo;

    // 当日总数
    private BigDecimal sumNo;

    // 核查日期
    private Timestamp checkTime;

    // 填报人
    private String fillPerson;

    // 联系方式
    private String mobile;

    // 填报时间
    private Timestamp fillTime;

    // 更新时间
    private Timestamp updateTime;
}