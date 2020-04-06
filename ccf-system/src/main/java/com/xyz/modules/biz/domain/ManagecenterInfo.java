package com.xyz.modules.biz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Entity
@Data
@Table(name="biz_org_managecenter_info")
public class ManagecenterInfo implements Serializable {

    // 主键
    @Id
    @Column(name = "id")
    private String id;

    // 综治中心代码
    @Column(name = "center_code")
    private String centerCode;

    // 综治中心名称
    @Column(name = "center_name")
    private String centerName;

    // 综治中心联系方式:手机号码或固定电话号码
    @Column(name = "center_mobile")
    private String centerMobile;

    // 综治中心层级:字典
    @Column(name = "grage")
    private String grage;

    // 负责人姓名
    @Column(name = "username")
    private String username;

    // 负责人编码
    @Column(name = "usercode")
    private String usercode;

    // 负责人联系方式:手机号码或固定电话号码
    @Column(name = "user_mobile")
    private String userMobile;

    // 组成单位*:可根据工作需要由各相关综治成员单位组成
    @Column(name = "dept_id")
    private String deptId;

    // 所在地:字典
    @Column(name = "addr")
    private String addr;

    // 所在地详址
    @Column(name = "addr_detail")
    private String addrDetail;

    // 经度
    @Column(name = "lng")
    private Double lng;

    // 纬度
    @Column(name = "lat")
    private Double lat;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createTime;

    // 修改人
    @Column(name = "modifier")
    private String modifier;

    // 修改时间
    @Column(name = "update_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp updateTime;

    // 所属单位
    @Column(name = "unit_code")
    private String unitCode;

    public void copy(ManagecenterInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}