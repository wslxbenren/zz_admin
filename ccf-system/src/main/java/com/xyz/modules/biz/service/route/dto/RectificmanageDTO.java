package com.xyz.modules.biz.service.route.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * @author 刘鑫
 * @date 2020-04-10
 * 护路护线->线路专项整治管理
 */
@Data
public class RectificmanageDTO implements Serializable {

    // ID，uuid()赋值
    private String rectId;

    // 跟线路基本表主键关联
    private String conId;

    // 线路名称
    private String routeName;

    // 行动类型 字典
    private String actionType;
    private String actionTypeStr;


    // 行动主题
    private String actionTheme;

    // 行动地 字典
    private String actionAddr;
    private String actionAddrStr;

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

    // 责任单位 字典
    private String responUnit;
    private String responUnitStr;

    // 协办单位 字典
    private String assistUnit;
    private String assistUnitStr;

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

    // 数据状态 10是保存待提交 12是生效状态 22是失效 字典
    private String statusCd;
    private String statusCdStr;


    // 操作人名称
    private String operName;

    // 操作时间

    private Timestamp operDate;

    // 创建人
    private String creator;

    // 创建时间

    private Timestamp createTime;

    // 单位编码,所属单位，后续可用于权限管理 字典
    private String unitCode;
    private String unitCodeStr;

}