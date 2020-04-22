package com.xyz.modules.biz.service.special.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 刘鑫
 * @date 2020-04-10
 * 特殊人群->刑满释放人员
 */
@Data
public class ReleasedPersonDTO implements Serializable {

    // ID，uuid()赋值
    private String releasedId;

    // 姓名
    private String personName;

    // 曾用名
    private String usedName;

    // 性别
    private String personSex;
    private String personSexStr;

    // 出生日期
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp dateBirth;

    // 民族
    private String nation;
    private String nationStr;

    // 身份证号
    private String identityNum;

    // 籍贯
    private String nativeInfo;
    private String nativeInfoStr;

    // 婚姻状况
    private String marriageFlag;
    private String marriageFlagStr;

    // 政治面貌
    private String partyFlag;
    private String partyFlagStr;

    // 文化程度
    private String eduLevel;
    private String eduLevelStr;

    // 宗教信仰
    private String faithType;
    private String faithTypeStr;

    // 职业
    private String vocation;
    private String vocationStr;

    // 服务场所
    private String servicePlace;

    // 联系方式
    private String contact;

    // 户籍地
    private String registeredPlace;
    private String registeredPlaceStr;

    // 户籍详细地址
    private String registeredAddress;

    // 现住地，跟现住地编码residence_code数据重复存储
    private String residence;
    private String residenceStr;

    // 现住地编码
    private String residenceCode;

    // 现住地详细地址
    private String residenceAddress;

    // 是否有犯罪史
    private String isPedigree;

    // 是否有犯罪史
    private String isPedigreeStr;

    // 原罪名
    private String chargeComments;

    // 原判刑期
    private String prisonTerm;

    // 服刑场所
    private String detainUnion;

    // 释放结束日期
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp prisonEnddate;

    // 危险性评估类型
    private String riskType;
    private String riskTypeStr;

    // 衔接日期
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp joinDate;

    // 衔接情况
    private String joinFlag;
    private String joinFlagStr;

    // 安置日期
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp arrangeDate;

    // 安置情况
    private String arrangeFlag;
    private String arrangeFlagStr;

    // 未安置原因
    private String unarrangeReason;

    // 帮扶情况
    private String helpeComment;


    // 是否重新犯罪
    private String isAgain;

    // 是否重新犯罪
    private String isAgainStr;

    // 重新犯罪罪名
    private String againCharge;

    // 生效时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp effDate;

    // 失效时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp expDate;

    // 同步状态
    private String status;
    private String statusStr;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    private String statusCd;
    private String statusCdStr;

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

    // 职业类别
    private String vocationCode;
    private String vocationCodeStr;

    // 籍贯详址
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    private String servicePlaceCode;
    private String servicePlaceCodeStr;
}