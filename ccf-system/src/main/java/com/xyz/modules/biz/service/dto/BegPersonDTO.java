package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class BegPersonDTO implements Serializable {

    // ID，uuid()赋值
    private String begId;

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

    // 现住地
    private String residence;

    // 现住地编码
    private String residenceCode;

    // 现住地详细地址
    private String residenceAddress;

    // 所属类别
    private String belongType;

    // 是否属于留守儿童
    private String leftoverChild;

    // 学习状况
    private String studyComment;

    // 未入学原因
    private String unstudyReason;

    // 是否享受低保补助
    private String isEnsure;

    // 辍学原因
    private String stopstudyReason;

    // 辍学其他原因
    private String stopstudyOther;

    // 是否有犯罪史
    private String isPedigree;

    // 就业情况
    private String workComment;

    // 就业其他情况
    private String workOther;

    // 是否有不良行为
    private String badBehaviour;

    // 不良行为其他情况
    private String behaviourOther;

    // 未就业原因
    private String unworkReason;

    // 未就业其他原因
    private String unworkOther;

    // 监护情况
    private String guardFlag;

    // 监护情况原因
    private String guardReason;

    // 其他需要说明问题
    private String otherComments;

    // 家庭主要经济来源
    private String sourceIncome;

    // 其他经济来源
    private String sourceOther;

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