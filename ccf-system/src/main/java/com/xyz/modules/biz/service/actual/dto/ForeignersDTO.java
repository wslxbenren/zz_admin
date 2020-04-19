package com.xyz.modules.biz.service.actual.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 实有人口-境外人员
 * @author dadovicn
 * @date 2020-04-08
 */
@Data
public class ForeignersDTO implements Serializable {

    // ID，uuid()赋值
    private String foreId;

    // 外文姓:编码应符合GB/T11643
    private String lastname;

    // 外文姓:编码应符合GB/T11643 字典翻译
    private String lastnameStr;

    // 外文名
    private String firstname;

    // 中文姓名
    private String chinesename;

    // 性别:编码应符合GB/T2261.1
    private String personSex;

    // 性别:编码应符合GB/T2261.1 字典翻译
    private String personSexStr;

    // 出生日期:格式为“YYYYMMDD”
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp dateBirth;

    // 国籍（地区）:编码应符合GB/T2659
    private String country;

    // 国籍（地区）:编码应符合GB/T2659 字典翻译
    private String countryStr;

    // 宗教信仰:编码应符合GA214.12
    private String faithType;

    // 宗教信仰:编码应符合GA214.12 字典翻译
    private String faithTypeStr;

    // 证件代码:编码应符合GA/T517
    private String cardType;

    // 证件代码:编码应符合GA/T517 字典翻译
    private String cardTypeStr;

    // 证件号码
    private String cardNo;

    // 证件有效期:格式为“YYYYMMDD”
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp validDate;

    // 联系方式:手机号码或固定电话号码
    private String contact;

    // 来华目的
    private String goalIn;

    // 职业类别:编码应符合GB/T6565
    private String vocationCode;

    // 职业类别:编码应符合GB/T6565 字典翻译
    private String vocationCodeStr;

    // 职业
    private String vocation;

    // 服务处所
    private String serviceAddr;

    // 现住地:编码应符合GB/T2260
    private String residence;

    // 现住地:编码应符合GB/T2260 字典翻译
    private String residenceStr;

    // 现住门（楼）详址
    private String residenceAddr;

    // 抵达日期:格式为“YYYYMMDD”
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp arrivalDate;

    // 预计离开日期:格式为“YYYYMMDD”
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp planLeave;

    // 是否重点关注人员
    private Integer ifImport;
    private String ifImportStr;

    // 生效时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp effDate;

    // 失效时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp expDate;

    // 同步状态
    private String status;
    private String statusStr;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    private String statusCd;
    private String statusCdStr;

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

    // 单位编码,所属单位，后续可用于权限管理   字典翻译
    private String unitCodeStr;

    // 服务处所的省市县编码
    private String servicePlaceCode;
}