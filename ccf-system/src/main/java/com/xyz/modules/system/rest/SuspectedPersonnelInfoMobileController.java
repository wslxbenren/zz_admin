package com.xyz.modules.system.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.system.domain.SuspectedPersonnelInfo;
import com.xyz.modules.system.domain.vo.SuspectedPersonnelInfoVo;
import com.xyz.modules.system.service.SuspectedPersonnelInfoService;
import com.xyz.modules.system.service.dto.SuspectedPersonnelInfoQueryCriteria;
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
* @date 2020-02-07
*/
@Api(tags = "SuspectedPersonnelInfo管理--Mobile端")
@RestController
@RequestMapping("api/m")
public class SuspectedPersonnelInfoMobileController {

    @Autowired
    private SuspectedPersonnelInfoService suspectedPersonnelInfoService;

    @Log("查询SuspectedPersonnelInfo")
    @ApiOperation(value = "查询SuspectedPersonnelInfo")
    @GetMapping(value = "/suspectedPersonnelInfo")
    public ResponseEntity getSuspectedPersonnelInfos(SuspectedPersonnelInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(suspectedPersonnelInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增SuspectedPersonnelInfo")
    @ApiOperation(value = "新增SuspectedPersonnelInfo")
    @PostMapping(value = "/suspectedPersonnelInfo")
    public ResponseEntity create(@Validated @RequestBody SuspectedPersonnelInfoVo resources) throws IllegalAccessException {
        return new ResponseEntity(suspectedPersonnelInfoService.create(resources.copy()),HttpStatus.CREATED);
    }

    @Log("修改SuspectedPersonnelInfo")
    @ApiOperation(value = "修改SuspectedPersonnelInfo")
    @PutMapping(value = "/suspectedPersonnelInfo")
    public ResponseEntity update(@Validated @RequestBody SuspectedPersonnelInfo resources){
        suspectedPersonnelInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除SuspectedPersonnelInfo")
    @ApiOperation(value = "删除SuspectedPersonnelInfo")
    @DeleteMapping(value = "/suspectedPersonnelInfo/{peId}")
    public ResponseEntity delete(@PathVariable String peId){
        suspectedPersonnelInfoService.delete(peId);
        return new ResponseEntity(HttpStatus.OK);
    }
}