package com.xyz.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Data
public class UserDTO implements Serializable {

    @ApiModelProperty(hidden = true)
    private String id;

    private String username;

    private String avatar;

    private String email;

    private String phone;

    private Boolean enabled;

    @JsonIgnore
    private String password;

    private Timestamp createTime;

    private Date lastPasswordResetTime;

    @ApiModelProperty(hidden = true)
    private Set<RoleSmallDTO> roles;

    @ApiModelProperty(hidden = true)
    private JobSmallDTO job;

    private DeptSmallDTO dept;

    private String deptId;

    // 性别:编码应符合GB/T2261.1
    private String sex;

    // 性别:翻译后值
    private String sexStr;
    /////////////////////////////////////////////////////////////////////////////综治新增

    // 民族:编码应符合GB/T3304
    private String national;
    // 民族:翻译后值
    private String nationalStr;

    // 政治面貌:编码应符合GB/T4762
    private String politicalStatus;
    // 政治面貌:翻译后值
    private String politicalStatusStr;

    private Date birth;

    private String specialty;

    // 学历:编码应符合GB/T4658
    private String educationBg;
    // 学历:翻译后值
    private String educationBgStr;

    //證件代碼
    private String cardCode;
    private String cardCodeStr;

    private String cardNumber;

    private String creator;

    private String modifier;

    private Date updateTime;

    private String realname;
}
