package com.xyz.modules.system.service.dto;

import com.xyz.annotation.Query;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
* @author Zheng Jie
* @date 2019-6-4 14:49:34
*/
@Data
@NoArgsConstructor
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query(propName = "id", joinName = "dept")
    private String deptId;

    @Query(propName = "id", joinName = "dept", type = Query.Type.IN)
    private Set<String> deptIds;
}