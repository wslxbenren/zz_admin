package com.xyz.modules.biz.audit;

import cn.hutool.core.util.ReflectUtil;
import com.xyz.config.DataScope;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.UserService;
import com.xyz.modules.system.service.dto.UserDTO;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限以及其他扩展
 */
@Component
@Slf4j
public class AuditSpecification {
    private static final String TOP_GRADE =  "-1";
    private UserService userDetailsService;
    private DeptService deptService;
    private DataScope dataScope;

    @Autowired
    public AuditSpecification(UserService userDetailsService, DeptService deptService, DataScope dataScope) {
        this.userDetailsService = userDetailsService;
        this.deptService = deptService;
        this.dataScope = dataScope;
    }

    /**
     * 查询统一数据权限过滤
     * @param q 查询封装对象
     * @return
     */
    public <Q> Specification genSpecification(Q q) {
        return (Specification) (root, criteriaQuery, criteriaBuilder) -> {
            UserDTO user = userDetailsService.findByName(SecurityUtils.getUsername());
            List<String> unitCodeParam = (List<String>) ReflectUtil.getFieldValue(q, "unitCode");
            // fixme 顶级暂时不加权限
            boolean userIsTopGrade = TOP_GRADE.equals(user.getDept().getPid());
            if(unitCodeParam == null) {
                if (!userIsTopGrade) {
                    Set<String> t = dataScope.getDeptCodesWithRole();
                    ReflectUtil.setFieldValue(q, "unitCode", new ArrayList<>(t));
                }
            } else {
                // fixme 顶级暂时加权限
                boolean paramIsTopGrade = unitCodeParam.stream().anyMatch((i) -> TOP_GRADE.equals(deptService.getGradeByCode(i)));
                if (!paramIsTopGrade) {
                    // todo 这里是否存在重复工作 如果指定机构查询则与当前角色集所有的数据权限做交集处理.
                    Set<String> paramList = new HashSet<>();
                    unitCodeParam.forEach(deptCode -> {
                        paramList.addAll(deptService.getDownGradeDeptCodes(deptCode));
                    });
                    Set<String> roleDeptCodes = dataScope.getDeptCodesWithRole();
                    if(roleDeptCodes != null) {
                        if(roleDeptCodes.isEmpty()) {
                            // 用户角色没有相应权限 todo 关于异常描述需要仔细定义一番
                            throw new RuntimeException("用户相应角色没有数据权限, 请管理员添加");
                        } else {
                            // (角色数据权限)∩(本级及下级所有) = 合法数据
                            paramList.retainAll(roleDeptCodes);
                            if (paramList.isEmpty()) {
                                throw new RuntimeException("无法查看当前权限之外数据, 请管理员添加");
                            }
                            ReflectUtil.setFieldValue(q, "unitCode", paramList);
                        }
                    } else {
                        // 具有查询全部的权限(这里的全部意思就是本级及下级所有)
                        ReflectUtil.setFieldValue(q, "unitCode", paramList);
                    }

                }
            }
            return QueryHelp.getPredicate(root,q,criteriaBuilder);
        };
    }


}
