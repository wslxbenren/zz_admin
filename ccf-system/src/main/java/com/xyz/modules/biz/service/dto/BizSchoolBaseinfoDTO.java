package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class BizSchoolBaseinfoDTO implements Serializable {

    // ID，uuid()赋值
    private String baseId;

    // 学校编码
    private String schoolCode;

    // 学校名称
    private String schoolName;

    // 学校地址省市县编码
    private String schoolAddrcode;

    // 学校地址
    private String schoolAddr;

    // 学校办学类型
    private String schoolType;

    // 学校所属主管教育行政部门
    private String competDept;

    // 在校学生人数
    private Integer schoolNum;

    // 校长姓名
    private String headmasterName;

    // 校长联系方式
    private String headmasterMobile;

    // 分管安全保卫责任人姓名
    private String guardchargeName;

    // 分管安全保卫责任人联系方式
    private String guardchargeMobile;

    // 安全保卫责任人姓名
    private String guardresponName;

    // 安全保卫责任人联系方式
    private String guardresponMobile;

    // 治安责任人姓名
    private String securityName;

    // 治安责任人联系方式
    private String securityMobile;

    // 安全保卫人数
    private Integer guardNum;

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