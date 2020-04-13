package com.xyz.modules.biz.ao;

import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.security.service.JwtUserDetailsService;
import com.xyz.modules.system.service.DeptService;
import com.xyz.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * 权限拦截&历史记录
 * why 切面？
 *  监听器需要每个类上面都加一个注解， 不友好。
 */
@Aspect
@Component
@Slf4j
public class AuditAspect {
    private JwtUserDetailsService userDetailsService;
    private DeptService deptService;

    @Autowired
    public AuditAspect(@Qualifier("jwtUserDetailsService") JwtUserDetailsService userDetailsService, DeptService deptService) {
        this.userDetailsService = userDetailsService;
        this.deptService = deptService;
    }

    /**
     * 只有新增可以更改创建者
     * @param point
     */
    @Before("execution(* com.xyz.modules.biz.service..*.create(..)) || execution(* com.xyz.modules.biz.service..*.update(..))")
    public void fillAudit(JoinPoint point) {
        String sourceMethodName = point.getSignature().getDeclaringTypeName();
        Object o = point.getArgs()[0];
        Class clazz = o.getClass();
        log.info("{}, enter fillAudit", clazz.getSimpleName());
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        if(u == null) {
            log.error("的横切面用户登录状态失效");
        }
        try {
            if (sourceMethodName.equals("create")) {
                clazz.getMethod("setCreator", String.class).invoke(o, u.getId());
            }
            clazz.getMethod("setUnitCode", String.class).invoke(o, u.getDeptDto().getCode());
            clazz.getMethod("setModifier", String.class).invoke(o, u.getId());
            log.info("{}, fillAudit ok", clazz.getSimpleName());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
