package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author dadovicn
* @date 2020-04-08
*/
@Data
public class LeftbehindDTO implements Serializable {

    // ID，uuid()赋值
    private String leftId;

    // 公民身份号码:编码应符合GB/T11643
    private String identityNum;

    // 姓名
    private String personName;

    // 曾用名
    private String usedName;

    // 性别:编码应符合GB/T2261.1
    private String personSex;

    // 出生日期:格式为“YYYYMMDD”
    private Timestamp dateBirth;

    // 民族:编码应符合GB/T3304
    private String nation;

    // 籍贯:编码应符合GB/T2260
    private String nativeInfo;

    // 婚姻状况:编码应符合GB/T2261.2
    private String marriageFlag;

    // 政治面貌:编码应符合GB/T4762
    private String partyFlag;

    // 学历:编码应符合GB/T4658
    private String educationBg;

    // 宗教信仰:编码应符合GA214.12
    private String faithType;

    // 职业类别:编码应符合GB/T6565
    private String vocationCode;

    // 职业
    private String vocation;

    // 服务处所
    private String serviceAddr;

    // 联系方式:手机号码或固定电话号码
    private String contact;

    // 户籍地:编码应符合GB/T2260
    private String registeredPlace;

    // 户籍门（楼）详址
    private String registeredAddr;

    // 现住地:编码应符合GB/T2260
    private String residence;

    // 现住门（楼）详址
    private String residenceAddr;

    // 健康状况
    private String healthy;

    // 个人年收入
    private String annualPerincome;

    // 人户一致标识:01：一致，02：不一致
    private String householdId;

    // 留守人员类型
    private String leftbehindType;

    // 家庭主要成员身份号码
    private String mainmemIdno;

    // 家庭主要成员姓名
    private String mainmemName;

    // 家庭主要成员健康状况
    private String mainmemHealth;

    // 与留守人员关系:编码应符合GB/T4761
    private String mainmemRela;

    // 家庭主要成员联系方式:手机号码或固定电话号码
    private String mainmemMobile;

    // 家庭主要成员工作详细地址
    private String mainmemAddr;

    // 家庭年收入
    private String annualIncome;

    // 困难及诉求
    private String demand;

    // 帮扶情况
    private String helpeComment;

    // 生效时间
    private Timestamp effDate;

    // 失效时间
    private Timestamp expDate;

    // 同步状态
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    private String statusCd;

    // 操作人名称
    private String operName;

    // 操作时间
    private Timestamp operDate;

    // 创建人
    private String creator;

    // 创建时间
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    private String unitCode;

    // 籍贯详址
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    private String servicePlaceCode;

    // 家庭主要成员居住地址省市县编码
    private String mainmemAddrcode;
}