package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 矛盾纠纷排查化解管理->矛盾纠纷排查化解信息
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class ResolvinginfoDTO implements Serializable {

    // ID，uuid()赋值
    private String resolvId;

    // 事件编号，跟事件基本信息关联
    private String eventCode;

    // 化解时限
    private Timestamp resolvTime;

    // 化解方式
    private String resolvWay;

    // 化解组织
    private String resolvOrgan;

    // 化解责任人姓名
    private String responName;

    // 化解责任人联系方式
    private String responMobile;

    // 化解是否成功
    private Integer ifSuccess;

    // 化解情况
    private String resolvInfo;

    // 考评日期
    private Timestamp appraisalTime;

    // 考评意见
    private String appraisalOpinion;

    // 经度
    private Double lng;

    // 纬度
    private Double lat;

    // 生效时间
    private Timestamp effDate;

    // 失效时间
    private Timestamp expDate;

    // 同步状态
    private String status;

    // 数据状态 10是保存待提交 12是生效状态 22是失效
    private String statusCd;

    // 操作人名称
    private String operName;

    // 操作时间
    private Timestamp operDate;

    // 创建人
    private String creator;

    // 创建时间
    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理
    private String unitCode;
}