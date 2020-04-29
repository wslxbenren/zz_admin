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

    @Query(type = Query.Type.IN, propName = "code")
    private Set<String> codes;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query
    private String pid;
    private String code;
    private String note;
    private String contact;
    private String creditCode;
    private String creator;
    private String modifier;
    private String instiType;
    private String organType;
    private String guideUnit;
    private String functions;
}