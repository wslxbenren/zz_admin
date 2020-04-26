package com.xyz.config;

import com.xyz.modules.system.domain.Dept;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.UserService;
import com.xyz.modules.system.service.dto.DeptSmallDTO;
import com.xyz.modules.system.service.dto.RoleSmallDTO;
import com.xyz.modules.system.service.dto.UserDTO;
import com.xyz.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限控制
 */
@Component
public class DataScope {
    private final String[] scopeType = {"全部","本级","本级及下级", "自定义"};
    /**
     * 超管
     */
    private static final String ALL = "全部";
    /**
     * 部门领导: 查看本级以及所有下级
     */
    private static final String LEADER = "本级及下级";
    /**
     * 普通成员
     */
    private static final String ORDINARY_MEMBER = "本级";
    /**
     * 自定义权限
     */
    private static final String CUSTOM = "自定义";

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;



    /**
     * 角色与部门
     * 方法已过期, 换 {@link #getDeptCodesWithRole()}
     * @return
     */
    @Deprecated
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

            // 本级及下级
            if (scopeType[2].equals(role.getDataScope())) {
                deptIds.addAll(getDeptChildren(user.getDept()));
            }

            // 存储自定义的数据权限
            if (scopeType[3].equals(role.getDataScope())) {
                Set<Dept> depts = deptService.findByRoleIds(role.getId());
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
                    return null;
                case LEADER:
                    deptCodes.addAll(deptService.getDownGradeDeptCodes(user.getDept().getCode()));
                    break;
                case ORDINARY_MEMBER:
                    deptCodes.add(user.getDept().getCode());
                    break;
                case CUSTOM:
                    deptService.findByRoleIds(role.getId()).forEach(dept -> {
                        deptCodes.addAll(deptService.getDownGradeDeptCodes(dept.getCode()));
                    });
                    break;
                default:
                    break;
            }
        }
        return deptCodes;
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

    public List<String> getDeptChildren(DeptSmallDTO dept) {
        List<String> list = new ArrayList<>();
        // fixme 这里没有 enable 校验
        if (dept != null ){
            List<Dept> depts = deptService.findByPid(dept.getId());
            list.addAll(getDeptChildren(depts));
            list.add(dept.getId());
        }
        return list;
    }
}
