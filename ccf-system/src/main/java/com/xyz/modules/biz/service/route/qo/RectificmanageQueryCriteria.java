package com.xyz.modules.biz.service.route.qo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
@ApiModel("护路护线->线路专项整治管理")
public class RectificmanageQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "行动日期: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> actionTime;

//    @Query(type = Query.Type.BETWEEN)
//    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
//    private List<String> updateTime;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "线路名称")
    private String routeName;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "行动类型")
    private String actionType;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "行动主题")
    private String actionTheme;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "行动地")
    private String actionAddr;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "职责人")
    private String responName;

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
//    private String modifier;
}