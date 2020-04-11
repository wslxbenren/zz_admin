package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 社会治安管理->寄递物流安全管理
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class BizSecurLogisticsDTO implements Serializable {

    // ID，uuid()赋值
    private String logisId;

    // 工商执照注册号（统一社会信用代码）
    private String creditCode;

    // 寄递物流企业名称
    private String logientityName;

    // 企业所在地6位编码
    private String entityaddrCode;

    // 企业详址
    private String entityaddr;

    // 企业联系方式
    private String entityPhone;

    // 企业负责人姓名
    private String entityleadName;

    // 企业负责人联系方式
    private String entityleadMobile;

    // 登记注册类型
    private String regisType;

    // 控股情况
    private String holdings;

    // 经营范围
    private String businessScope;

    // 企业类型
    private String entityType;

    // 服务品牌
    private String serviceBrand;

    // 从业人员数量
    private Integer practiNum;

    // 监控摄像机数量
    private Integer cameraNum;

    // X光机数量
    private Integer xrayNum;

    // 是否落实100%先验视后封箱
    private Integer ifPriori;

    // 是否落实100%寄递实名制
    private Integer ifRealname;

    // 是否落实100%X光机安检
    private Integer ifSecucheck;

    // 经度
    private Double lng;

    // 纬度
    private Double lat;

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