package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.dispute.entity.Processmonitor;
import com.xyz.modules.biz.service.dispute.ProcessmonitorService;
import com.xyz.modules.biz.service.dispute.qo.ProcessmonitorQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Api(tags = "Processmonitor管理")
@RestController
@RequestMapping("api")
public class ProcessmonitorController {

    @Autowired
    private ProcessmonitorService ProcessmonitorService;

    @Log("查询Processmonitor")
    @ApiOperation(value = "查询Processmonitor")
    @GetMapping(value = "/Processmonitor")
    @PreAuthorize("hasAnyRole('ADMIN','PROCESSMONITOR_ALL','PROCESSMONITOR_SELECT')")
    public ResponseEntity getProcessmonitors(ProcessmonitorQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(ProcessmonitorService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Processmonitor")
    @ApiOperation(value = "新增Processmonitor")
    @PostMapping(value = "/Processmonitor")
    @PreAuthorize("hasAnyRole('ADMIN','PROCESSMONITOR_ALL','PROCESSMONITOR_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Processmonitor resources){
        return new ResponseEntity(ProcessmonitorService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Processmonitor")
    @ApiOperation(value = "修改Processmonitor")
    @PutMapping(value = "/Processmonitor")
    @PreAuthorize("hasAnyRole('ADMIN','PROCESSMONITOR_ALL','PROCESSMONITOR_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Processmonitor resources){
        ProcessmonitorService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Processmonitor")
    @ApiOperation(value = "删除Processmonitor")
    @DeleteMapping(value = "/Processmonitor/{processId}")
    @PreAuthorize("hasAnyRole('ADMIN','PROCESSMONITOR_ALL','PROCESSMONITOR_DELETE')")
    public ResponseEntity delete(@PathVariable String processId){
        ProcessmonitorService.delete(processId);
        return new ResponseEntity(HttpStatus.OK);
    }
}