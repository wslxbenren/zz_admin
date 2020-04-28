package com.xyz.modules.biz.service.secur.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
@ApiModel("社会治安管理->命案受害人")
public class VictiminfoQueryCriteria{
//    // 时间
//    @Query(type = Query.Type.BETWEEN)
//    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
//    private List<String> createTime;
//
//    @Query(type = Query.Type.BETWEEN)
//    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
//    private List<String> operDate;

    // 精确
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "姓名")
    private String personName;

    // 精确
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "案件编号")
    private String caseCode;

    @ApiModelProperty(value = "现住地:编码应符合GB/T2260")
    private String residence;

    @Query(type = Query.Type.IN, propName = "residence")
    private List<String> residenceWithDownGrade;


    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    @ApiModelProperty(value = "单位编码,所属单位")
    private List<String> unitCode;

//    @Query(type = Query.Type.EQUAL)
//    @ApiModelProperty(value = "创建人id")
//    private String creator;
//
//    @Query(type = Query.Type.EQUAL)
//    @ApiModelProperty(value = "修改人id")
//    private String operName;
}