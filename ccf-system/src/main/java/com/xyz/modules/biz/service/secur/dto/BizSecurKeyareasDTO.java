package com.xyz.modules.biz.service.secur.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 社会治安管理->重点地区排查整治
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class BizSecurKeyareasDTO implements Serializable {

    // ID，uuid()赋值
    private String keyId;

    // 治安重点地区
    private String securityKarea;

    // 治安突出问题
    private String outproblem;

    // 治安突出问题 字典翻译
    private String outproblemStr;

    // 涉及区域类型
    private String areaType;

    // 涉及区域类型  字典翻译
    private String areaTypeStr;

    // 涉及区域范围
    private String areaScope;

    // 整治牵头单位
    private String leadunitCode;

    // 整治参与单位
    private String partunitCode;

    // 整治牵头单位负责人姓名
    private String leadunitPername;

    // 整治牵头单位负责人联系方式
    private String leadunitPermobile;

    // 整改时限

    private Timestamp limitTime;

    // 整治期间破获刑事案件数
    private String criminalcaseNum;

    // 整治期间查处治安案件数
    private String securitycaseNum;

    // 整治情况
    private String regulation;


    // 效果评估
    private String evaluation;
    private String evaluationStr;

    // 生效时间

    private Timestamp effDate;

    // 失效时间

    private Timestamp expDate;

    // 同步状态
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    private String statusCd;
    private String statusCdStr;

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