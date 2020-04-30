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
@ApiModel("护路护线-线路周边重点人员")
public class KeypersoninfoQueryCriteria {
    // 时间
//    @Query(type = Query.Type.BETWEEN)
//    @ApiModelProperty(value = "创建时间: 格式[yyyy-MM-dd HH:mm:ss]")
//    private List<String> createTime;
//
//    @Query(type = Query.Type.BETWEEN)
//    @ApiModelProperty(value = "更新时间: 格式[yyyy-MM-dd HH:mm:ss]")
//    private List<String> updateTime;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "姓名")
    private String personName;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "线路名称")
    private String routeName;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "性别")
    private String personSex;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "名族")
    private String nation;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "籍贯")
    private String nativeInfo;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "婚姻状况")
    private String marriageFlag;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "政治面貌")
    private String partyFlag;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "学历")
    private String educationBg;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "宗教信仰")
    private String faithType;

    @Query(type = Query.Type.EQUAL)
    @ApiModelProperty(value = "危害程度")
    private String hazardLevel;


    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "联系方式")
    private String mobile;
    ;


    // 审计字段
    @Query(type = Query.Type.INNER_LIKE)
    @JsonIgnore
    @ApiModelProperty(value = "所属单位")
    private List<String> unitCode;


//    @Query(type = Query.Type.EQUAL)
//    @ApiModelProperty(value = "创建人id")
//    private String creator;
//
//    @Query(type = Query.Type.EQUAL)
//    @ApiModelProperty(value = "修改人id")
//    private String modifier;
}