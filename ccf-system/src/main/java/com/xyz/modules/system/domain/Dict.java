package com.xyz.modules.system.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Entity
@Table(name="dict")
@Getter
@Setter
public class Dict implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    /**
     * 字典名称
     */
    @Column(name = "name",nullable = false,unique = true)
    @NotBlank
    private String name;

    /**
     * 描述
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 当前字典层级数
     */
    @Column(name = "grage")
    private String grage;

    @Where(clause = "grage is null or grage = 1")
    @OneToMany(mappedBy = "dict",cascade={CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<DictDetail> dictDetails;

    public @interface Update {}
}