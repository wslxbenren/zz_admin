package com.xyz.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Set;

/**
* @author dadovicn
* @date 2020-02-07
*/
@Entity
@Data
@Table(name="t_suspected_personnel_info")
public class SuspectedPersonnelInfo implements Serializable {

    // 人员编号
    @Id
    @Column(name = "p_id")
    private String peId;

    // 姓名
    @Column(name = "p_name")
    private String peName;

    // 性别
    @Column(name = "p_sex")
    private String peSex;

    // 身份证号
    @Column(name = "p_idno")
    private String peIdno;

    // 年龄
    @Column(name = "p_age")
    private String peAge;

    // 联系电话
    @Column(name = "p_mobile")
    private String peMobile;

    // 常住地住址
    @Column(name = "p_alwaddr")
    private String peAlwaddr;

    // 现在郑住址
    @Column(name = "p_addr")
    private String peAddr;

    // 是否途径武汉或湖北,1是0否
    @Column(name = "if_hubei")
    private BigDecimal ifHubei;

    // 途径时间
    @Column(name = "way_time")
    private Timestamp wayTime;

    // 到达郑州时间
    @Column(name = "arrive_time")
    private Timestamp arriveTime;

    // 同行人员姓名：,分隔
    @Column(name = "peer_name")
    private String peerName;

    // 同行人关系说明
    @Column(name = "peer_info")
    private String peerInfo;

    // 同行人员联系方式
    @Column(name = "peer_mobile")
    private String peerMobile;

    // 同行人员身份证号
    @Column(name = "peer_idno")
    private String peerIdno;

    // 交通方式
    @Column(name = "transport_mode")
    private String transportMode;

    // 车次/班次/航班号/车牌号
    @Column(name = "train_no")
    private String trainNo;

    // 途中是否停留1是0否
    @Column(name = "if_stay")
    private BigDecimal ifStay;

    // 停留地点说明
    @Column(name = "stayaddr")
    private String stayaddr;

    // 居家隔离开始日期
    @Column(name = "stay_time")
    private Timestamp stayTime;

    // 家人是否有病例或是否接触过病例说明
    @Column(name = "contact_info")
    private String contactInfo;

    // 身体状况说明
    @Column(name = "physical_info")
    private String physicalInfo;

    // 体温是否正常1正常0异常
    @Column(name = "if_ok")
    private BigDecimal ifOk;

    // 其他说明
    @Column(name = "remark")
    private String remark;

    // 采取措施（是否已隔离或者解除）
    @Column(name = "measures")
    private String measures;

    // 录入人
    @Column(name = "entry_name")
    private String entryName;

    // 录入时间
    @Column(name = "entry_time")
    @CreationTimestamp
    private Timestamp entryTime;

    // 录入单位
    @Column(name = "entry_unit")
    private String entryUnit;

    // 是否安排排查人跟进1是0否
    @Column(name = "if_arrange")
    private BigDecimal ifArrange;

    // 安排时间
    @Column(name = "arr_time")
    private Timestamp arrTime;

    // 排查人
    @Column(name = "screening_name")
    private String screeningName;

    // 负责人
    @Column(name = "head_name")
    private String headName;

    // 责任领导
    @Column(name = "leader_name")
    private String leaderName;

    // 更新时间
    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

    @Column(name = "apply_status")
    private BigDecimal applyStatus;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "p_id")
    private Set<SuspectedPersonnelScreening> suspectedPersonnelScreenings;

    public void copy(SuspectedPersonnelInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}