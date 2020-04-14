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
 * 功能模块：护路护线/护路护线基本信息
 */
@Entity
@Data
@DynamicUpdate
@Table(name="biz_route_convenientinfo")
public class Convenientinfo implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "con_id")
    private String conId;

    // 名称
    @Column(name = "route_name")
    private String routeName;

    // 路线编码
    @Column(name = "route_code")
    private String routeCode;

    // 路线类型
    @Column(name = "route_type")
    private String routeType;

    // 隶属单位名称
    @Column(name = "subordunit")
    private String subordunit;

    // 隶属单位省市县编码
    @Column(name = "subordunit_addrcode")
    private String subordunitAddrcode;

    // 隶属单位详址
    @Column(name = "subordunit_addr")
    private String subordunitAddr;

    // 隶属单位联系方式
    @Column(name = "subordunit_phone")
    private String subordunitPhone;

    // 隶属单位负责人姓名
    @Column(name = "subord_name")
    private String subordName;

    // 隶属单位负责人联系方式
    @Column(name = "subord_mobile")
    private String subordMobile;

    // 管辖单位名称
    @Column(name = "jurisdunit")
    private String jurisdunit;

    // 管辖单位省市县编码
    @Column(name = "jurisdunit_addrcode")
    private String jurisdunitAddrcode;

    // 管辖单位详址
    @Column(name = "jurisdunit_addr")
    private String jurisdunitAddr;

    // 管辖单位联系方式
    @Column(name = "jurisdunit_phone")
    private String jurisdunitPhone;

    // 分管治保负责人姓名
    @Column(name = "charge_name")
    private String chargeName;

    // 分管治保负责人联系方式
    @Column(name = "charge_mobile")
    private String chargeMobile;

    // 治安隐患情况
    @Column(name = "secuhidden_info")
    private String secuhiddenInfo;

    // 治安隐患等级
    @Column(name = "secuhidden_level")
    private String secuhiddenLevel;

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

    public void copy(Convenientinfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}