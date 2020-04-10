package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class PsychosisPersonDTO implements Serializable {

    // ID，uuid()赋值
    private String psychosisId;

    // 姓名
    private String personName;

    // 曾用名
    private String usedName;

    // 性别
    private String personSex;

    // 出生日期
    private Timestamp dateBirth;

    // 民族
    private String nation;

    // 身份证号
    private String identityNum;

    // 籍贯
    private String nativeInfo;

    // 婚姻状况
    private String marriageFlag;

    // 政治面貌
    private String partyFlag;

    // 文化程度
    private String eduLevel;

    // 宗教信仰
    private String faithType;

    // 职业
    private String vocation;

    // 服务场所
    private String servicePlace;

    // 联系方式
    private String contact;

    // 户籍地
    private String registeredPlace;

    // 户籍详细地址
    private String registeredAddress;

    // 现住地，跟现住地编码residence_code数据重复存储
    private String residence;

    // 现住地编码
    private String residenceCode;

    // 现住地详细地址
    private String residenceAddress;

    // 家庭经济情况
    private String sourceIncome;

    // 是否纳入低保
    private String isBasicliving;

    // 监护人身份证号
    private String guarderIdentity;

    // 监护人姓名
    private String guarderName;

    // 监护人联系方式
    private String guarderAddress;

    // 初次发病日期
    private Timestamp attackDate;

    // 诊断类型
    private String diagnoseType;

    // 有无肇祸史
    private String isTrouble;

    // 肇祸次数
    private Integer troubleTimes;

    // 上次肇祸日期
    private String lastTrouble;

    // 危险性评估等级
    private String riskLevel;

    // 治疗情况
    private String treatFlag;

    // 治疗医院名称
    private String reatHospital;

    // 实施住院治疗原因  ---多选项
    private String inhospitalReason;

    // 接受康复机构名称
    private String reviceUnion;

    // 参与管理人员  ---多选项
    private String joinManager;

    // 帮扶情况  ---多选项
    private String helpeFlag;

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

    // 职业类别
    private String vocationCode;

    // 籍贯详址
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    private String servicePlaceCode;
}