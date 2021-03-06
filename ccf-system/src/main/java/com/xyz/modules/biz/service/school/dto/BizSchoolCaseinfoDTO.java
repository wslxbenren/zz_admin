package com.xyz.modules.biz.service.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
 * 学校周边涉及师生安全案（事）件
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class BizSchoolCaseinfoDTO implements Serializable {

    // ID，uuid()赋值
    private String caseId;

    // 学校编码
    private String schoolCode;

    // 学校名称
    private String schoolName;

    // 案（事）件编号
    private String caseCode;

    // 案（事）件类型
    private String caseType;

    // 发生日期

    private Timestamp happenTime;

    // 发案地
    private String happenAddrcode;

    // 发生地点
    private String happenAddr;

    // 案（事）件性质
    private String caseNature;

    // 案（事）件情况
    private String caseInfo;

    // 主犯（嫌疑人）证件代码
    private String princcardType;

    // 主犯（嫌疑人）证件号码
    private String princcardCode;

    // 主犯（嫌疑人）姓名
    private String principalName;

    // 是否破案
    private Integer ifSolve;

    // 作案人数
    private Integer crimeNum;

    // 在逃人数
    private Integer onrunNum;

    // 抓捕人数
    private Integer captureNum;

    // 案件侦破情况
    private String solveInfo;

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