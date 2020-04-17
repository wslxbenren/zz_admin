package com.xyz.modules.system.service.dto;

import com.xyz.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Data
public class UserQueryCriteria implements Serializable {

    @Query
    private String id;

    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<String> deptIds;

    // 多字段模糊
    @Query(blurry = "email,username")
    private String blurry;

    @Query
    private Boolean enabled;

    private String deptId;
    @Query(type = Query.Type.EQUAL)
    private String national;
    @Query(type = Query.Type.EQUAL)
    private String politicalStatus;
    @Query
    private Date birth;
    @Query
    private String specialty;
    @Query(type = Query.Type.EQUAL)
    private String  educationBg;
    @Query(type = Query.Type.EQUAL)
    private String cardCode;
    @Query
    private String cardNumber;
    @Query
    private String creator;
    @Query
    private String modifier;
    @Query
    private Date updateTime;
    @Query
    private String realname;

}
