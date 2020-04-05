package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
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

    // 实施主体名称
    private String implementerName;

    // 实施主体层级:字典
    private String implementerGrage;

    // 政策种类:字典
    private String policyType;

    // 创建人
    private String creator;

    // 创建时间
    private Timestamp createTime;

    // 修改人
    private String modifier;

    // 修改时间
    private Timestamp updateTime;

    // 所属单位
    private String unitCode;
}