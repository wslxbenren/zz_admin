package com.xyz.modules.biz.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import com.xyz.annotation.Query;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Data
public class RectificmanageQueryCriteria{

    // 精确
    @Query
    private String routeName;
}