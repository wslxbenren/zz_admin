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
@Table(name="biz_dispute_resolvinginfo")
public class Resolvinginfo implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "resolv_id")
    private String resolvId;

    // 事件编号，跟事件基本信息关联
    @Column(name = "event_code")
    private String eventCode;

    // 化解时限
    @Column(name = "resolv_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp resolvTime;

    // 化解方式
    @Column(name = "resolv_way")
    private String resolvWay;

    // 化解组织
    @Column(name = "resolv_organ")
    private String resolvOrgan;

    // 化解责任人姓名
    @Column(name = "respon_name")
    private String responName;

    // 化解责任人联系方式
    @Column(name = "respon_mobile")
    private String responMobile;

    // 化解是否成功
    @Column(name = "if_success")
    private Integer ifSuccess;

    // 化解情况
    @Column(name = "resolv_info")
    private String resolvInfo;

    // 考评日期
    @Column(name = "appraisal_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Timestamp appraisalTime;

    // 考评意见
    @Column(name = "appraisal_opinion")
    private String appraisalOpinion;

    // 经度
    @Column(name = "lng")
    private Double lng;

    // 纬度
    @Column(name = "lat")
    private Double lat;

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
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonIgnore
    @CreationTimestamp
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(Resolvinginfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}