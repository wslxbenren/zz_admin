package com.xyz.modules.system.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.system.domain.SuspectedPersonnelInfo;
import com.xyz.modules.system.service.SuspectedPersonnelInfoService;
import com.xyz.modules.system.service.dto.SuspectedPersonnelInfoQueryCriteria;
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
* @author dadovicn
* @date 2020-02-07
*/
@Api(tags = "SuspectedPersonnelInfo管理--PC端")
@RestController
@RequestMapping("api")
public class SuspectedPersonnelInfoPcController {

    @Autowired
    private SuspectedPersonnelInfoService suspectedPersonnelInfoService;

    @Log("查询SuspectedPersonnelInfo")
    @ApiOperation(value = "查询SuspectedPersonnelInfo")
    @GetMapping(value = "/suspectedPersonnelInfo")
    @PreAuthorize("hasAnyRole('ADMIN','SUSPECTEDPERSONNELSCREENING_ALL','SUSPECTEDPERSONNELSCREENING_SELECT')")
    public ResponseEntity getSuspectedPersonnelInfos(SuspectedPersonnelInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(suspectedPersonnelInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增SuspectedPersonnelInfo")
    @ApiOperation(value = "新增SuspectedPersonnelInfo")
    @PostMapping(value = "/suspectedPersonnelInfo")
    @PreAuthorize("hasAnyRole('ADMIN','SUSPECTEDPERSONNELSCREENING_ALL','SUSPECTEDPERSONNELSCREENING_CREATE')")
    public ResponseEntity create(@Validated @RequestBody SuspectedPersonnelInfo resources){
        return new ResponseEntity(suspectedPersonnelInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改SuspectedPersonnelInfo")
    @ApiOperation(value = "修改SuspectedPersonnelInfo")
    @PutMapping(value = "/suspectedPersonnelInfo")
    @PreAuthorize("hasAnyRole('ADMIN','SUSPECTEDPERSONNELSCREENING_ALL','SUSPECTEDPERSONNELSCREENING_EDIT')")
    public ResponseEntity update(@Validated @RequestBody SuspectedPersonnelInfo resources){
        suspectedPersonnelInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除SuspectedPersonnelInfo")
    @ApiOperation(value = "删除SuspectedPersonnelInfo")
    @DeleteMapping(value = "/suspectedPersonnelInfo/{peId}")
    @PreAuthorize("hasAnyRole('ADMIN','SUSPECTEDPERSONNELSCREENING_ALL','SUSPECTEDPERSONNELSCREENING_DELETE')")
    public ResponseEntity delete(@PathVariable String peId){
        suspectedPersonnelInfoService.delete(peId);
        return new ResponseEntity(HttpStatus.OK);
    }
}