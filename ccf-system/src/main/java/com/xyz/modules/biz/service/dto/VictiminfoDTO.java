package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class VictiminfoDTO implements Serializable {

    // ID，uuid()赋值
    private String vicId;

    // 受害人编号
    private String victimCode;

    // 案件编号
    private String caseCode;

    // 证件代码
    private String cardType;

    // 证件号码
    private String cardCode;

    // 姓名
    private String personName;

    // 性别
    private String personSex;

    // 出生日期
    private Timestamp dateBirth;

    // 国籍（地区）
    private String country;

    // 民族
    private String nation;

    // 籍贯
    private String nativeInfo;

    // 籍贯详址
    private String nativeAddr;

    // 婚姻状况
    private String marriageFlag;

    // 政治面貌
    private String partyFlag;

    // 学历
    private String educationBg;

    // 宗教信仰
    private String faithType;

    // 职业类别
    private String vocationCode;

    // 职业
    private String vocation;

    // 服务处所省市县编码
    private String serviceAddrcode;

    // 服务处所
    private String serviceAddr;

    // 户籍地
    private String registeredPlace;

    // 户籍门（楼）详址
    private String registeredAddr;

    // 现住地
    private String residence;

    // 现住门（楼）详址
    private String residenceAddr;

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
}