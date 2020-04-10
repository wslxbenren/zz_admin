package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import com.xyz.annotation.Query;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class ReleasedPersonQueryCriteria{

    // 精确
    @Query
    private String personName;

    private String creator;

    @Query(type = Query.Type.IN)
    private List<String> unitCode;
}