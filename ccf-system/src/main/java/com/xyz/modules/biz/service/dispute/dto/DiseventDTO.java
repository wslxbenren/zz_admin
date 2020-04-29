package com.xyz.modules.biz.service.dispute.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 矛盾纠纷排查化解管理->矛盾纠纷排查事件
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class DiseventDTO implements Serializable {

    // ID，uuid()赋值
    private String eventId;

    // 事件编号
    private String eventCode;

    // 事件名称
    private String eventName;

    // 发生日期

    private Timestamp happenDate;

    // 发生地点
    private String happenAddr;
//    private String happenAddrStr;


    // 事件规模 字典
    private String eventSize;
    private String eventSizeStr;

    // 事件类别 字典
    private String eventType;
    private String eventTypeStr;

    // 涉及人数
    private Integer involvNum;

    // 事件简述
    private String eventInfo;

    // 涉及单位  字典
    private String involvUnit;
    private String involvUnitStr;

    // 主要当事人证件代码
    private String princardType;

    // 主要当事人证件号码
    private String princardCode;

    // 主要当事人姓名
    private String prinName;

    // 主要当事人性别 字典
    private String prinSex;
    private String prinSexStr;


    // 主要当事人民族 字典
    private String prinNation;
    private String prinNationStr;

    // 主要当事人学历
    private String prinEducation;
    private String prinEducationStr;

    // 主要当事人人员类别
    private String prinPertype;
    private String prinPertypeStr;

    // 主要当事人居住地省市县编码  字典
    private String residence;
    private String residenceStr;

    // 主要当事人居住详址
    private String residenceAddr;

    // 生效时间

    private Timestamp effDate;

    // 失效时间

    private Timestamp expDate;

    // 同步状态
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效 字典
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

    // 单位编码,所属单位，后续可用于权限管理 字典
    private String unitCode;
    private String unitCodeStr;
}