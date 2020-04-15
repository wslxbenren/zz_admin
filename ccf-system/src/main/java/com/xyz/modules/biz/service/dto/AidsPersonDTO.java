package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 特殊人群->艾滋病危险人群
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class AidsPersonDTO implements Serializable {

    // ID，uuid()赋值
    private String aidsId;

    // 姓名
    private String personName;

    // 曾用名
    private String usedName;

    // 性别
    private String personSex;
    //  字典翻译：性别
    private String personSexStr;

    // 出生日期
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Timestamp dateBirth;

    // 民族
    private String nation;
    //  字典翻译：民族
    private String nationStr;

    // 身份证号
    private String identityNum;

    // 籍贯
    private String nativeInfo;
    //  字典翻译：籍贯
    private String nativeInfoStr;

    // 婚姻状况
    private String marriageFlag;
    //  字典翻译：婚姻状况
    private String marriageFlagStr;

    // 政治面貌
    private String partyFlag;
    //  字典翻译：婚姻状况
    private String partyFlagStr;

    // 文化程度
    private String eduLevel;
    //  字典翻译：文化程度
    private String eduLevelStr;

    // 宗教信仰
    private String faithType;
    //  字典翻译：宗教信仰
    private String faithTypeStr;

    // 职业
    private String vocation;


    // 服务场所
    private String servicePlace;

    // 联系方式
    private String contact;

    // 户籍地
    private String registeredPlace;
    // 字典翻译：户籍地
    private String registeredPlaceStr;

    // 户籍详细地址
    private String registeredAddress;

    // 感染途径
    private String routesInfection;
    // 字典翻译：感染途径
    private String routesInfectionStr;

    // 是否有犯罪史
    private String isPedigree;

    // 犯罪情况说明
    private String pedigreeComments;

    // 案件类别
    private String caseType;
    // 字典翻译：案件类别
    private String caseTypeStr;

    // 关注类型
    private String takeType;
    // 字典翻译：关注类型
    private String takeTypeStr;

    // 帮扶情况
    private String helpComments;
    // 字典翻译:帮扶情况
    private String helpCommentsStr;

    // 帮扶人姓名
    private String helperName;

    // 帮扶人联系方式
    private String helperAddress;

    // 收治情况
    private String detainType;
    // 字典翻译:收治情况
    private String detainTypeStr;

    // 收治机构名称
    private String detainUnion;

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
    private String unitCodeStr;

    // 职业类别
    private String vocationCode;
    private String vocationCodeStr;

    // 现住地
    private String residence;

    // 现住门（楼）详址
    private String residenceAddr;

    // 籍贯详址
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    private String servicePlaceCode;
}