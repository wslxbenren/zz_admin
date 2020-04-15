package com.xyz.modules.biz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 特殊人群刑满释放人员基础信息表
 */
@Entity
@Data
@Table(name="biz_special_released_person")
@DynamicUpdate
public class ReleasedPerson implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "released_id")
    private String releasedId;

    // 姓名
    @Column(name = "person_name",nullable = false)
    private String personName;

    // 曾用名
    @Column(name = "used_name",nullable = false)
    private String usedName;

    // 性别
    @Column(name = "person_sex")
    private String personSex;

    // 出生日期
    @Column(name = "date_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp dateBirth;

    // 民族
    @Column(name = "nation",nullable = false)
    private String nation;

    // 身份证号
    @Column(name = "identity_num",unique = true,nullable = false)
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

    // 是否有犯罪史
    @Column(name = "is_pedigree",nullable = false)
    private String isPedigree;

    // 原罪名
    @Column(name = "charge_comments",nullable = false)
    private String chargeComments;

    // 原判刑期
    @Column(name = "prison_term",nullable = false)
    private String prisonTerm;

    // 服刑场所
    @Column(name = "detain_union",nullable = false)
    private String detainUnion;

    // 释放结束日期
    @Column(name = "prison_enddate",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp prisonEnddate;

    // 危险性评估类型
    @Column(name = "risk_type",nullable = false)
    private String riskType;

    // 衔接日期
    @Column(name = "join_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp joinDate;

    // 衔接情况
    @Column(name = "join_flag",nullable = false)
    private String joinFlag;

    // 安置日期
    @Column(name = "arrange_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp arrangeDate;

    // 安置情况
    @Column(name = "arrange_flag",nullable = false)
    private String arrangeFlag;

    // 未安置原因
    @Column(name = "unarrange_reason")
    private String unarrangeReason;

    // 帮扶情况
    @Column(name = "helpe_comment")
    private String helpeComment;

    // 是否重新犯罪
    @Column(name = "is_again",nullable = false)
    private String isAgain;

    // 重新犯罪罪名
    @Column(name = "again_charge")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator",updatable = false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false,updatable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
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

    public void copy(ReleasedPerson source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}