package com.xyz.modules.biz.service.actual.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.modules.system.util.annotation.Dict;
import com.xyz.modules.system.util.annotation.FieldAlias;
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
 * @author dadovicn
 * @date 2020-04-08
 * 功能模块 ： 实有人口/留守人员信息
 */
@Entity
@Data
@Table(name = "biz_actual_leftbehind")
@DynamicUpdate
public class Leftbehind implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "left_id")
    private String leftId;

    // 公民身份号码:编码应符合GB/T11643
    @Column(name = "identity_num", unique = true)
    @FieldAlias("公民身份号码")
    private String identityNum;

    // 姓名
    @Column(name = "person_name")
    @FieldAlias("姓名")
    private String personName;

    // 曾用名
    @Column(name = "used_name")
    @FieldAlias("曾用名")
    private String usedName;

    // 性别:编码应符合GB/T2261.1
    @Column(name = "person_sex")
    @FieldAlias("性别")
    @Dict(DictEnum.XING_BIE)
    private String personSex;

    // 出生日期:格式为“YYYYMMDD”
    @Column(name = "date_birth")
    @FieldAlias("出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp dateBirth;

    // 民族:编码应符合GB/T3304
    @FieldAlias("民族")
    @Dict(DictEnum.MIN_ZU)
    @Column(name = "nation")
    private String nation;

    // 籍贯:编码应符合GB/T2260
    @Column(name = "native_info")
    @FieldAlias("籍贯")
    @Dict(DictEnum.ADDRESS)
    private String nativeInfo;

    // 婚姻状况:编码应符合GB/T2261.2
    @FieldAlias("婚姻状况")
    @Dict(DictEnum.HYZK)
    @Column(name = "marriage_flag")
    private String marriageFlag;

    // 政治面貌:编码应符合GB/T4762
    @FieldAlias("政治面貌")
    @Dict(DictEnum.ZZMM)
    @Column(name = "party_flag")
    private String partyFlag;

    // 学历:编码应符合GB/T4658
    @FieldAlias("学历")
    @Dict(DictEnum.XUE_LI)
    @Column(name = "education_bg")
    private String educationBg;

    // 宗教信仰:编码应符合GA214.12
    @FieldAlias("宗教信仰")
    @Dict(DictEnum.ZJXY)
    @Column(name = "faith_type")
    private String faithType;

    // 职业类别:编码应符合GB/T6565
    @Column(name = "vocation_code")
    @FieldAlias("职业类别")
    @Dict(DictEnum.ZYLB)
    private String vocationCode;

    // 职业
    @Column(name = "vocation")
    @FieldAlias("职业")
    private String vocation;

    // 服务处所
    @Column(name = "service_addr")
    @FieldAlias("服务处所")
    private String serviceAddr;

    // 联系方式:手机号码或固定电话号码
    @Column(name = "contact")
    @FieldAlias("联系方式")
    private String contact;

    // 户籍地:编码应符合GB/T2260
    @FieldAlias("户籍地")
    @Dict(DictEnum.ADDRESS)
    @Column(name = "registered_place")
    private String registeredPlace;

    // 户籍门（楼）详址
    @Column(name = "registered_addr")
    @FieldAlias("户籍门（楼）详址")
    private String registeredAddr;

    // 现住地:编码应符合GB/T2260
    @FieldAlias("现住地")
    @Dict(DictEnum.ADDRESS)
    @Column(name = "residence")
    private String residence;

    // 现住门（楼）详址
    @Column(name = "residence_addr")
    @FieldAlias("现住门（楼）详址")
    private String residenceAddr;

    // 健康状况
    @Column(name = "healthy")
    @FieldAlias("健康状况")
    @Dict(DictEnum.JKZK)
    private String healthy;

    // 个人年收入
    @Column(name = "annual_perincome")
    @FieldAlias("个人年收入")
    private String annualPerincome;

    // 人户一致标识:01：一致，02：不一致
    @Column(name = "household_id")
    @FieldAlias("人户一致标识")
    @Dict(DictEnum.RHYZBS)
    private String householdId;

    // 留守人员类型
    @Column(name = "leftbehind_type")
    @FieldAlias("留守人员类型")
    @Dict(DictEnum.LSRYLX)
    private String leftbehindType;

    // 家庭主要成员身份号码
    @Column(name = "mainmem_idno")
    @FieldAlias("家庭主要成员身份号码")
    private String mainmemIdno;

    // 家庭主要成员姓名
    @Column(name = "mainmem_name")
    @FieldAlias("家庭主要成员姓名")
    private String mainmemName;

    // 家庭主要成员健康状况
    @Column(name = "mainmem_health")
    @FieldAlias("家庭主要成员健康状况")
    @Dict(DictEnum.JKZK)
    private String mainmemHealth;

    // 与留守人员关系:编码应符合GB/T4761
    @Column(name = "mainmem_rela")
    @FieldAlias("与留守人员关系")
    @Dict(DictEnum.YHZGX)
    private String mainmemRela;

    // 家庭主要成员联系方式:手机号码或固定电话号码
    @Column(name = "mainmem_mobile")
    @FieldAlias("家庭主要成员联系方式")
    private String mainmemMobile;

    // 家庭主要成员工作详细地址
    @Column(name = "mainmem_addr")
    @FieldAlias("家庭主要成员工作详细地址")
    private String mainmemAddr;

    // 家庭年收入
    @Column(name = "annual_income")
    @FieldAlias("家庭年收入")
    private String annualIncome;

    // 困难及诉求
    @Column(name = "demand")
    @FieldAlias("困难及诉求")
    private String demand;

    // 帮扶情况
    @Column(name = "helpe_comment")
    @FieldAlias("帮扶情况")
    private String helpeComment;

    // 生效时间
    @Column(name = "eff_date", insertable = false)
    @FieldAlias("生效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date", insertable = false)
    @FieldAlias("失效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp expDate;

    // 同步状态
    @Column(name = "status")
    @FieldAlias("同步状态")
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    @Column(name = "status_cd")
    @FieldAlias("数据状态")
    @Dict(DictEnum.SJZT)
    private String statusCd;

    // 操作人名称
    @Column(name = "oper_name")
    @FieldAlias("操作人名称")
    private String operName;

    // 操作时间
    @Column(name = "oper_date")
    @UpdateTimestamp
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldAlias("操作时间")
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator", updatable = false)
    @FieldAlias("创建人")
    private String creator;

    // 创建时间
    @Column(name = "create_time", updatable = false)
    @CreationTimestamp
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldAlias("创建时间")
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    @FieldAlias("所属单位")
    private String unitCode;

    // 籍贯详址
    @Column(name = "native_info_addr")
    @FieldAlias("籍贯详址")
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    @Column(name = "service_place_code")
    @FieldAlias("服务处所的省市县编码")
    @Dict(DictEnum.ADDRESS)
    private String servicePlaceCode;

    // 家庭主要成员居住地址省市县编码
    @Column(name = "mainmem_addrcode")
    @FieldAlias("家庭主要成员居住地址省市县编码")
    @Dict(DictEnum.ADDRESS)
    private String mainmemAddrcode;

    public void copy(Leftbehind source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}