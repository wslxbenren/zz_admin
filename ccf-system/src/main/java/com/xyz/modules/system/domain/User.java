package com.xyz.modules.system.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-11-22
 */
@Entity
@Getter
@Setter
@Table(name="user")
public class User implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private String id;

    @NotBlank
    @Column(name = "username")
    private String username;

    private String avatar;

    @NotBlank
    @Pattern(regexp = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}",message = "格式错误")
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private Boolean enabled;

    private String password;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "last_password_reset_time")
    private Date lastPasswordResetTime;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    //建立中间表user_roles
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;
    //////////////////////////////////////////////////////////////////////////////////////
    @Column(name = "IDENTIFIER")
    private String identifier;

    @Column(name = "SEX")
    private String sex;


    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "COMM_TYPE")
    private String comnType;

    @Column(name = "SMOBILE")
    private String smobile;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DEP_CODE")
    private String depCode;


    @Column(name = "GRADE")
    private String grade;


    @Column(name = "POLICE")
    private String police;

    @Column(name = "POSITION")
    private String position;


    @Column(name = "OFFICE_PHONE")
    private String officePhone;


    @Column(name = "NAME_BRIEF_SPELL")
    private String nameBriefSpell;


    @Column(name = "SEQ")
    private String seq;


    @Column(name = "NOTE")
    private String note;

    @Column(name = "N_LAST_UPDATE_TIME")
    private String nLastUpdateTime;

    @Column(name = "DELETE_FLAG")
    private String deleteFlag;

    @Column(name = "PERSONTYPE")
    private String persontype;

    @Column(name = "JXFLAG")
    private String jxflag;


    @Column(name = "DISPLAY_FLAG")
    private String displayFlag;

    @Column(name = "dep_id")
    private String depId;




    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", lastPasswordResetTime=" + lastPasswordResetTime +
                ", roles=" + roles +
                ", job=" + job +
                ", dept=" + dept +
                ", identifier='" + identifier + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", comnType='" + comnType + '\'' +
                ", smobile='" + smobile + '\'' +
                ", code='" + code + '\'' +
                ", depCode='" + depCode + '\'' +
                ", grade='" + grade + '\'' +
                ", police='" + police + '\'' +
                ", position='" + position + '\'' +
                ", officePhone='" + officePhone + '\'' +
                ", nameBriefSpell='" + nameBriefSpell + '\'' +
                ", seq='" + seq + '\'' +
                ", note='" + note + '\'' +
                ", nLastUpdateTime='" + nLastUpdateTime + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", persontype='" + persontype + '\'' +
                ", jxflag='" + jxflag + '\'' +
                ", displayFlag='" + displayFlag + '\'' +
                '}';
    }

    public @interface Update {}
}