package com.xyz.modules.biz.service.organ.entity;

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
@Table(name="biz_organ_nonpublic")
public class Nonpublic implements Serializable {

    // ID，uuid()赋值
    @Id
    @Column(name = "non_id")
    private String nonId;

    // 工商执照注册号（统一社会信用代码）
    @Column(name = "credit_code")
    private String creditCode;

    // 企业名称
    @Column(name = "entity_name")
    private String entityName;

    // 企业类别
    @Column(name = "entity_type")
    private String entityType;

    // 企业地址省市县编码
    @Column(name = "entity_addrcode")
    private String entityAddrcode;

    // 企业地址
    @Column(name = "entity_addr")
    private String entityAddr;

    // 企业联系方式
    @Column(name = "entity_phone")
    private String entityPhone;

    // 企业员工数
    @Column(name = "entity_num")
    private Integer entityNum;

    // 法定代表人证件代码
    @Column(name = "legalcard_type")
    private String legalcardType;

    // 法定代表人证件号码
    @Column(name = "legalcard_code")
    private String legalcardCode;

    // 法定代表人姓名
    @Column(name = "legalperson_name")
    private String legalpersonName;

    // 法定代表人联系方式
    @Column(name = "legalperson_mobile")
    private String legalpersonMobile;

    // 治保负责人姓名
    @Column(name = "supervisor_name")
    private String supervisorName;

    // 治保负责人联系方式
    @Column(name = "supervisor_mobile")
    private String supervisorMobile;

    // 是否危化企业
    @Column(name = "if_dangerous")
    private Integer ifDangerous;

    // 安全隐患类型
    @Column(name = "safetrouble_type")
    private String safetroubleType;

    // 关注程度
    @Column(name = "attention")
    private String attention;

    // 是否具备建立中共党组织条件:1是0否
    @Column(name = "if_condition")
    private Integer ifCondition;

    // 是否有中共党组织:1是0否
    @Column(name = "if_organ")
    private Integer ifOrgan;

    // 中共党员数量
    @Column(name = "organ_num")
    private Integer organNum;

    // 是否有工会:1是0否
    @Column(name = "if_union")
    private Integer ifUnion;

    // 工会会员数量
    @Column(name = "union_num")
    private Integer unionNum;

    // 是否有共青团组织:1是0否
    @Column(name = "if_youthleague")
    private Integer ifYouthleague;

    // 共青团团员数量
    @Column(name = "youthleague_num")
    private Integer youthleagueNum;

    // 是否有妇联组织:1是0否
    @Column(name = "if_womenfeder")
    private Integer ifWomenfeder;

    // 妇女数量
    @Column(name = "womenfeder_num")
    private Integer womenfederNum;

    // 经度
    @Column(name = "lng")
    private Double lng;

    // 纬度
    @Column(name = "lat")
    private Double lat;

    // 生效时间
    @Column(name = "eff_date", insertable = false ,updatable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Timestamp effDate;

    // 失效时间
    @Column(name = "exp_date", insertable = false,updatable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    @JsonIgnore
    private Timestamp operDate;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time",nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(Nonpublic source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}