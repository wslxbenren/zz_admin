package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.ManageleadresponsInfo;
import com.xyz.modules.biz.service.ManageleadresponsInfoService;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.DictService;
import com.xyz.utils.SecurityUtils;
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
* @author xjh
* @date 2020-04-05
*功能模块 ： 综治组织/领导责任制
*/
@Api(tags = "ManageleadresponsInfo管理")
@RestController
@RequestMapping("api")
public class ManageleadresponsInfoController {

    @Autowired
    private ManageleadresponsInfoService ManageleadresponsInfoService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询ManageleadresponsInfo")
    @GetMapping(value = "/ManageleadresponsInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_SELECT')")
    public ResponseEntity getManageleadresponsInfos(ManageleadresponsInfoQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(ManageleadresponsInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("详情ManageleadresponsInfo")
    @GetMapping(value = "/ManageleadresponsInfo/details/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_SELECT')")
    public ResponseEntity getManageleadresponsInfoDetails(@PathVariable String id){
        return new ResponseEntity( ManageleadresponsInfoService.findById(id),HttpStatus.OK);

    }

    @Log("新增ManageleadresponsInfo")
    @PostMapping(value = "/ManageleadresponsInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody ManageleadresponsInfo resources){
        return new ResponseEntity(ManageleadresponsInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ManageleadresponsInfo")
    @PutMapping(value = "/ManageleadresponsInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody ManageleadresponsInfo resources){
        ManageleadresponsInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ManageleadresponsInfo")
    @DeleteMapping(value = "/ManageleadresponsInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        ManageleadresponsInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*@Log("获取字典项")
    @ApiOperation(value = "获取字典项")
    @GetMapping(value = "/ManageleadresponsInfo/getDict")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_DELETE')")
    public ResponseEntity getDict() {
        return new ResponseEntity(dictService.buildDict("com.xyz.modules.biz.domain.ManageleadresponsInfo"), HttpStatus.OK);
    }*/
}