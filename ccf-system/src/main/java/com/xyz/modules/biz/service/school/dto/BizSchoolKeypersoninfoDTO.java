package com.xyz.modules.biz.service.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 学校及周边-> 学校周边重点人员信息表
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class BizSchoolKeypersoninfoDTO implements Serializable {

    // ID，uuid()赋值
    private String keyId;

    // 学校编码
    private String schoolCode;

    // 学校名称
    private String schoolName;

    // 公民身份证号码
    private String identityNum;

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

    // 籍贯
    private String nativeInfo;

    // 籍贯详址
    private String nativeAddr;

    // 婚姻状况
    private String marriageFlag;

    // 政治面貌
    private String partyFlag;

    // 学历
    private String educationBg;

    // 宗教信仰
    private String faithType;

    // 职业类别
    private String vocationCode;

    // 职业
    private String vocation;

    // 服务处所省市县编码
    private String serviceAddrcode;

    // 服务处所
    private String serviceAddr;

    // 联系方式
    private String contact;

    // 户籍地
    private String registeredPlace;

    // 户籍门（楼）详址
    private String registeredAddr;

    // 现住地
    private String residence;

    // 现住门（楼）详址
    private String residenceAddr;

    // 是否关注
    private Integer ifFocus;

    // 危害程度
    private String hazardLevel;

    // 危害说明
    private String hazardInfo;

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