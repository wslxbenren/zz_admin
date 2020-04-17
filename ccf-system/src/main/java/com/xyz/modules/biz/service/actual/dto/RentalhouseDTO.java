package com.xyz.modules.biz.service.actual.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 实有人口->出租房屋基础信息
 * @author lx
 * @date 2020-04-08
 */
@Data
public class RentalhouseDTO implements Serializable {

    // ID，uuid()赋值
    private String rentId;

    // 房屋编号
    private String houseCode;

    // 房屋名称
    private String houseName;

    // 房屋地址
    private String houseAddr;

    // 建筑用途
    private String constrPurpose;
    private String constrPurposeStr;

    // 出租用途
    private String rentalPurposes;
    private String rentalPurposesStr;

    // 建筑面积（平方米）
    private Double constrArea;

    // 证件代码:编码应符合GA/T517
    private String cardType;
    private String cardTypeStr;

    // 证件号码
    private String cardNo;

    // 房主姓名
    private String homeownerName;

    // 房主联系方式:手机号码或固定电话号码
    private String homeownerMobile;

    // 房主现居详址
    private String homeownerAddr;

    // 隐患类型
    private String hazardType;
    private String hazardTypeStr;

    // 承租人公民身份号码:编码应符合GB11463
    private String lesseeIdno;

    // 承租人姓名
    private String lesseeName;

    // 承租人联系方式:手机号码或固定电话号码
    private String lesseeMobile;

    // 经度
    private Double lng;

    // 纬度
    private Double lat;

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

    // 房屋地址省市县编码
    private String houseAddrcode;

    // 房主居住地址的省市县编码
    private String homeownerAddrcode;
}