package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 重点青少年->重点青少年
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class BizTeenagerBaseinfoDTO implements Serializable {

    // ID，uuid()赋值
    private String teenId;

    // 身份证号
    private String identityNum;

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

    // 籍贯
    private String nativeInfo;

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

    // 服务处所
    private String serviceAddr;

    // 联系方式
    private String contact;

    // 户籍地
    private String registeredPlace;

    // 户籍门（楼）详址
    private String registeredAddr;

    // 现住地
    private String residence;

    // 现住门（楼）详址
    private String residenceAddr;

    // 人员类型
    private String peopleType;

    // 家庭情况
    private String homeSitu;

    // 监护人公民身份号码
    private String guardianIdno;

    // 监护人姓名
    private String guardianName;

    // 与监护人关系
    private String guardianRela;

    // 监护人联系方式
    private String guardianMobile;

    // 监护人居住详址
    private String guardianAddr;

    // 是否违法犯罪:1是0否
    private Integer ifIllegal;

    // 违法犯罪情况
    private String illegalSitu;

    // 帮扶人姓名
    private String helpeName;

    // 帮扶人联系方式
    private String helpeMobile;

    // 帮扶手段
    private String helpeMethod;

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

    // 监护人居住地址的省市县编码
    private String guardianAddrcode;
}