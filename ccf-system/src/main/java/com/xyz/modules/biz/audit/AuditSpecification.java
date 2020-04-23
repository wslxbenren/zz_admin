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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
     * 机构权限过滤, 如果前端参数包含了机构查询参数, 则只查询当前机构下的数据
     * todo 匿名类暂不支持异常抛出, 待解决 不能仅仅靠前端约束机构条件查询
     * @param q 查询封装对象
     * @return
     */
    public <Q> Specification genSpecification(Q q) {
        return (Specification) (root, criteriaQuery, criteriaBuilder) -> {
            UserDTO user = userDetailsService.findByName(SecurityUtils.getUsername());
            List<String> unitCodeParam = (List<String>) ReflectUtil.getFieldValue(q, "unitCode");
            // 先默认没有空字符串传过来
            if (unitCodeParam == null) {
                if (!"-1".equals(user.getDept().getPid())) {
                    ReflectUtil.setFieldValue(q, "unitCode", dataScope.getDeptCodesWithRole());
                }
            } else {
                Set<String> tmp = Collections.EMPTY_SET;
                unitCodeParam.forEach(deptCode -> {
                    tmp.addAll(deptService.getDownGradeDeptCodes(deptCode));
                });
                ReflectUtil.setFieldValue(q, "unitCode", dataScope.getDeptCodesWithRole().retainAll(tmp));
            }

            return QueryHelp.getPredicate(root,q,criteriaBuilder);
        };
    }


}
