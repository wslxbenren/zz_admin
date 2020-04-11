package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* @author lx
* @date 2020-04-06
*/
@Data
@ApiModel("综治组织及总之业务->楼栋长信息")
public class BuildheadInfoQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "")
    private List<String> updateTime;

    // 字典项
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "")
    private String sex;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "")
    private String national;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "")
    private String politicalStatus;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "")
    private String educationBg;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "")
    private String addr;

    // 其他
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "")
    private String addrDetail;

    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    private List<String> unitCode;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "创建人id")
    private String creator;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "修改人id")
    private String modifier;
}