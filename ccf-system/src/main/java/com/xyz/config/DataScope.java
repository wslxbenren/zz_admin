package com.xyz.config;

import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.security.service.JwtUserDetailsService;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.SecurityUtils;
import com.xyz.modules.system.domain.Dept;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.RoleService;
import com.xyz.modules.system.service.UserService;
import com.xyz.modules.system.service.dto.RoleSmallDTO;
import com.xyz.modules.system.service.dto.UserDTO;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 权限控制
 */
@Component
public class DataScope {
    private final String[] scopeType = {"全部","本级", "本级及下级所有", "下级所有",  "自定义"};
    /**
     * 顶级领导
     */
    private static final String ALL = "全部";

    /**
     * 部门领导: 查看本级以及所有下级
     */
    private static final String LEADER = "查看本级以及下级部门";
    /**
     * 普通成员
     */
    private static final String ORDINARY_MEMBER = "本级";
    /**
     * 自定义权限
     */
    private static final String CUSTOM = "自定义";


    private DeptService deptService;

    private UserService userService;

    @Autowired
    public DataScope(UserService userService, DeptService deptService) {
        this.deptService = deptService;
        this.userService = userService;
    }

    /**
     * 角色与部门
     * @return
     */
    public Set<String> getDeptIds() {
        UserDTO user = userService.findByName(SecurityUtils.getUsername());
        Set<String> deptIds = new HashSet<>();
        // 查询用户角色
        Set<RoleSmallDTO> roleSet = user.getRoles();
        for (RoleSmallDTO role : roleSet) {
            if (scopeType[0].equals(role.getDataScope())) {
                return new HashSet<>() ;
            }
            // 存储本级的数据权限
            if (scopeType[1].equals(role.getDataScope())) {
                deptIds.add(user.getDept().getId());
            }
            // 存储自定义的数据权限
            if (scopeType[2].equals(role.getDataScope())) {
                Set<Dept> depts = deptService.findByRoleIds(role.getId().toString());
                for (Dept dept : depts) {
                    deptIds.add(dept.getId());
                    List<Dept> deptChildren = deptService.findByPid(dept.getId());
                    if (deptChildren != null && deptChildren.size() != 0) {
                        deptIds.addAll(getDeptChildren(deptChildren));
                    }
                }
            }
        }
        return deptIds;
    }

    /**
     * 根据当前用户所有角色进行部门的权限控制
     */
    public Set<String> getDeptCodesWithRole() {
        UserDTO user = userService.findByName(SecurityUtils.getUsername());
        Set<RoleSmallDTO> userRoles = user.getRoles();
        Set<String> deptCodes = new HashSet<>();
        for (RoleSmallDTO role : userRoles) {
            switch (role.getDataScope()) {
                case ALL:
                    return Collections.EMPTY_SET;
                case LEADER:
                    deptCodes.addAll(deptService.getDownGradeDeptCodes(user.getDept().getCode()));
                    break;
                case ORDINARY_MEMBER:
                    deptCodes.add(user.getDeptId());
                    break;
                case CUSTOM:
                    deptService.findByRoleIds(role.getId().toString()).forEach(dept -> {
                        deptCodes.addAll(deptService.getDownGradeDeptCodes(dept.getCode()));
                    });
                    break;
                default:
                    break;
            }
        }
        return deptCodes;
    }

    /**
     * 机构权限过滤, 如果前端参数包含了机构查询参数, 则只查询当前机构下的数据
     * todo 匿名类暂不支持异常抛出, 待解决 不能仅仅靠前端约束机构条件查询
     * @param q 查询封装对象
     * @return
     */
    public <Q> Specification genSpecification(Q q) {
        return (Specification) (root, criteriaQuery, criteriaBuilder) -> {
            UserDTO user = userService.findByName(SecurityUtils.getUsername());
            String userDeptCode = user.getDept().getCode();
            Set<String> inDeptCodes;
            try {
                Field unitCode = q.getClass().getDeclaredField("unitCode");
                unitCode.setAccessible(true);
                List<String> unitCodeParam = (List<String>) unitCode.get(q);
                if (unitCodeParam == null) {
//                    if (!"-1".equals(u.getDeptDto().getPid())) {
//                        inDeptCodes = deptService.getDownGradeDeptCodes(userDeptCode);
//                        unitCode.set(q, inDeptCodes);
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

    public List<String> getDeptChildren(List<Dept> deptList) {
        List<String> list = new ArrayList<>();
        deptList.forEach(dept -> {
//                    if (dept!=null && dept.getEnabled()){
                    if (dept!=null && dept.getEnabled()){
                        List<Dept> depts = deptService.findByPid(dept.getId());
                        if(deptList!=null && deptList.size()!=0){
                            list.addAll(getDeptChildren(depts));
                        }
                        list.add(dept.getId());
                    }
                }
        );
        return list;
    }
}
