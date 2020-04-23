package com.xyz.modules.biz.audit;

import cn.hutool.core.util.ReflectUtil;
import com.xyz.config.DataScope;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.security.service.JwtUserDetailsService;
import com.xyz.modules.system.domain.Role;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.UserService;
import com.xyz.modules.system.service.dto.RoleDTO;
import com.xyz.modules.system.service.dto.UserDTO;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 权限以及其他扩展
 */
@Component
@Slf4j
public class AuditSpecification {
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
            if (unitCodeParam == null) {
                if (!"-1".equals(user.getDept().getPid())) {
                    Set<String> t = dataScope.getDeptCodesWithRole();
                    ReflectUtil.setFieldValue(q, "unitCode", t);
                }
            } else {
                // 如果指定机构查询则与当前角色集所有的数据权限做交集处理.
                Set<String> tmp = new HashSet<>();
                unitCodeParam.forEach(deptCode -> {
                    tmp.addAll(deptService.getDownGradeDeptCodes(deptCode));
                });
                Set<String> roleDeptCodes = dataScope.getDeptCodesWithRole();
                if(roleDeptCodes.isEmpty()) {
                    ReflectUtil.setFieldValue(q, "unitCode", tmp);
                } else {
                    tmp.retainAll(roleDeptCodes);
                }
                ReflectUtil.setFieldValue(q, "unitCode", tmp);
            }
            return QueryHelp.getPredicate(root,q,criteriaBuilder);
        };
    }


}
