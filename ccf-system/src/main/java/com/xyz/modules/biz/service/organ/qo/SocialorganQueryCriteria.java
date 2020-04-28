package com.xyz.modules.biz.service.organ.qo;

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
@ApiModel("非公有制经济组织及社会组织-> 社会组织")
public class SocialorganQueryCriteria{
    // 时间
    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> createTime;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
    private List<String> updateTime;

    // 原型查询条件
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "社会组织名称")
    private String socialorganName;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "统一社会信用代码")
    private String creditCode;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "登记证号")
    private String regisCode;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "登记管理机关代码")
    private String regisunitCode;

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