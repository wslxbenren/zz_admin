package com.xyz.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Entity
@Data
@Table(name="t_1beginepidemic_prevention")
public class T1beginepidemicPrevention implements Serializable {

    // 主键
    @Id
    @Column(name = "id")
    private String id;

    // 日期
    @Column(name = "check_time")
    private Timestamp checkTime;

    // 单位编码
    @Column(name = "dept_code")
    private String deptCode;

    // 单位名称
    @Column(name = "dept_name")
    private String deptName;

    // 定点医疗机构数量
    @Column(name = "design_hospitals")
    private BigDecimal designHospitals;

    // 定点医疗投入警力数
    @Column(name = "design_police")
    private BigDecimal designPolice;

    // 发热门诊、隔离集中点
    @Column(name = "fever_clinics")
    private BigDecimal feverClinics;

    // 发热门诊投入警力数
    @Column(name = "fever_police")
    private BigDecimal feverPolice;

    // 重点社区数量
    @Column(name = "focus_community")
    private BigDecimal focusCommunity;

    // 重点社区投入警力数
    @Column(name = "focuscomm_police")
    private BigDecimal focuscommPolice;

    // 重点乡村数量
    @Column(name = "focus_rural")
    private BigDecimal focusRural;

    // 重点乡村投入警力数
    @Column(name = "focusrural_police")
    private BigDecimal focusruralPolice;

    // 投入民警总数
    @Column(name = "police_no")
    private BigDecimal policeNo;

    // 投入辅警总数
    @Column(name = "auxiliary_no")
    private BigDecimal auxiliaryNo;

    // 协助核查追踪转运等感染疑似人总数
    @Column(name = "check_no")
    private BigDecimal checkNo;

    // 办案数-哄抬物价等扰乱市场秩序(当天数量)
    @Column(name = "caseno_prices")
    private BigDecimal casenoPrices;

    // 办案数-制造假劣医药卫生用品
    @Column(name = "caseno_medicine")
    private BigDecimal casenoMedicine;

    // 办案数-非法收购运输野生动物
    @Column(name = "caseno_animal")
    private BigDecimal casenoAnimal;

    // 办案数-妨碍传染病治疗
    @Column(name = "caseno_treatment")
    private BigDecimal casenoTreatment;

    // 办案数-以危险方法危害公共安全
    @Column(name = "caseno_safety")
    private BigDecimal casenoSafety;

    // 办案数-编造传播虚假有害信息
    @Column(name = "caseno_falseinfo")
    private BigDecimal casenoFalseinfo;

    // 办案数-利用疫情实施诈骗
    @Column(name = "caseno_fraud")
    private BigDecimal casenoFraud;

    // 办案数-聚众哄抢
    @Column(name = "caseno_group")
    private BigDecimal casenoGroup;

    // 办案数-妨害公务
    @Column(name = "caseno_official")
    private BigDecimal casenoOfficial;

    // 办案数-涉疫情防控其他案件
    @Column(name = "caseno_other")
    private BigDecimal casenoOther;

    // 处理人员-刑事拘留
    @Column(name = "peono_criminal")
    private BigDecimal peonoCriminal;

    // 处理人员-行政拘留
    @Column(name = "peono_detention")
    private BigDecimal peonoDetention;

    // 处理人员-行政罚款
    @Column(name = "peono_penalty")
    private BigDecimal peonoPenalty;

    // 处理人员-教育训诫
    @Column(name = "peono_education")
    private BigDecimal peonoEducation;

    // 民警感染人数
    @Column(name = "policeno_infection")
    private BigDecimal policenoInfection;

    // 辅警感染人数
    @Column(name = "auxiliaryno_infection")
    private BigDecimal auxiliarynoInfection;

    // 填报人
    @Column(name = "fill_person")
    private String fillPerson;

    // 联系方式
    @Column(name = "mobile")
    private String mobile;

    // 填报时间
    @Column(name = "fill_time", updatable = false)
    @CreationTimestamp
    private Timestamp fillTime;

    // 签发人
    @Column(name = "issue_name")
    private String issueName;

    // 更新时间
    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

    public void copy(T1beginepidemicPrevention source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}