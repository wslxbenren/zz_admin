package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class SuspectinfoDTO implements Serializable {

    // ID，uuid()赋值
    private String suspId;

    // 嫌疑人员编号
    private Integer suspectCode;

    // 案件编号
    private String caseCode;

    // 证件代码
    private String cardType;

    // 证件代码  字典翻译
    private String cardTypeStr;

    // 证件号码
    private String cardCode;

    // 姓名
    private String personName;

    // 曾用名
    private String usedName;

    // 性别
    private String personSex;

    // 性别   字典翻译
    private String personSexStr;

    // 出生日期
    private Timestamp dateBirth;

    // 国籍（地区）
    private String country;

    // 国籍（地区）  字典翻译
    private String countryStr;

    // 民族
    private String nation;

    // 民族   字典翻译
    private String nationStr;

    // 籍贯
    private String nativeInfo;

    // 籍贯   字典翻译
    private String nativeInfoStr;

    // 籍贯详址
    private String nativeAddr;

    // 婚姻状况
    private String marriageFlag;

    // 婚姻状况   字典翻译
    private String marriageFlagStr;

    // 政治面貌
    private String partyFlag;

    // 政治面貌   字典翻译
    private String partyFlagStr;

    // 学历
    private String educationBg;

    // 学历   字典翻译
    private String educationBgStr;

    // 宗教信仰
    private String faithType;

    // 宗教信仰   字典翻译
    private String faithTypeStr;

    // 职业类别
    private String vocationCode;

    // 职业类别   字典翻译
    private String vocationCodeStr;

    // 职业
    private String vocation;

    // 服务处所省市县编码
    private String serviceAddrcode;

    // 服务处所
    private String serviceAddr;

    // 户籍地
    private String registeredPlace;

    // 户籍地   字典翻译
    private String registeredPlaceStr;

    // 户籍门（楼）详址
    private String registeredAddr;

    // 现住地
    private String residence;

    // 现住门（楼）详址
    private String residenceAddr;

    // 是否为严重精神障碍患者:1是0否
    private Integer ifPsychopath;

    // 是否为未成年人a:1是0否，未成年人为不满18周岁
    private Integer ifMinors;

    // 是否为青少年a:1是0否，青少年为已满6周岁不满25周岁
    private Integer ifTeenagers;

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

    // 单位编码,所属单位，后续可用于权限管理  字典翻译
    private String unitCodeStr;
}