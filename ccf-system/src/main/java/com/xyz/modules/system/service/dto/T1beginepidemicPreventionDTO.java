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
public class T1beginepidemicPreventionDTO implements Serializable {

    // 主键
    private String id;

    // 日期
    private Timestamp checkTime;

    // 单位编码
    private String deptCode;

    // 单位名称
    private String deptName;

    // 定点医疗机构数量
    private BigDecimal designHospitals;

    // 定点医疗投入警力数
    private BigDecimal designPolice;

    // 发热门诊、隔离集中点
    private BigDecimal feverClinics;

    // 发热门诊投入警力数
    private BigDecimal feverPolice;

    // 重点社区数量
    private BigDecimal focusCommunity;

    // 重点社区投入警力数
    private BigDecimal focuscommPolice;

    // 重点乡村数量
    private BigDecimal focusRural;

    // 重点乡村投入警力数
    private BigDecimal focusruralPolice;

    // 投入民警总数
    private BigDecimal policeNo;

    // 投入辅警总数
    private BigDecimal auxiliaryNo;

    // 协助核查追踪转运等感染疑似人总数
    private BigDecimal checkNo;

    // 办案数-哄抬物价等扰乱市场秩序(当天数量)
    private BigDecimal casenoPrices;

    // 办案数-制造假劣医药卫生用品
    private BigDecimal casenoMedicine;

    // 办案数-非法收购运输野生动物
    private BigDecimal casenoAnimal;

    // 办案数-妨碍传染病治疗
    private BigDecimal casenoTreatment;

    // 办案数-以危险方法危害公共安全
    private BigDecimal casenoSafety;

    // 办案数-编造传播虚假有害信息
    private BigDecimal casenoFalseinfo;

    // 办案数-利用疫情实施诈骗
    private BigDecimal casenoFraud;

    // 办案数-聚众哄抢
    private BigDecimal casenoGroup;

    // 办案数-妨害公务
    private BigDecimal casenoOfficial;

    // 办案数-涉疫情防控其他案件
    private BigDecimal casenoOther;

    // 处理人员-刑事拘留
    private BigDecimal peonoCriminal;

    // 处理人员-行政拘留
    private BigDecimal peonoDetention;

    // 处理人员-行政罚款
    private BigDecimal peonoPenalty;

    // 处理人员-教育训诫
    private BigDecimal peonoEducation;

    // 民警感染人数
    private BigDecimal policenoInfection;

    // 辅警感染人数
    private BigDecimal auxiliarynoInfection;

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