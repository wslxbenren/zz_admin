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

    //字典等值
    @Query(type = Query.Type.EQUAL)
    private String addr;

    @Query(type = Query.Type.EQUAL)
    private String grage;

    @Query(type = Query.Type.EQUAL)
    private String centerCode;

    @Query(type = Query.Type.IN)
    private String deptId;

    @Query(type = Query.Type.INNER_LIKE)
    private String centerName;

    @Query(type = Query.Type.INNER_LIKE)
    private String username;

    // 多字段模糊
//    @Query(blurry = "username")
//    private String blurry;

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