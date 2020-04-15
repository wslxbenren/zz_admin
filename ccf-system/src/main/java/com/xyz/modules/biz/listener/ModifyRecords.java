package com.xyz.modules.biz.listener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


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
//    @Id
    private String id;
    private String entityName;
    private String entityId;
    private String modifyUserid;
    private Date modifyTime;
    private String modifyContent;
    private String before;
    private String after;
}