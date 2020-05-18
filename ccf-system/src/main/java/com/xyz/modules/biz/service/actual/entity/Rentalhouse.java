package com.xyz.modules.biz.service.actual.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.modules.system.util.annotation.Dict;
import com.xyz.modules.system.util.annotation.FieldAlias;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author dadovicn
 * @date 2020-04-08
 */
@Entity
@Data
@Table(name = "biz_actual_rentalhouse")
@DynamicUpdate
public class Rentalhouse implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "rent_id")
    @FieldAlias("Id")
    private String rentId;

    // 房屋编号
    @Column(name = "house_code")
    @FieldAlias("房屋编号")
    private String houseCode;

    // 房屋名称
    @Column(name = "house_name")
    @FieldAlias("房屋名称")
    private String houseName;

    // 房屋地址
    @Column(name = "house_addr")
    @FieldAlias("房屋地址")
    private String houseAddr;

    // 建筑用途
    @Column(name = "constr_purpose")
    @FieldAlias("建筑用途")
    @Dict(DictEnum.JZYT)
    private String constrPurpose;

    // 出租用途
    @Column(name = "rental_purposes")
    @FieldAlias("出租用途")
    @Dict(DictEnum.CZYT)
    private String rentalPurposes;

    // 建筑面积（平方米）
    @Column(name = "constr_area")
    @FieldAlias("建筑面积（平方米）")
    private Double constrArea;

    // 证件代码:编码应符合GA/T517
    @Column(name = "card_type")
    @Dict(DictEnum.ZJDM)
    @FieldAlias("证件代码")
    private String cardType;

    // 证件号码
    @Column(name = "card_no", unique = true)
    @FieldAlias("证件号码")
    private String cardNo;

    // 房主姓名
    @Column(name = "homeowner_name")
    @FieldAlias("房主姓名")
    private String homeownerName;

    // 房主联系方式:手机号码或固定电话号码
    @Column(name = "homeowner_mobile")
    @FieldAlias("房主联系方式")
    private String homeownerMobile;

    // 房主现居详址
    @Column(name = "homeowner_addr")
    @FieldAlias("房主现居详址")
    private String homeownerAddr;

    // 隐患类型
    @Column(name = "hazard_type")
    @FieldAlias("隐患类型")
    @Dict(DictEnum.YHLX)
    private String hazardType;

    // 承租人公民身份号码:编码应符合GB11463
    @Column(name = "lessee_idno")
    @FieldAlias("承租人公民身份号码")
    private String lesseeIdno;

    // 承租人姓名
    @Column(name = "lessee_name")
    @FieldAlias("承租人姓名")
    private String lesseeName;

    // 承租人联系方式:手机号码或固定电话号码
    @Column(name = "lessee_mobile")
    @FieldAlias("承租人联系方式")
    private String lesseeMobile;

    // 经度
    @Column(name = "lng")
    @FieldAlias("经度")
    private Double lng;

    // 纬度
    @Column(name = "lat")
    @FieldAlias("纬度")
    private Double lat;

    // 生效时间
    @Column(name = "eff_date", insertable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldAlias("生效时间")
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date", insertable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldAlias("失效时间")
    private Timestamp expDate;

    // 同步状态
    @Column(name = "status")
    @FieldAlias("同步状态")
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    @Column(name = "status_cd")
    @FieldAlias("数据状态")
    @Dict(DictEnum.SJZT)
    private String statusCd;

    // 操作人名称
    @Column(name = "oper_name")
    @FieldAlias("操作人名称")
    private String operName;

    // 操作时间
    @Column(name = "oper_date")
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    @FieldAlias("操作时间")
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator", updatable = false)
    @FieldAlias("创建人")
    private String creator;

    // 创建时间
    @Column(name = "create_time", updatable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    @FieldAlias("创建时间")
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    @FieldAlias("所属单位")
    private String unitCode;

    // 房屋地址省市县编码
    @Column(name = "house_addrcode")
    @FieldAlias("房屋地址省市县编码")
    @Dict(DictEnum.ADDRESS)
    private String houseAddrcode;

    // 房主居住地址的省市县编码
    @Column(name = "homeowner_addrcode")
    @FieldAlias("房主居住地址的省市县编码")
    @Dict(DictEnum.ADDRESS)
    private String homeownerAddrcode;

    public void copy(Rentalhouse source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}