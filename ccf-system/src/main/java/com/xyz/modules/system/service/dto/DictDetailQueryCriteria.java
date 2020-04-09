package com.xyz.modules.system.service.dto;

import com.xyz.annotation.Query;
import lombok.Data;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Data
public class DictDetailQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    @Query(propName = "name",joinName = "dict")
    private String dictName;

    @Query(type = Query.Type.EQUAL)
    private String value;

    @Query(type = Query.Type.EQUAL)
    private String pId;
}