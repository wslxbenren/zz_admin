package com.xyz.modules.biz.service.dispute.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 邢家华
 * @date 2020-04-10
 * 矛盾纠纷排查化解管理->矛盾纠纷排查督办
 */
@Data
public class OverseeinfoDTO implements Serializable {

    // ID，uuid()赋值
    private String overseeId;

    // 事件编号，跟事件基本信息关联
    private String eventCode;

    // 督办单位
    private String overseeUnit;

    // 督办人
    private String overseeName;

    // 督办时间
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp overseeTime;

    // 督办意见
    private String overseeOpinion;

    // 督办材料
    private String overseeMaterial;

    // 备注
    private String remark;

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
}