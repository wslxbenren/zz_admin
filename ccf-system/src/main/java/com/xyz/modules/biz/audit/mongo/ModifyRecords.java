package com.xyz.modules.biz.audit.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * 数据更新记录实体类
 *
 * @author xiaonanfeng
 * @date 2020-04-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ModifyRecords implements java.io.Serializable {
    private String id;
    private String entityName;
    private String entityId;
    private String modifyUserid;
    private LocalDateTime modifyTime;
    private String creator;
    private LocalDateTime createTime;
    private String operName;
    private LocalDateTime operTime;
    private String modifyContent;
    private String deptName;
    private String before;
    private String after;
}
