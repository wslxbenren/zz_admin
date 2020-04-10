package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.BuildheadInfo;
import com.xyz.modules.biz.service.BuildheadInfoService;
import com.xyz.modules.biz.service.dto.BuildheadInfoQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.DictService;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author lx
* @date 2020-04-06
 * 综治组织/楼长信息 功能模块的CRUD
*/
@Api(tags = "BuildheadInfo管理")
@RestController
@RequestMapping("api")
public class BuildheadInfoController {

    @Autowired
    private BuildheadInfoService BuildheadInfoService;

    @Autowired
    private DictService dictService;

    @Autowired
    private DeptService deptService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Log("查询BuildheadInfo")
    @ApiOperation(value = "查询BuildheadInfo")
    @GetMapping(value = "/BuildheadInfo")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_SELECT')")
    public ResponseEntity getBuildheadInfos(BuildheadInfoQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(BuildheadInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增BuildheadInfo")
    @ApiOperation(value = "新增BuildheadInfo")
    @PostMapping(value = "/BuildheadInfo")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody BuildheadInfo resources){
        return new ResponseEntity(BuildheadInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改BuildheadInfo")
    @ApiOperation(value = "修改BuildheadInfo")
    @PutMapping(value = "/BuildheadInfo")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody BuildheadInfo resources){
        if (StringUtils.isBlank(resources.getId())){
            throw new BadRequestException("主键ID不能为空");
        }
        BuildheadInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BuildheadInfo")
    @ApiOperation(value = "删除BuildheadInfo")
    @DeleteMapping(value = "/BuildheadInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        BuildheadInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("获取字典项")
    @ApiOperation(value = "获取字典项")
    @GetMapping(value = "/BuildheadInfo/getDict")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_DELETE')")
    public ResponseEntity getDict() {
        return new ResponseEntity(dictService.buildDict("com.xyz.modules.biz.domain.BuildheadInfo"), HttpStatus.OK);
    }
}