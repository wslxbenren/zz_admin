package com.xyz.modules.biz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.modules.system.util.annotation.Dict;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author dadovicn
* @date 2020-04-08
*/
@Entity
@Data
@Table(name="biz_actual_leftbehind")
public class Leftbehind implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "left_id")
    private String leftId;

    // 公民身份号码:编码应符合GB/T11643
    @Column(name = "identity_num",unique = true)
    private String identityNum;

    // 姓名
    @Column(name = "person_name")
    private String personName;

    // 曾用名
    @Column(name = "used_name")
    private String usedName;

    // 性别:编码应符合GB/T2261.1
    @Column(name = "person_sex")
    private String personSex;

    // 出生日期:格式为“YYYYMMDD”
    @Column(name = "date_birth")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp dateBirth;

    // 民族:编码应符合GB/T3304
    @Dict(DictEnum.MIN_ZU)
    @Column(name = "nation")
    private String nation;

    // 籍贯:编码应符合GB/T2260
    @Column(name = "native_info")
    private String nativeInfo;

    // 婚姻状况:编码应符合GB/T2261.2
    @Dict(DictEnum.HYZK)
    @Column(name = "marriage_flag")
    private String marriageFlag;

    // 政治面貌:编码应符合GB/T4762
    @Dict(DictEnum.ZZMM)
    @Column(name = "party_flag")
    private String partyFlag;

    // 学历:编码应符合GB/T4658
    @Dict(DictEnum.XUE_LI)
    @Column(name = "education_bg")
    private String educationBg;

    // 宗教信仰:编码应符合GA214.12
    @Dict(DictEnum.ZJXY)
    @Column(name = "faith_type")
    private String faithType;

    // 职业类别:编码应符合GB/T6565
    @Column(name = "vocation_code")
    private String vocationCode;

    // 职业
    @Column(name = "vocation")
    private String vocation;

    // 服务处所
    @Column(name = "service_addr")
    private String serviceAddr;

    // 联系方式:手机号码或固定电话号码
    @Column(name = "contact")
    private String contact;

    // 户籍地:编码应符合GB/T2260
    @Dict(DictEnum.ADDRESS)
    @Column(name = "registered_place")
    private String registeredPlace;

    // 户籍门（楼）详址
    @Column(name = "registered_addr")
    private String registeredAddr;

    // 现住地:编码应符合GB/T2260
    @Dict(DictEnum.ADDRESS)
    @Column(name = "residence")
    private String residence;

    // 现住门（楼）详址
    @Column(name = "residence_addr")
    private String residenceAddr;

    // 健康状况
    @Column(name = "healthy")
    private String healthy;

    // 个人年收入
    @Column(name = "annual_perincome")
    private String annualPerincome;

    // 人户一致标识:01：一致，02：不一致
    @Column(name = "household_id")
    private String householdId;

    // 留守人员类型
    @Column(name = "leftbehind_type")
    private String leftbehindType;

    // 家庭主要成员身份号码
    @Column(name = "mainmem_idno")
    private String mainmemIdno;

    // 家庭主要成员姓名
    @Column(name = "mainmem_name")
    private String mainmemName;

    // 家庭主要成员健康状况
    @Column(name = "mainmem_health")
    private String mainmemHealth;

    // 与留守人员关系:编码应符合GB/T4761
    @Column(name = "mainmem_rela")
    private String mainmemRela;

    // 家庭主要成员联系方式:手机号码或固定电话号码
    @Column(name = "mainmem_mobile")
    private String mainmemMobile;

    // 家庭主要成员工作详细地址
    @Column(name = "mainmem_addr")
    private String mainmemAddr;

    // 家庭年收入
    @Column(name = "annual_income")
    private String annualIncome;

    // 困难及诉求
    @Column(name = "demand")
    private String demand;

    // 帮扶情况
    @Column(name = "helpe_comment")
    private String helpeComment;

    // 生效时间
    @Column(name = "eff_date",nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    // 籍贯详址
    @Column(name = "native_info_addr")
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    @Column(name = "service_place_code")
    private String servicePlaceCode;

    // 家庭主要成员居住地址省市县编码
    @Column(name = "mainmem_addrcode")
    private String mainmemAddrcode;

    public void copy(Leftbehind source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}