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
    @ApiModelProperty(value = "创建时间范围查询格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "修改时间范围查询格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> updateTime;

    // 原型查询项
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "小区（单位）名称")
    private String villageName;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "楼栋名称")
    private String buildName;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "楼长姓名")
    private String headName;

    // 字典项
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "性别:编码应符合GB/T2261.1")
    private String sex;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "民族:编码应符合GB/T3304")
    private String national;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "政治面貌:编码应符合GB/T4762")
    private String politicalStatus;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "学历:编码应符合GB/T4658")
    private String educationBg;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "所在地:编码应符合GB/T2260")
    private String addr;

    // 其他
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "所在地详址")
    private String addrDetail;

    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    @ApiModelProperty(value = "单位编码,所属单位，后续可用于权限管理")
    private List<String> unitCode;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "创建人id")
    private String creator;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "修改人id")
    private String modifier;
}