package com.xyz.modules.biz.service.actual.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author lx
 * @date 2020-04-08
 * 实有人口->户籍人口
 */
@Data
public class RegistpeopleDTO implements Serializable {

    // ID，uuid()赋值
    private String regisId;

    // 身份证号:编码应符合GB/T11643
    private String identityNum;

    // 姓名
    private String personName;

    // 曾用名
    private String usedName;

    // 性别:编码应符合GB/T2261.1
    private String personSex;
    // 性别:翻译后值
    private String personSexStr;

    // 出生日期:格式为“YYYYMMDD”
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp dateBirth;

    // 民族:编码应符合GB/T3304
    private String nation;
    // 民族:翻译后值
    private String nationStr;

    // 籍贯:编码应符合GB/T2260
    private String nativeInfo;
    // 籍贯:翻译后值
    private String nativeInfoStr;

    // 婚姻状况:编码应符合GB/T2261.2
    private String marriageFlag;
    // 婚姻状况:翻译后值
    private String marriageFlagStr;

    // 政治面貌:编码应符合GB/T4762
    private String partyFlag;
    // 政治面貌:翻译后值
    private String partyFlagStr;

    // 学历:编码应符合GB/T4658
    private String educationBg;
    // 学历:翻译后值
    private String educationBgStr;

    // 宗教信仰:编码应符合GA214.12
    private String faithType;
    // 宗教信仰:翻译后值
    private String faithTypeStr;

    // 职业类别:编码应符合GB/T6565
    private String vocationCode;
    // 职业类别:翻译后值
    private String vocationCodeStr;

    // 职业
    private String vocation;

    // 服务处所
    private String serviceAddr;

    // 联系方式:手机号码或固定电话号码
    private String contact;

    // 户籍地:编码应符合GB/T2260
    private String registeredPlace;
    // 户籍地:翻译后值
    private String registeredPlaceStr;

    // 户籍门（楼）详址
    private String registeredAddr;

    // 现住地:编码应符合GB/T2260
    private String residence;
    private String residenceStr;

    // 现住门（楼）详址
    private String residenceAddr;

    // 人户一致标识:01：一致,02：不一致
    private String householdId;

    // 户号
    private String doorNo;

    // 户主公民身份号码:编码应符合GB/T11643
    private String househeadIdno;

    // 户主姓名
    private String househeadName;

    // 与户主关系:编码应符合GB/T4761
    private String househeadRela;
    private String househeadRelaStr;

    // 户主联系方式:手机号码或固定电话号码
    private String househeadMobile;

    // 生效时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp effDate;

    // 失效时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp expDate;

    // 同步状态
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    private String statusCd;

    // 操作人名称
    private String operName;

    // 操作时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp operDate;

    // 创建人
    private String creator;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    private String unitCode;
    private String unitCodeStr;
    // 籍贯详址
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    private String servicePlaceCode;
}