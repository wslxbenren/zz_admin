package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 护路护线->护路护线基本信息表
 * @author xjh
 * @date 2020-04-10
 */
@Data
public class ConvenientinfoDTO implements Serializable {

    // ID，uuid()赋值
    private String conId;

    // 名称
    private String routeName;

    // 路线编码
    private String routeCode;

    // 路线类型
    private String routeType;

    // 路线类型   字典翻译
    private String routeTypeStr;

    // 隶属单位名称
    private String subordunit;

    // 隶属单位省市县编码
    private String subordunitAddrcode;

    // 隶属单位详址
    private String subordunitAddr;

    // 隶属单位联系方式
    private String subordunitPhone;

    // 隶属单位负责人姓名
    private String subordName;

    // 隶属单位负责人联系方式
    private String subordMobile;

    // 管辖单位名称
    private String jurisdunit;

    // 管辖单位省市县编码
    private String jurisdunitAddrcode;

    // 管辖单位详址
    private String jurisdunitAddr;

    // 管辖单位联系方式
    private String jurisdunitPhone;

    // 分管治保负责人姓名
    private String chargeName;

    // 分管治保负责人联系方式
    private String chargeMobile;

    // 治安隐患情况
    private String secuhiddenInfo;

    // 治安隐患等级
    private String secuhiddenLevel;

    // 治安隐患等级  字典翻译
    private String secuhiddenLevelStr;

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

    // 单位编码,所属单位，后续可用于权限管理  字典翻译
    private String unitCodeStr;
}