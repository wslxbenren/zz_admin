package com.xyz.modules.biz.audit;

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
     * 机构权限过滤, 如果前端参数包含了机构查询参数, 则只查询当前机构下的数据
     * todo 匿名类暂不支持异常抛出, 待解决 不能仅仅靠前端约束机构条件查询
     * @param q 查询封装对象
     * @return
     */
    public <Q> Specification genSpecification(Q q) {
        return (Specification) (root, criteriaQuery, criteriaBuilder) -> {
            JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
            String userDeptCode = u.getDeptDto().getCode();
            List<String> inDeptCodes;
            try {
                Field unitCode = q.getClass().getDeclaredField("unitCode");
                String unitCodeParam = (String) unitCode.get(q);
                if (unitCodeParam != null & unitCodeParam != "") {
                    // fixme 这里需要判断要查询的机构是否在当前用户机构内 暂时由前端约束
                    inDeptCodes = deptService.getDownGradeDeptCodes(unitCodeParam);
                } else {
                    inDeptCodes = deptService.getDownGradeDeptCodes(userDeptCode);
                }
                if(!inDeptCodes.isEmpty()) {
                    unitCode.setAccessible(true);
                    unitCode.set(q, inDeptCodes);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return QueryHelp.getPredicate(root,q,criteriaBuilder);
        };
    }


}
