package com.xyz.modules.biz.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* @author lx
* @date 2020-04-08
*/
@Data
@ApiModel("综治组织及综治业务->综治中心")
public class ManagecenterInfoQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> updateTime;

    // 原型查询条件
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "综治中心名称")
    private String centerName;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "综治中心联系方式")
    private String centerMobile;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "综治中心代码")
    private String centerCode;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "负责人姓名")
    private String username;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "负责人联系方式")
    private String userMobile;

    //字典等值
    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "所在地:字典")
    private String addr;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "综治中心层级:字典")
    private String grage;

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