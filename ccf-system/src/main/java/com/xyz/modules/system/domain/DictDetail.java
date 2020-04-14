package com.xyz.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@NamedStoredProcedureQuery(name = "proAddrParentList", procedureName = "proAddrParentList",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_id", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_value", type = String.class)
        })
@Entity
@Data
@Table(name="dict_detail")
public class DictDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    /**
     * 字典标签
     */
    @Column(name = "label",nullable = false)
    private String label;

    /**
     * 字典值
     */
    @Column(name = "value",nullable = false)
    private String value;

    /**
     * 排序
     */
    @Column(name = "sort")
    private String sort = "999";

    /**
     * 字典id
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "dict_id")
    @JsonIgnore
    private Dict dict;

    @Column(name = "pid")
    private String pId;


    public @interface Update {}
}