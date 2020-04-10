package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import com.xyz.annotation.Query;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Data
public class ProcessmonitorQueryCriteria{

    // 精确
    @Query
    private String disposalName;
}