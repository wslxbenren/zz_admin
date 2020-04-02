package com.xyz.modules.system.service.dto;

import com.xyz.annotation.Query;
import lombok.Data;

import java.io.Serializable;
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
}
