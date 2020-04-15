package com.xyz.modules.biz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Entity
@Data
@Table(name="biz_dispute_disevent")
public class Disevent implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "event_id")
    private String eventId;

    // 事件编号
    @Column(name = "event_code")
    private String eventCode;

    // 事件名称
    @Column(name = "event_name")
    private String eventName;

    // 发生日期
    @Column(name = "happen_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp happenDate;

    // 发生地点
    @Column(name = "happen_addr")
    private String happenAddr;

    // 事件规模
    @Column(name = "event_size")
    private String eventSize;

    // 事件类别
    @Column(name = "event_type")
    private String eventType;

    // 涉及人数
    @Column(name = "involv_num")
    private Integer involvNum;

    // 事件简述
    @Column(name = "event_info")
    private String eventInfo;

    // 涉及单位
    @Column(name = "involv_unit")
    private String involvUnit;

    // 主要当事人证件代码
    @Column(name = "princard_type")
    private String princardType;

    // 主要当事人证件号码
    @Column(name = "princard_code")
    private String princardCode;

    // 主要当事人姓名
    @Column(name = "prin_name")
    private String prinName;

    // 主要当事人性别
    @Column(name = "prin_sex")
    private String prinSex;

    // 主要当事人民族
    @Column(name = "prin_nation")
    private String prinNation;

    // 主要当事人学历
    @Column(name = "prin_education")
    private String prinEducation;

    // 主要当事人人员类别
    @Column(name = "prin_pertype")
    private String prinPertype;

    // 主要当事人居住地省市县编码
    @Column(name = "residence")
    private String residence;

    // 主要当事人居住详址
    @Column(name = "residence_addr")
    private String residenceAddr;

    // 生效时间
    @Column(name = "eff_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp expDate;

    // 同步状态
    @Column(name = "status")
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    @Column(name = "status_cd")
    private String statusCd;

    // 操作人名称
    @Column(name = "oper_name")
    private String operName;

    // 操作时间
    @Column(name = "oper_date")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(Disevent source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}