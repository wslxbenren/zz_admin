package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 综治组织及综治业务-> 重特大案（事）件信息
 * @author dadovicn
 * @date 2020-04-05
 */
@Data
public class MajorcaseInfoDTO implements Serializable {

    // 主键
    private String id;

    // 案（事）件编码
    private String caseCode;

    // 案（事）件名称
    private String caseName;

    // 发生日期:格式为“YYYYMMDD”
    private Timestamp occurDate;

    // 发生地:编码应符合GB/T2260
    private String occurAddr;

    // 发生地:编码应符合GB/T2260 字典翻译
    private String occurAddrStr;

    // 发生地详址
    private String occurAddrdetail;

    // 案（事）件分级:字典
    private String caseGrage;

    // 案（事）件分级名称:字典翻译
    private String caseGrageStr;

    // 案（事）件类型:字典
    private String caseType;

    // 案（事）件类型:字典翻译
    private String caseTypeStr;

    // 案（事）件情况
    private String caseInfo;

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

    // 所属单位 字典翻译
    private String unitCodeStr;
}