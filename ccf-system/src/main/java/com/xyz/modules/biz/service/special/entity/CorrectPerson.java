package com.xyz.modules.biz.service.special.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author 刘鑫
 * @date 2020-04-10
 * 特殊人群社区矫正人群基础信息表
 */
@Entity
@Data
@Table(name="biz_special_correct_person")
@DynamicUpdate
public class CorrectPerson implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "correct_id")
    private String correctId;

    // 姓名
    @Column(name = "person_name",nullable = false)
    private String personName;

    // 曾用名
    @Column(name = "used_name")
    private String usedName;

    // 性别
    @Column(name = "person_sex")
    private String personSex;

    // 出生日期
    @Column(name = "date_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd " )
    private Timestamp dateBirth;

    // 民族
    @Column(name = "nation")
    private String nation;

    // 身份证号
    @Column(name = "identity_num",unique = true)
    private String identityNum;

    // 籍贯
    @Column(name = "native_info")
    private String nativeInfo;

    // 婚姻状况
    @Column(name = "marriage_flag")
    private String marriageFlag;

    // 政治面貌
    @Column(name = "party_flag")
    private String partyFlag;

    // 文化程度
    @Column(name = "edu_level")
    private String eduLevel;

    // 宗教信仰
    @Column(name = "faith_type")
    private String faithType;

    // 职业
    @Column(name = "vocation")
    private String vocation;

    // 服务场所
    @Column(name = "service_place")
    private String servicePlace;

    // 联系方式
    @Column(name = "contact")
    private String contact;

    // 户籍地
    @Column(name = "registered_place")
    private String registeredPlace;

    // 户籍详细地址
    @Column(name = "registered_address")
    private String registeredAddress;

    // 现住地，跟现住地编码residence_code数据重复存储
    @Column(name = "residence")
    private String residence;

    // 现住地编码
    @Column(name = "residence_code")
    private String residenceCode;

    // 现住地详细地址
    @Column(name = "residence_address")
    private String residenceAddress;

    // 社区矫正人员编号
    @Column(name = "correct_code")
    private String correctCode;

    // 原羁押场所
    @Column(name = "detain_union")
    private String detainUnion;

    // 矫正类别
    @Column(name = "correct_type")
    private String correctType;

    // 案件类别
    @Column(name = "case_type")
    private String caseType;

    // 具体罪名
    @Column(name = "charge_comments")
    private String chargeComments;

    // 原判刑期
    @Column(name = "prison_term")
    private String prisonTerm;

    // 原判刑开始日期
    @Column(name = "prison_beagindate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp prisonBeagindate;

    // 原判刑结束日期
    @Column(name = "prison_enddate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp prisonEnddate;

    // 矫正开始日期
    @Column(name = "correct_beagindate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp correctBeagindate;

    // 矫正结束日期
    @Column(name = "correct_enddate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp correctEnddate;

    // 接收方式
    @Column(name = "revice_flag")
    private String reviceFlag;

    // 四史情况--可多选，|号隔开
    @Column(name = "sishi_flag")
    private String sishiFlag;

    // 是否是惯犯
    @Column(name = "is_recidivist")
    private String isRecidivist;

    // 三涉情况--可多选，|号隔开
    @Column(name = "sanshe_flag")
    private String sansheFlag;

    // 是否建立矫正小组
    @Column(name = "is_team")
    private String isTeam;

    // 矫正小组人员情况--可多选，|号隔开
    @Column(name = "team_guys")
    private String teamGuys;

    // 矫正解除类型
    @Column(name = "correct_remove")
    private String correctRemove;

    // 是否脱管
    @Column(name = "is_breakmanage",nullable = false)
    private String isBreakmanage;

    // 脱管原因说明
    @Column(name = "breakmanage_reason")
    private String breakmanageReason;

    // 检查监督情况
    @Column(name = "check_comments")
    private String checkComments;

    // 脱管纠正情况
    @Column(name = "breakmanage_correct")
    private String breakmanageCorrect;

    // 是否漏管
    @Column(name = "is_omit",nullable = false)
    private String isOmit;

    // 漏管原因
    @Column(name = "omit_reason",nullable = false)
    private String omitReason;

    // 检查漏管情况
    @Column(name = "check_omit")
    private String checkOmit;

    // 漏管纠正情况
    @Column(name = "omit_correct")
    private String omitCorrect;

    // 奖惩情况
    @Column(name = "bonus_penalty")
    private String bonusPenalty;

    // 刑罚变更执行情况
    @Column(name = "prison_change")
    private String prisonChange;

    // 是否重新犯罪
    @Column(name = "is_again",nullable = false)
    private String isAgain;

    // 重新犯罪罪名
    @Column(name = "again_charge",nullable = false)
    private String againCharge;

    // 生效时间
    @Column(name = "eff_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp expDate;

    // 同步状态
    @Column(name = "status")
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    @Column(name = "status_cd")
    private String statusCd;

    // 操作人名称
    @Column(name = "oper_name")
    private String operName;

    // 操作时间
    @Column(name = "oper_date")
    @UpdateTimestamp
    @JsonIgnore
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator",updatable = false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false,updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    // 职业类别
    @Column(name = "vocation_code")
    private String vocationCode;

    // 籍贯详址
    @Column(name = "native_info_addr")
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    @Column(name = "service_place_code")
    private String servicePlaceCode;

    public void copy(CorrectPerson source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}