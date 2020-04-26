package com.xyz.modules.biz.service.org.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 综治组织及综治业务->综治中心
 * @author lx
 * @date 2020-04-08
 */
@Data
public class ManagecenterInfoDTO implements Serializable {

    // 主键
    private String id;

    // 综治中心代码
    private String centerCode;

    // 综治中心名称
    private String centerName;

    // 综治中心联系方式:手机号码或固定电话号码
    private String centerMobile;

    // 综治中心层级:字典
    private String grage;

    // 负责人姓名
    private String username;

    // 负责人编码
    private String usercode;

    // 负责人联系方式:手机号码或固定电话号码
    private String userMobile;

    // 组成单位*:可根据工作需要由各相关综治成员单位组成
    private String deptId;

    // 所在地:字典
    private String addr;

    // 所在地详址
    private String addrDetail;

    // 经度
    private Double lng;

    // 纬度
    private Double lat;

    // 创建人
    private String creator;

    // 创建时间

    private Timestamp createTime;

    // 修改人
    private String modifier;

    // 修改时间

    private Timestamp updateTime;

    // 所属单位
    private String unitCode;
    private String unitCodeStr;

    // 综治中心层级:字典
    private String grageStr;

    // 所在地:字典翻译后值
    private String addrStr;

    private String status;
    private String statusStr;

    private String statusCd;
    private String statusCdStr;
}