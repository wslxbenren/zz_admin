package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class DrugPersonDTO implements Serializable {

    // ID，uuid()赋值
    private String drugId;

    // 姓名
    private String personName;

    // 曾用名
    private String usedName;

    // 性别
    private String personSex;

    // 出生日期
    private Timestamp dateBirth;

    // 民族
    private String nation;

    // 身份证号
    private String identityNum;

    // 籍贯
    private String nativeInfo;

    // 婚姻状况
    private String marriageFlag;

    // 政治面貌
    private String partyFlag;

    // 文化程度
    private String eduLevel;

    // 宗教信仰
    private String faithType;

    // 职业
    private String vocation;

    // 服务场所
    private String servicePlace;

    // 联系方式
    private String contact;

    // 户籍地
    private String registeredPlace;

    // 户籍详细地址
    private String registeredAddress;

    // 现住地，跟现住地编码residence_code数据重复存储
    private String residence;

    // 现住地编码
    private String residenceCode;

    // 现住地详细地址
    private String residenceAddress;

    // 初次发现日期
    private Timestamp findDate;

    // 管控情况
    private String manageType;

    // 管控人姓名
    private String managerName;

    // 管控人联系方式
    private String managerAddress;

    // 帮扶情况
    private String helpeComment;

    // 帮扶人姓名
    private String helperName;

    // 帮扶人联系方式
    private String helperAddress;

    // 是否有犯罪史
    private String isPedigree;

    // 犯罪情况
    private String crimeComment;

    // 吸毒原因
    private String drugReason;

    // 吸毒后果
    private String drugResult;

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

    // 职业类别
    private String vocationCode;

    // 籍贯详址
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    private String servicePlaceCode;
}