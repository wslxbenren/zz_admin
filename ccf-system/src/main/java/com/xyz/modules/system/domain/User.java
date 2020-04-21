package com.xyz.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.modules.system.util.annotation.Dict;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

//    @NotBlank
//    @Pattern(regexp = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}",message = "格式错误")
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private Boolean enabled;

    private String password;

    // 创建时间
    @Column(name = "create_time",nullable = false,updatable = false)
    @JsonIgnore
    @CreationTimestamp
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

    // 性别:编码应符合GB/T2261.1
    @Column(name = "SEX")
    @Dict(DictEnum.XING_BIE)
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
/////////////////////////////////////////////////////////////////////////////综治新增
    @Column(name = "national")
    @Dict(DictEnum.MIN_ZU)
    private String national;

    @Column(name = "political_status")
    @Dict(DictEnum.ZZMM)
    private String politicalStatus;

    @Column(name = "birth")
    private Date birth;

    @Column(name = "specialty")
    private String specialty;


    @Column(name = "education_bg")
    @Dict(DictEnum.XUE_LI)
    private String  educationBg;


    @Column(name = "card_code")
    @Dict(DictEnum.ZJDM)
    private String cardCode;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "creator")
    private String creator;

    @Column(name = "modifier")
    private String modifier;


    // 修改时间
    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "realname")
    private String realname;



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
                ", national='" + national + '\'' +
                ", politicalStatus='" + politicalStatus  + '\'' +
                ", birth='" + birth + '\'' +
                ", specialty='" + specialty + '\'' +
                ", educationBg='" + educationBg + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }

    public @interface Update {}
}