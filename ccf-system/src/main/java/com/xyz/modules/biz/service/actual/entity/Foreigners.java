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
 * @author xjh
 * @date 2020-04-08
 * 功能模块 ： 实有人口/境外人员信息
 */
@Entity
@Data
@Table(name = "biz_actual_foreigners")
@DynamicUpdate
public class Foreigners implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "fore_id")
    @FieldAlias("Id")
    private String foreId;

    // 外文姓:编码应符合GB/T11643
    @Column(name = "lastname")
    @FieldAlias("外文姓")
    private String lastname;

    // 外文名
    @Column(name = "firstname")
    @FieldAlias("外文名")
    private String firstname;

    // 中文姓名
    @Column(name = "chinesename")
    @FieldAlias("中文姓名")
    private String chinesename;

    // 性别:编码应符合GB/T2261.1
    @Column(name = "person_sex")
    @Dict(DictEnum.XING_BIE)
    @FieldAlias("性别")
    private String personSex;

    // 出生日期:格式为“YYYYMMDD”
    @Column(name = "date_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FieldAlias("出生日期")
    private Timestamp dateBirth;

    // 国籍（地区）:编码应符合GB/T2659
    @Column(name = "country")
    @Dict(DictEnum.GJ_DQ)
    @FieldAlias("国籍（地区）")
    private String country;

    // 宗教信仰:编码应符合GA214.12
    @Column(name = "faith_type")
    @Dict(DictEnum.ZJXY)
    @FieldAlias("宗教信仰")
    private String faithType;

    // 证件代码:编码应符合GA/T517
    @Column(name = "card_type")
    @Dict(DictEnum.ZJDM)
    @FieldAlias("证件代码")
    private String cardType;

    // 证件号码
    @Column(name = "card_no")
    @FieldAlias("证件号码")
    private String cardNo;

    // 证件有效期:格式为“YYYYMMDD”
    @Column(name = "valid_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FieldAlias("证件有效期")
    private Timestamp validDate;

    // 联系方式:手机号码或固定电话号码
    @Column(name = "contact")
    @FieldAlias("联系方式")
    private String contact;

    // 来华目的
    @Column(name = "goal_in")
    @FieldAlias("来华目的")
    @Dict(DictEnum.LHMD)
    private String goalIn;

    // 职业类别:编码应符合GB/T6565
    @Column(name = "vocation_code")
    @Dict(DictEnum.ZYLB)
    @FieldAlias("职业类别")
    private String vocationCode;

    // 职业
    @Column(name = "vocation")
    @FieldAlias("职业")
    private String vocation;

    // 服务处所
    @Column(name = "service_addr")
    @FieldAlias("服务处所")
    private String serviceAddr;

    // 现住地:编码应符合GB/T2260
    @Column(name = "residence")
    @Dict(DictEnum.ADDRESS)
    @FieldAlias("现住地")
    private String residence;

    // 现住门（楼）详址
    @Column(name = "residence_addr")
    @FieldAlias("现住门（楼）详址")
    private String residenceAddr;

    // 抵达日期:格式为“YYYYMMDD”
    @Column(name = "arrival_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FieldAlias("抵达日期")
    private Timestamp arrivalDate;

    // 预计离开日期:格式为“YYYYMMDD”
    @Column(name = "plan_leave")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FieldAlias("预计离开日期")
    private Timestamp planLeave;

    // 是否重点关注人员
    @Column(name = "if_import")
    @FieldAlias("是否重点关注人员")
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
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldAlias("操作时间")
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator", updatable = false)
    @FieldAlias("创建人")
    private String creator;

    // 创建时间
    @Column(name = "create_time", updatable = false)
    @CreationTimestamp
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldAlias("创建时间")
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    @FieldAlias("所属单位")
    private String unitCode;

    // 服务处所的省市县编码
    @Column(name = "service_place_code")
    @FieldAlias("服务处所的省市县编码")
    @Dict(DictEnum.ADDRESS)
    private String servicePlaceCode;

    public void copy(Foreigners source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}