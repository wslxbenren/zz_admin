package com.xyz.modules.biz.service.org.qo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* @author xjh
* @date 2020-04-05
 * 功能模块 ： 综治组织/重大案件事件
*/
@Data
@ApiModel("综治组织及综治业务-> 重特大案（事）件信息")
public class MajorcaseInfoQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> updateTime;

    // 原型查询条件
    @ApiModelProperty(value = "案（事）件名称")
    @Query(type = Query.Type.INNER_LIKE)
    private String caseName;

    @ApiModelProperty(value = "发生日期： 格式[yyyy-MM-dd]")
    private List<String> occurDate;

    //字典项
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "发生地:编码应符合GB/T2260")
    private String occurAddr;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "案（事）件分级:字典")
    private String caseGrage;

    @ApiModelProperty(value = "案（事）件类型:字典")
    @Query(type = Query.Type.EQUAL)
    private String caseType;

    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    @ApiModelProperty(value = "单位编码,所属单位")
    private List<String> unitCode;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "创建人id")
    private String creator;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "修改人id")
    private String modifier;
}