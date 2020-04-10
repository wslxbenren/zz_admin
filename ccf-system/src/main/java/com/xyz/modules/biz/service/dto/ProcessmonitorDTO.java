package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class ProcessmonitorDTO implements Serializable {

    // ID，uuid()赋值
    private String processId;

    // 事件编号，跟事件基本信息关联
    private String eventCode;

    // 处置环节
    private String disposalLink;

    // 处理单位
    private String disposalUnit;

    // 处理时间
    private Timestamp disposalTime;

    // 处理人
    private String disposalName;

    // 处理意见
    private String disposalOpinion;

    // 核查人
    private String checkName;

    // 核查结果
    private String checkResult;

    // 核查意见
    private String checklOpinion;

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