package com.xyz.modules.biz.service.special.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xyz.annotation.Query;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.List;


/**
 * @author 刘鑫
 * @date 2020-04-10
 * 特殊人群->特殊人群社区矫正人群
 */
@Data
public class CorrectPersonDTO implements Serializable {

    // ID，uuid()赋值
    private String correctId;

    // 姓名
    private String personName;

    // 曾用名
    private String usedName;

    // 性别
    private String personSex;
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

    // 现住地，跟现住地编码residence_code数据重复存储
    private String residence;

    // 现住地编码
    private String residenceCode;

    // 现住地详细地址
    private String residenceAddress;

    // 社区矫正人员编号
    private String correctCode;

    // 原羁押场所
    private String detainUnion;

    // 矫正类别
    private String correctType;
    // 字典翻译:矫正类别
    private String correctTypeStr;

    // 案件类别
    private String caseType;
    // 字典翻译：案件类别
    private String caseTypeStr;

    // 具体罪名
    private String chargeComments;

    // 原判刑期
    private String prisonTerm;

    // 原判刑开始日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp prisonBeagindate;

    // 原判刑结束日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp prisonEnddate;

    // 矫正开始日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp correctBeagindate;

    // 矫正结束日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp correctEnddate;

    // 接收方式
    private String reviceFlag;
    // 字典翻译：接收方式
    private String reviceFlagStr;

    // 四史情况--可多选，|号隔开
    private String sishiFlag;

    // 是否是惯犯
    private String isRecidivist;

    // 三涉情况--可多选，|号隔开
    private String sansheFlag;

    // 是否建立矫正小组
    private String isTeam;

    // 矫正小组人员情况--可多选，|号隔开
    private String teamGuys;

    // 矫正解除类型
    private String correctRemove;

    // 是否脱管
    private String isBreakmanage;

    // 脱管原因说明
    private String breakmanageReason;

    // 检查监督情况
    private String checkComments;

    // 脱管纠正情况
    private String breakmanageCorrect;

    // 是否漏管
    private String isOmit;

    // 漏管原因
    private String omitReason;

    // 检查漏管情况
    private String checkOmit;

    // 漏管纠正情况
    private String omitCorrect;

    // 奖惩情况
    private String bonusPenalty;

    // 刑罚变更执行情况
    private String prisonChange;

    // 是否重新犯罪
    private String isAgain;

    // 重新犯罪罪名
    private String againCharge;

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
    // 字典翻译：职业类别
    private String vocationCodeStr;

    // 籍贯详址
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    private String servicePlaceCode;


}