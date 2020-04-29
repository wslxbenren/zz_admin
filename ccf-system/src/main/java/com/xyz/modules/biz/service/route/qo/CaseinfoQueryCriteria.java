package com.xyz.modules.biz.service.route.qo;

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
@ApiModel("护路护线 -> 涉及线、路案（事）件")
public class CaseinfoQueryCriteria{

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "案发日期: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> happenTime;

    // 精确
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "跟线路基本表主键关联")
    private String conId;

    //caseType案件类型
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "案件性质")
    private String caseNature;

    //caseName
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "案件名称")
    private String caseName;

    //线路名称
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "线路名称")
    private String routeName;

    //案件编码
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "案件编码")
    private String caseCode;

    //主犯人姓名 principalName
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "主犯人姓名")
    private String principalName;





    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    @ApiModelProperty(value = "单位编码,所属单位")
    private List<String> unitCode;

}