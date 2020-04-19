package com.xyz.modules.biz.service.org.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 综治组织及综治业务->综治领导责任制
 * @author dadovicn
 * @date 2020-04-05
 */
@Data
public class ManageleadresponsInfoDTO implements Serializable {

    // 主键
    private String id;

    // 被实施地区编码
    private String impledareaCode;

    // 被实施地区
    private String impledareaName;

    // 被实施地区层级:字典
    private String areaGrage;

    // 被实施地区层级:字典f翻译
    private String areaGrageStr;

    // 实施主体名称
    private String implementerName;

    // 实施主体层级:字典
    private String implementerGrage;

    // 实施主体层级:字典翻译
    private String implementerGrageStr;

    // 政策种类:字典
    private String policyType;

    // 政策种类:字典翻译
    private String policyTypeStr;

    // 创建人
    private String creator;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    // 修改人
    private String modifier;

    // 修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp updateTime;

    // 所属单位
    private String unitCode;

    // 所属单位  字典翻译
    private String unitCodeStr;

    private String status;
    private String statusStr;

    private String statusCd;
    private String statusCdStr;
}