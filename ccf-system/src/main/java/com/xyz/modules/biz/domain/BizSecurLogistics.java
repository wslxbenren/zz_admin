package com.xyz.modules.biz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Entity
@Data
@DynamicUpdate
@Table(name="biz_secur_logistics")
public class BizSecurLogistics implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "logis_id")
    private String logisId;

    // 工商执照注册号（统一社会信用代码）
    @Column(name = "credit_code")
    private String creditCode;

    // 寄递物流企业名称
    @Column(name = "logientity_name")
    private String logientityName;

    // 企业所在地6位编码
    @Column(name = "entityaddr_code")
    private String entityaddrCode;

    // 企业详址
    @Column(name = "entityaddr")
    private String entityaddr;

    // 企业联系方式
    @Column(name = "entity_phone")
    private String entityPhone;

    // 企业负责人姓名
    @Column(name = "entitylead_name")
    private String entityleadName;

    // 企业负责人联系方式
    @Column(name = "entitylead_mobile")
    private String entityleadMobile;

    // 登记注册类型
    @Column(name = "regis_type")
    private String regisType;

    // 控股情况
    @Column(name = "holdings")
    private String holdings;

    // 经营范围
    @Column(name = "business_scope")
    private String businessScope;

    // 企业类型
    @Column(name = "entity_type")
    private String entityType;

    // 服务品牌
    @Column(name = "service_brand")
    private String serviceBrand;

    // 从业人员数量
    @Column(name = "practi_num")
    private Integer practiNum;

    // 监控摄像机数量
    @Column(name = "camera_num")
    private Integer cameraNum;

    // X光机数量
    @Column(name = "xray_num")
    private Integer xrayNum;

    // 是否落实100%先验视后封箱
    @Column(name = "if_priori")
    private Integer ifPriori;

    // 是否落实100%寄递实名制
    @Column(name = "if_realname")
    private Integer ifRealname;

    // 是否落实100%X光机安检
    @Column(name = "if_secucheck")
    private Integer ifSecucheck;

    // 经度
    @Column(name = "lng")
    private Double lng;

    // 纬度
    @Column(name = "lat")
    private Double lat;

    // 生效时间
    @Column(name = "eff_date",nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator",updatable=false)
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false,updatable=false)
    @CreationTimestamp
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(BizSecurLogistics source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}