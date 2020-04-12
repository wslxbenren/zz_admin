package com.xyz.modules.biz.service.strategy;

import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.security.service.JwtUserDetailsService;
import com.xyz.modules.system.service.DeptService;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
     * 权限&其他扩展 todo 待考虑
     * @param q 查询封装对象
     * @return
     */
    public <Q> Specification genSpecification(Q q) {
        return (Specification) (root, criteriaQuery, criteriaBuilder) -> {
            JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
            String deptCode = u.getDeptDto().getCode();
            List<String> deptCodes = deptService.getDownGradeDeptCodes(deptCode);
            try {
                Field unitCode = q.getClass().getDeclaredField("unitCode");
                unitCode.setAccessible(true);
                unitCode.set(q, deptCodes);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return QueryHelp.getPredicate(root,q,criteriaBuilder);
        };
    }


}
