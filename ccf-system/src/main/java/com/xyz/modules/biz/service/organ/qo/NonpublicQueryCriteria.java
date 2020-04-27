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
@ApiModel("非公有制经济组织及社会组织-> 非公有制经济组织")
public class NonpublicQueryCriteria{
//    // 时间
//    @Query(type = Query.Type.BETWEEN)
//    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
//    private List<String> createTime;
//
//    @Query(type = Query.Type.BETWEEN)
//    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
//    private List<String> updateTime;

    // 精确
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "企业名称")
    private String entityName;

    // 精确
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "工商执照注册号（统一社会信用代码）")
    private String creditCode;

    // 精确
    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "联系方式")
    private String entityPhone;

    // 审计字段
    @Query(type = Query.Type.IN)
    @JsonIgnore
    private List<String> unitCode;

//    @Query(type = Query.Type.EQUAL)
//    @ApiModelProperty(value = "创建人id")
//    private String creator;
//
//    @Query(type = Query.Type.EQUAL)
//    @ApiModelProperty(value = "修改人id")
//    private String modifier;
}