package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.org.entity.BuildheadInfo;
import com.xyz.modules.biz.service.org.BuildheadInfoService;
import com.xyz.modules.biz.service.org.qo.BuildheadInfoQueryCriteria;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @Log("查询列表BuildheadInfo")
    @ApiOperation(value = "查询BuildheadInfo")
    @GetMapping(value = "/BuildheadInfo")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_SELECT')")
    public ResponseEntity getBuildheadInfos(BuildheadInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(BuildheadInfoService.queryAll(criteria, pageable), HttpStatus.OK);
    }
    @Log("查询单个BuildheadInfo")
    @ApiOperation(value = "查询单个BuildheadInfo")
    @GetMapping(value = "/BuildheadInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_SELECT')")
    public ResponseEntity getById(@PathVariable String id){
        return new ResponseEntity(BuildheadInfoService.findById(id),HttpStatus.OK);
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
        BuildheadInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BuildheadInfo")
    @ApiOperation(value = "删除BuildheadInfo")
    @DeleteMapping(value = "/BuildheadInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        BuildheadInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}