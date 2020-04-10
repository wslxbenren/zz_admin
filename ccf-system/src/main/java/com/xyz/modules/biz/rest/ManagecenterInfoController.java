package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.ManagecenterInfo;
import com.xyz.modules.biz.service.ManagecenterInfoService;
import com.xyz.modules.biz.service.dto.ManagecenterInfoQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.DictService;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

/**
* @author lx
* @date 2020-04-07
 * 综治组织/综治中心信息 功能模块的CRUD。
*/
@Api(tags = "ManagecenterInfo管理")
@RestController
@RequestMapping("api")
public class ManagecenterInfoController {

    @Autowired
    private ManagecenterInfoService ManagecenterInfoService;

    @Autowired
    private DictService dictService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询ManagecenterInfo")
    @ApiOperation(value = "查询ManagecenterInfo")
    @GetMapping(value = "/ManagecenterInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGECENTERINFO_ALL','MANAGECENTERINFO_SELECT')")
    public ResponseEntity getManagecenterInfos(ManagecenterInfoQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(ManagecenterInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }
    @Log("查询单个ManagecenterInfo")
    @ApiOperation(value = "查询单个ManagecenterInfo")
    @GetMapping(value = "/ManagecenterInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGECENTERINFO_ALL','MANAGECENTERINFO_SELECT')")
    public ResponseEntity getById(@PathVariable String id){
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        return new ResponseEntity(ManagecenterInfoService.findById(id),HttpStatus.OK);
    }

    @Log("新增ManagecenterInfo")
    @ApiOperation(value = "新增ManagecenterInfo")
    @PostMapping(value = "/ManagecenterInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGECENTERINFO_ALL','MANAGECENTERINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody ManagecenterInfo resources){
        return new ResponseEntity(ManagecenterInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ManagecenterInfo")
    @ApiOperation(value = "修改ManagecenterInfo")
    @PutMapping(value = "/ManagecenterInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGECENTERINFO_ALL','MANAGECENTERINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody ManagecenterInfo resources){
        if (StringUtils.isBlank(resources.getId())){
            throw new BadRequestException("主键ID不能为空");
        }
        ManagecenterInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ManagecenterInfo")
    @ApiOperation(value = "删除ManagecenterInfo")
    @DeleteMapping(value = "/ManagecenterInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGECENTERINFO_ALL','MANAGECENTERINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        ManagecenterInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("获取字典项")
    @ApiOperation(value = "获取字典项")
    @GetMapping(value = "/ManagecenterInfo/getDict")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_DELETE')")
    public ResponseEntity getDict() {
        return new ResponseEntity(dictService.buildDict("com.xyz.modules.biz.domain.ManagecenterInfo"), HttpStatus.OK);
    }
}