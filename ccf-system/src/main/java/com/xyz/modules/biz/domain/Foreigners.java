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
@Table(name="biz_actual_foreigners")
public class Foreigners implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "fore_id")
    private String foreId;

    // 外文姓:编码应符合GB/T11643
    @Column(name = "lastname")
    private String lastname;

    // 外文名
    @Column(name = "firstname")
    private String firstname;

    // 中文姓名
    @Column(name = "chinesename")
    private String chinesename;

    // 性别:编码应符合GB/T2261.1
    @Column(name = "person_sex")
    private String personSex;

    // 出生日期:格式为“YYYYMMDD”
    @Column(name = "date_birth")
    private Timestamp dateBirth;

    // 国籍（地区）:编码应符合GB/T2659
    @Column(name = "country")
    private String country;

    // 宗教信仰:编码应符合GA214.12
    @Column(name = "faith_type")
    private String faithType;

    // 证件代码:编码应符合GA/T517
    @Column(name = "card_type")
    private String cardType;

    // 证件号码
    @Column(name = "card_no")
    private String cardNo;

    // 证件有效期:格式为“YYYYMMDD”
    @Column(name = "valid_date")
    private Timestamp validDate;

    // 联系方式:手机号码或固定电话号码
    @Column(name = "contact")
    private String contact;

    // 来华目的
    @Column(name = "goal_in")
    private String goalIn;

    // 职业类别:编码应符合GB/T6565
    @Column(name = "vocation_code")
    private String vocationCode;

    // 职业
    @Column(name = "vocation")
    private String vocation;

    // 服务处所
    @Column(name = "service_addr")
    private String serviceAddr;

    // 现住地:编码应符合GB/T2260
    @Column(name = "residence")
    private String residence;

    // 现住门（楼）详址
    @Column(name = "residence_addr")
    private String residenceAddr;

    // 抵达日期:格式为“YYYYMMDD”
    @Column(name = "arrival_date")
    private Timestamp arrivalDate;

    // 预计离开日期:格式为“YYYYMMDD”
    @Column(name = "plan_leave")
    private Timestamp planLeave;

    // 是否重点关注人员
    @Column(name = "if_import")
    private Integer ifImport;

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

    // 服务处所的省市县编码
    @Column(name = "service_place_code")
    private String servicePlaceCode;

    public void copy(Foreigners source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}