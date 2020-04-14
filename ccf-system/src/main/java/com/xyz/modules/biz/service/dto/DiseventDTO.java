package com.xyz.modules.biz.service.dto;

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

    // 事件规模
    private String eventSize;

    // 事件类别
    private String eventType;

    // 涉及人数
    private Integer involvNum;

    // 事件简述
    private String eventInfo;

    // 涉及单位
    private String involvUnit;

    // 主要当事人证件代码
    private String princardType;

    // 主要当事人证件号码
    private String princardCode;

    // 主要当事人姓名
    private String prinName;

    // 主要当事人性别
    private String prinSex;

    // 主要当事人民族
    private String prinNation;

    // 主要当事人学历
    private String prinEducation;

    // 主要当事人人员类别
    private String prinPertype;

    // 主要当事人居住地省市县编码
    private String residence;

    // 主要当事人居住详址
    private String residenceAddr;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    private String unitCode;
}