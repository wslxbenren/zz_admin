package com.xyz.modules.biz.service.secur.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xjh
 * @date 2020-04-10
 */
@Data
@ApiModel("社会治安管理->命案基本信息")
public class BizSecurHomicidebaseinfoQueryCriteria{

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "案件发生开始日期: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> crimeDate;


    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "侦查终结日期: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> endDate;

    // 精确q
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "案件名称")
    private String caseName;


    // 精确q
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "案件编号-----嫌疑人查询、受害人查询 需传案件编号查询对应的表")
    private String caseCode;

    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    @ApiModelProperty(value = "单位编码,所属单位")
    private List<String> unitCode;

}