package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Overseeinfo;
import com.xyz.modules.biz.service.OverseeinfoService;
import com.xyz.modules.biz.service.dto.OverseeinfoQueryCriteria;
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
@Api(tags = "Overseeinfo管理")
@RestController
@RequestMapping("api")
public class OverseeinfoController {

    @Autowired
    private OverseeinfoService OverseeinfoService;

    @Log("查询Overseeinfo")
    @ApiOperation(value = "查询Overseeinfo")
    @GetMapping(value = "/Overseeinfo")
    @PreAuthorize("hasAnyRole('ADMIN','OVERSEEINFO_ALL','OVERSEEINFO_SELECT')")
    public ResponseEntity getOverseeinfos(OverseeinfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(OverseeinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Overseeinfo")
    @ApiOperation(value = "新增Overseeinfo")
    @PostMapping(value = "/Overseeinfo")
    @PreAuthorize("hasAnyRole('ADMIN','OVERSEEINFO_ALL','OVERSEEINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Overseeinfo resources){
        return new ResponseEntity(OverseeinfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Overseeinfo")
    @ApiOperation(value = "修改Overseeinfo")
    @PutMapping(value = "/Overseeinfo")
    @PreAuthorize("hasAnyRole('ADMIN','OVERSEEINFO_ALL','OVERSEEINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Overseeinfo resources){
        OverseeinfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Overseeinfo")
    @ApiOperation(value = "删除Overseeinfo")
    @DeleteMapping(value = "/Overseeinfo/{overseeId}")
    @PreAuthorize("hasAnyRole('ADMIN','OVERSEEINFO_ALL','OVERSEEINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String overseeId){
        OverseeinfoService.delete(overseeId);
        return new ResponseEntity(HttpStatus.OK);
    }
}