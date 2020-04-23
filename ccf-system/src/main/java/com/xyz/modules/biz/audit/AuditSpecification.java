package com.xyz.modules.biz.audit;

import cn.hutool.core.util.ReflectUtil;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.security.service.JwtUserDetailsService;
import com.xyz.modules.system.domain.Role;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.dto.RoleDTO;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * 权限以及其他扩展
 */
@Component
@Slf4j
public class AuditSpecification {
    private JwtUserDetailsService userDetailsService;
    private DeptService deptService;

    @Autowired
    public AuditSpecification(@Qualifier("jwtUserDetailsService") JwtUserDetailsService userDetailsService, DeptService deptService) {
        this.userDetailsService = userDetailsService;
        this.deptService = deptService;
    }

    /**
     * 机构权限过滤, 如果前端参数包含了机构查询参数, 则只查询当前机构下的数据
     * todo 匿名类暂不支持异常抛出, 待解决 不能仅仅靠前端约束机构条件查询
     * @param q 查询封装对象
     * @return
     */
    public <Q> Specification genSpecification(Q q) {
        return (Specification) (root, criteriaQuery, criteriaBuilder) -> {
            List<String> unitCodeParam = (List<String>) ReflectUtil.getFieldValue(q, "unitCode");
            // 先默认没有空字符串传过来
            if (unitCodeParam == null) {
                JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
                String userDeptCode = u.getDeptDto().getCode();
                if (!"-1".equals(u.getDeptDto().getPid())) {
                    ReflectUtil.setFieldValue(q, "unitCode", deptService.getDownGradeDeptCodes(userDeptCode));
                }
            } else {
                unitCodeParam.forEach(deptCode -> {
                    ReflectUtil.setFieldValue(q, "unitCode", deptService.getDownGradeDeptCodes(deptCode));
                });
            }

            return QueryHelp.getPredicate(root,q,criteriaBuilder);
        };
    }


}
