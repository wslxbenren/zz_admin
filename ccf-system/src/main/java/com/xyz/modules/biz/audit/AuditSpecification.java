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
                List<String> unitCodeParam = (List<String>) unitCode.get(q);
                if (unitCodeParam.isEmpty()) {
                    inDeptCodes = deptService.getDownGradeDeptCodes(userDeptCode);
                    unitCode.setAccessible(true);
                    unitCode.set(q, inDeptCodes);
                    // 一个方法调用了两遍
                    // fixme 这里需要判断要查询的机构是否在当前用户机构内 暂时由前端约束
//                    if(deptService.findById(unitCodeParam).getGrage == u.getDeptDto().getGrage  ) {
//                        if(uCode == codeParam) {
//                            // 同级同部门 可见
//                        } else {
//                            // 同级不同部门
//                        }
//                    } else if (deptService.findById(unitCodeParam).getGrage < u.getDeptDto().getGrage) {
//                        // 下级 可见
//                    } else {
//                        // 上级 不可见
//                    }
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
