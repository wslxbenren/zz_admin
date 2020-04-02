package com.xyz.modules.system.service.dto;

import com.xyz.annotation.Query;
import lombok.Data;

import java.util.Set;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
@Data
public class DeptQueryCriteria{

    @Query(type = Query.Type.IN, propName="id")
    private Set<String> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query
    private String pid;
}