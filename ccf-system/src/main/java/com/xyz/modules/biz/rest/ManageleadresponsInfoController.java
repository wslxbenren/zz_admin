package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.ManageleadresponsInfo;
import com.xyz.modules.biz.service.ManageleadresponsInfoService;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Api(tags = "ManageleadresponsInfo管理")
@RestController
@RequestMapping("api")
public class ManageleadresponsInfoController {

    @Autowired
    private ManageleadresponsInfoService ManageleadresponsInfoService;

    @Log("查询ManageleadresponsInfo")
    @ApiOperation(value = "查询ManageleadresponsInfo")
    @GetMapping(value = "/ManageleadresponsInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_SELECT')")
    public ResponseEntity getManageleadresponsInfos(ManageleadresponsInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(ManageleadresponsInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增ManageleadresponsInfo")
    @ApiOperation(value = "新增ManageleadresponsInfo")
    @PostMapping(value = "/ManageleadresponsInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody ManageleadresponsInfo resources){
        return new ResponseEntity(ManageleadresponsInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ManageleadresponsInfo")
    @ApiOperation(value = "修改ManageleadresponsInfo")
    @PutMapping(value = "/ManageleadresponsInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody ManageleadresponsInfo resources){
        ManageleadresponsInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ManageleadresponsInfo")
    @ApiOperation(value = "删除ManageleadresponsInfo")
    @DeleteMapping(value = "/ManageleadresponsInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        ManageleadresponsInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}