package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Foreigners;
import com.xyz.modules.biz.service.ForeignersService;
import com.xyz.modules.biz.service.dto.ForeignersQueryCriteria;
import com.xyz.modules.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.sql.Timestamp;
import java.util.Date;

/**
* @author xjh
* @date 2020-04-08
*/
@Api(tags = "Foreigners管理")
@RestController
@RequestMapping("api")
public class ForeignersController {

    @Autowired
    private ForeignersService ForeignersService;

    @Autowired
    private DictService dictService;

    @Log("查询Foreigners")
    @ApiOperation(value = "查询Foreigners")
    @GetMapping(value = "/Foreigners")
    @PreAuthorize("hasAnyRole('ADMIN','FOREIGNERS_ALL','FOREIGNERS_SELECT')")
    public ResponseEntity getForeignerss(ForeignersQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(ForeignersService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("详情Foreigners")
    @ApiOperation(value = "详情Foreigners")
    @GetMapping(value = "/Foreigners/details/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','FOREIGNERS_ALL','FOREIGNERS_SELECT')")
    public ResponseEntity getForeignerssDetails(@PathVariable String id){
        return new ResponseEntity(ForeignersService.findById(id),HttpStatus.OK);
    }

    @Log("新增Foreigners")
    @ApiOperation(value = "新增Foreigners")
    @PostMapping(value = "/Foreigners")
    @PreAuthorize("hasAnyRole('ADMIN','FOREIGNERS_ALL','FOREIGNERS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Foreigners resources){
        return new ResponseEntity(ForeignersService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Foreigners")
    @ApiOperation(value = "修改Foreigners")
    @PutMapping(value = "/Foreigners")
    @PreAuthorize("hasAnyRole('ADMIN','FOREIGNERS_ALL','FOREIGNERS_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Foreigners resources){
        ForeignersService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Foreigners")
    @ApiOperation(value = "删除Foreigners")
    @DeleteMapping(value = "/Foreigners/{foreId}")
    @PreAuthorize("hasAnyRole('ADMIN','FOREIGNERS_ALL','FOREIGNERS_DELETE')")
    public ResponseEntity delete(@PathVariable String foreId){
        ForeignersService.delete(foreId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("获取字典项")
    @ApiOperation(value = "获取字典项")
    @GetMapping(value = "/Foreigners/getDict")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_DELETE')")
    public ResponseEntity getDict() {
        return new ResponseEntity(dictService.buildDict("com.xyz.modules.biz.domain.Foreigners"), HttpStatus.OK);
    }
}