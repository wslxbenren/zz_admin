package com.xyz.modules.biz.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author dadovicn
* @date 2020-04-08
*/
@Entity
@Data
@Table(name="biz_actual_rentalhouse")
public class Rentalhouse implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "rent_id")
    private String rentId;

    // 房屋编号
    @Column(name = "house_code")
    private String houseCode;

    // 房屋名称
    @Column(name = "house_name")
    private String houseName;

    // 房屋地址
    @Column(name = "house_addr")
    private String houseAddr;

    // 建筑用途
    @Column(name = "constr_purpose")
    private String constrPurpose;

    // 建筑面积（平方米）
    @Column(name = "constr_area")
    private Double constrArea;

    // 证件代码:编码应符合GA/T517
    @Column(name = "card_type")
    private String cardType;

    // 证件号码
    @Column(name = "card_no")
    private String cardNo;

    // 房主姓名
    @Column(name = "homeowner_name")
    private String homeownerName;

    // 房主联系方式:手机号码或固定电话号码
    @Column(name = "homeowner_mobile")
    private String homeownerMobile;

    // 房主现居详址
    @Column(name = "homeowner_addr")
    private String homeownerAddr;

    // 隐患类型
    @Column(name = "hazard_type")
    private String hazardType;

    // 承租人公民身份号码:编码应符合GB11463
    @Column(name = "lessee_idno")
    private String lesseeIdno;

    // 承租人姓名
    @Column(name = "lessee_name")
    private String lesseeName;

    // 承租人联系方式:手机号码或固定电话号码
    @Column(name = "lessee_mobile")
    private String lesseeMobile;

    // 经度
    @Column(name = "lng")
    private Double lng;

    // 纬度
    @Column(name = "lat")
    private Double lat;

    // 生效时间
    @Column(name = "eff_date",nullable = false)
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date",nullable = false)
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
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    // 房屋地址省市县编码
    @Column(name = "house_addrcode")
    private String houseAddrcode;

    // 房主居住地址的省市县编码
    @Column(name = "homeowner_addrcode")
    private String homeownerAddrcode;

    public void copy(Rentalhouse source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}