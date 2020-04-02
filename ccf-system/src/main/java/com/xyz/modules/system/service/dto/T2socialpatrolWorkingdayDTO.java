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
public class T2socialpatrolWorkingdayDTO implements Serializable {

    // 主键
    private String id;

    // 日期
    private Timestamp checkTime;

    // 单位编码
    private String deptCode;

    // 单位名称
    private String deptName;

    // 巡逻防控投入民警数
    private BigDecimal patrolPolice;

    // 巡逻防控投入辅警数
    private BigDecimal patrolAuxiliary;

    // 群防群治力量总数
    private BigDecimal patrolOther;

    // 启动公安检查站数
    private BigDecimal checkpointNo;

    // 临时执勤点数
    private BigDecimal checkpointTempno;

    // 联合防疫检查站数
    private BigDecimal checkpointUnionno;

    // 检查站投入民警数
    private BigDecimal checkPolice;

    // 检查站投入辅警数
    private BigDecimal checkAuxiliary;

    // 检查车辆数
    private BigDecimal checkCar;

    // 检查人员数
    private BigDecimal checkPerson;

    // 发现协助转达疑似感染数
    private BigDecimal assistNo;

    // 发现核查涉稳线索数
    private BigDecimal clueNo;

    // 处置涉疫情稳定事件数
    private BigDecimal caseNo;

    // 部署警力应急备勤数
    private BigDecimal emergencyNo;

    // 填报人
    private String fillPerson;

    // 联系方式
    private String mobile;

    // 填报时间
    private Timestamp fillTime;

    // 签发人
    private String issueName;

    // 更新时间
    private Timestamp updateTime;
}