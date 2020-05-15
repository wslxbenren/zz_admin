package com.xyz.modules.biz.service.actual.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.modules.system.util.annotation.Dict;
import com.xyz.modules.system.util.annotation.FieldAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.NoArgsConstructor;
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
@Table(name = "biz_actual_floatpeople")
@DynamicUpdate
public class Floatpeople implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "float_id")
    @FieldAlias("id")
    private String floatId;

    // 公民身份号码:编码应符合GB/T11643
    @Column(name = "identity_num", unique = true)
    @FieldAlias("公民身份号码")
    private String identityNum;

    // 姓名
    @Column(name = "person_name")
    @FieldAlias("姓名")
    private String personName;

    // 曾用名
    @Column(name = "used_name")
    @FieldAlias("曾用名")
    private String usedName;

    // 性别:编码应符合GB/T2261.1
    @Column(name = "person_sex")
    @FieldAlias("性别")
    @Dict(DictEnum.XING_BIE)
    private String personSex;

    // 出生日期:格式为“YYYYMMDD”
    @Column(name = "date_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FieldAlias("出生日期")
    private Timestamp dateBirth;

    // 民族:编码应符合GB/T3304
    @Column(name = "nation")
    @FieldAlias("民族")
    @Dict(DictEnum.MIN_ZU)
    private String nation;

    // 籍贯:编码应符合GB/T2260
    @Column(name = "native_info")
    @FieldAlias("籍贯")
    @Dict(value =  DictEnum.ADDRESS,level = true )
    private String nativeInfo;

    // 婚姻状况:编码应符合GB/T2261.2
    @Column(name = "marriage_flag")
    @FieldAlias("婚姻状况")
    @Dict(DictEnum.HYZK)
    String marriageFlag;

    // 政治面貌:编码应符合GB/T4762
    @Column(name = "party_flag")
    @FieldAlias("政治面貌")
    @Dict(DictEnum.ZZMM)
    private String partyFlag;

    // 学历:编码应符合GB/T4658
    @Column(name = "education_bg")
    @FieldAlias("学历")
    @Dict(DictEnum.XUE_LI)
    private String educationBg;

    // 宗教信仰:编码应符合GA214.12
    @Column(name = "faith_type")
    @FieldAlias("宗教信仰")
    @Dict(DictEnum.ZJXY)
    private String faithType;

    // 职业类别:编码应符合GB/T6565
    @Column(name = "vocation_code")
    @FieldAlias("职业类别")
    @Dict(DictEnum.ZYLB)
    private String vocationCode;

    // 职业
    @Column(name = "vocation")
    @FieldAlias("职业")
    private String vocation;

    // 服务处所
    @Column(name = "service_addr")
    @FieldAlias("服务处所")
    private String serviceAddr;

    // 联系方式:手机号码或固定电话号码
    @Column(name = "contact")
    @FieldAlias("联系方式")
    private String contact;

    // 户籍地:编码应符合GB/T2260
    @Column(name = "registered_place")
    @FieldAlias("户籍地")
    @Dict(value = DictEnum.ADDRESS,level = true)
    private String registeredPlace;

    // 户籍门（楼）详址
    @Column(name = "registered_addr")
    @FieldAlias("户籍门")
    private String registeredAddr;

    // 现住地:编码应符合GB/T2260
    @Column(name = "residence")
    @FieldAlias("现住地")
    @Dict(value = DictEnum.ADDRESS,level = true)
    private String residence;

    // 现住门（楼）详址
    @Column(name = "residence_addr")
    @FieldAlias("现住门（楼）详址")
    private String residenceAddr;

    // 流入原因
    @Column(name = "into_cause")
    @FieldAlias("流入原因")
    @Dict(DictEnum.LRYY)
    private String intoCause;

    // 办证类型
    @Column(name = "card_type")
    @FieldAlias("办证类型")
    @Dict(DictEnum.BZLX)
    private String cardType;

    // 证件号码
    @Column(name = "card_no")
    @FieldAlias("证件号码")
    private String cardNo;

    // 登记日期:格式为“YYYYMMDD”
    @Column(name = "regis_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FieldAlias("登记日期")
    private Timestamp regisDate;

    // 证件到期日期:格式为“YYYYMMDD”
    @Column(name = "expiry_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FieldAlias("证件到期日期")
    private Timestamp expiryDate;

    // 住所类型
    @Column(name = "resid_type")
    @FieldAlias("住所类型")
    @Dict(DictEnum.ZSLX)
    private String residType;

    // 是否重点关注人员
    @Column(name = "if_import")
    @FieldAlias("是否重点关注人员")
    @Dict(DictEnum.EMPTY)
    private Integer ifImport;

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
    @Dict(DictEnum.ZSLX)
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    @Column(name = "status_cd")
    @FieldAlias("数据状态")
    @Dict(DictEnum.ZSLX)
    private String statusCd;

    // 操作人名称
    @Column(name = "oper_name")
    @FieldAlias("操作人名称")
    private String operName;

    // 操作时间
    @Column(name = "oper_date")
    @UpdateTimestamp
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldAlias("操作时间")
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator", updatable = false)
    @FieldAlias("创建人")
    private String creator;

    // 创建时间
    @Column(name = "create_time", nullable = false, updatable = false)
    @UpdateTimestamp
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldAlias("创建时间")
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    @FieldAlias("所属单位")
    private String unitCode;

    // 籍贯详址
    @Column(name = "native_info_addr")
    @FieldAlias("籍贯详址")
    private String nativeInfoAddr;

    // 服务处所的省市县编码
    @Column(name = "service_place_code")
    @Dict(value = DictEnum.ADDRESS,level = true)
    @FieldAlias("服务处所的省市县编码")

    private String servicePlaceCode;

    public void copy(Floatpeople source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }


}