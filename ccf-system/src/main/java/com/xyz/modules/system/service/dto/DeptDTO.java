package com.xyz.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.List;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
@Data
public class DeptDTO implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    @NotNull
    private Boolean enabled;

    /**
     * 上级部门
     */
    private String pid;

    private String code;
//    private  String lable;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptDTO> children;

    private Timestamp createTime;

    public String getLabel() {
        return name;
    }

    public String getValue() {
        return code;
    }


    private String note;
    private String contact;
    private String creditCode;
    private String creator;
    private String modifier;

    //字典翻譯
    private String instiType;
    private String instiTypeStr;
    //字典翻譯
    private String organType;
    private String organTypeStr;


    private String guideUnit;
    private String function;

}