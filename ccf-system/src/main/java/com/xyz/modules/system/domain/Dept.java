package com.xyz.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
* @author Zheng Jie
* @date 2019-03-25
*/

@NamedStoredProcedureQuery(name = "getChildList", procedureName = "getChildList",
        parameters = { @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_code", type = String.class) })
@Entity
@Data
@Table(name="dept")
public class Dept implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private String id;

    /**
     * 名称
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    @NotNull
    private Boolean enabled;

    /**
     * 上级部门
     */
    @Column(name = "pid",nullable = false)
    @NotNull
    private String pid;

    @JsonIgnore
    @ManyToMany(mappedBy = "depts")
    private Set<Role> roles;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;
/////////////////////////////////////////////////////////////////////////////////////
    @Column(name = "code")
    private String code;


    @Column(name = "contact")
    private String contact;


    @Column(name = "phone")
    private String phone;

    @Column(name = "GRAGE")
    private String grage;

    @Column(name = "sqe")
    private String sqe;

    @Column(name = "DEP_ABB")
    private String depAbb;

    @Column(name = "NOTE")
    private String note;


    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "N_LAST_UPDATE_TIME")
    private String nLastUpdateTime;


    @Column(name = "DELETE_FLAG")
    private String delteFLAG;

    @Column(name = "PARENT_ID")
    private String parentId;


    @Column(name = "PARENT_CODE")
    private String parentCode;
    // fixme 这里会报外键约束异常， 暂不影响其他功能
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentId")
    private Set<Dept> depts = new HashSet<>(16);

    @Override
    public String toString() {
        return "Dept{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", pid='" + pid + '\'' +
                ", roles=" + roles +
                ", createTime=" + createTime +
                ", code='" + code + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", grage='" + grage + '\'' +
                ", sqe='" + sqe + '\'' +
                ", depAbb='" + depAbb + '\'' +
                ", note='" + note + '\'' +
                ", amount='" + amount + '\'' +
                ", nLastUpdateTime='" + nLastUpdateTime + '\'' +
                ", delteFLAG='" + delteFLAG + '\'' +
                ", parentId='" + parentId + '\'' +
                ", parentCode='" + parentCode + '\'' +
                '}';
    }

    public @interface Update {}
}