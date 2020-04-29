package com.xyz.modules.biz.service.dispute.qo;

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
@ApiModel("矛盾纠纷排查化解管理->矛盾纠纷排查事件")
public class DiseventQueryCriteria{
    // 时间
//    @Query(type = Query.Type.BETWEEN)
//    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
//    private List<String> createTime;
//



    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "事件编号")
    private String identiteventCodeyNum;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "事件名称")
    private String eventName;


    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "发生时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> happenDate;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "事件规模")
    private String eventSize;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "事件类别")
    private String eventType;


    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "发生地点")
    private String happenAddr;

    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    private List<String> unitCode;

//
//    @Query(type = Query.Type.EQUAL)
//    @ApiModelProperty(value = "修改人id")
//    private String modifier;
}