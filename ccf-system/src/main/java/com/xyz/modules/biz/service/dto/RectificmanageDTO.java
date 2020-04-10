package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class RectificmanageDTO implements Serializable {

    // ID，uuid()赋值
    private String rectId;

    // 跟线路基本表主键关联
    private String conId;

    // 线路名称
    private String routeName;

    // 行动类型
    private String actionType;

    // 行动主题
    private String actionTheme;

    // 行动地
    private String actionAddr;

    // 行动日期
    private Timestamp actionTime;

    // 出动人数
    private Integer outNum;

    // 工作指标
    private String workIndic;

    // 工作内容
    private String workInfo;

    // 责任人
    private String responName;

    // 责任单位
    private String responUnit;

    // 协办单位
    private String assistUnit;

    // 出动人员
    private Integer assistOutnum;

    // 整治成效
    private String reguresults;

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