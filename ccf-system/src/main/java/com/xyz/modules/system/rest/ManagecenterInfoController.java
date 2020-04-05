package com.xyz.modules.system.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.system.domain.ManagecenterInfo;
import com.xyz.modules.system.service.ManagecenterInfoService;
import com.xyz.modules.system.service.dto.ManagecenterInfoQueryCriteria;
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
@Api(tags = "ManagecenterInfo管理")
@RestController
@RequestMapping("api")
public class ManagecenterInfoController {

    @Autowired
    private ManagecenterInfoService ManagecenterInfoService;

    @Log("查询ManagecenterInfo")
    @ApiOperation(value = "查询ManagecenterInfo")
    @GetMapping(value = "/ManagecenterInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGECENTERINFO_ALL','MANAGECENTERINFO_SELECT')")
    public ResponseEntity getManagecenterInfos(ManagecenterInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(ManagecenterInfoService.queryAll(criteria,pageable),HttpStatus.OK);
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
        ManagecenterInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ManagecenterInfo")
    @ApiOperation(value = "删除ManagecenterInfo")
    @DeleteMapping(value = "/ManagecenterInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGECENTERINFO_ALL','MANAGECENTERINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        ManagecenterInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}