package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Leftbehind;
import com.xyz.modules.biz.service.LeftbehindService;
import com.xyz.modules.biz.service.dto.LeftbehindQueryCriteria;
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
* @author dadovicn
* @date 2020-04-08
*/
@Api(tags = "Leftbehind管理")
@RestController
@RequestMapping("api")
public class LeftbehindController {

    @Autowired
    private LeftbehindService LeftbehindService;

    @Autowired
    private DictService dictService;

    @Log("查询Leftbehind")
    @ApiOperation(value = "查询Leftbehind")
    @GetMapping(value = "/Leftbehind")
    @PreAuthorize("hasAnyRole('ADMIN','LEFTBEHIND_ALL','LEFTBEHIND_SELECT')")
    public ResponseEntity getLeftbehinds(LeftbehindQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(LeftbehindService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("详情Leftbehind")
    @ApiOperation(value = "详情Leftbehind")
    @GetMapping(value = "/Leftbehind/details/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','LEFTBEHIND_ALL','LEFTBEHIND_SELECT')")
    public ResponseEntity getLeftbehindsDetails(@PathVariable String id){
        return new ResponseEntity(LeftbehindService.findById(id),HttpStatus.OK);
    }

    @Log("新增Leftbehind")
    @ApiOperation(value = "新增Leftbehind")
    @PostMapping(value = "/Leftbehind")
    @PreAuthorize("hasAnyRole('ADMIN','LEFTBEHIND_ALL','LEFTBEHIND_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Leftbehind resources){
        Timestamp createTime = new Timestamp(new Date().getTime());
        resources.setCreateTime(createTime);
        return new ResponseEntity(LeftbehindService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Leftbehind")
    @ApiOperation(value = "修改Leftbehind")
    @PutMapping(value = "/Leftbehind")
    @PreAuthorize("hasAnyRole('ADMIN','LEFTBEHIND_ALL','LEFTBEHIND_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Leftbehind resources){
        Timestamp operDate = new Timestamp(new Date().getTime());
        resources.setOperDate(operDate);
        LeftbehindService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Leftbehind")
    @ApiOperation(value = "删除Leftbehind")
    @DeleteMapping(value = "/Leftbehind/{leftId}")
    @PreAuthorize("hasAnyRole('ADMIN','LEFTBEHIND_ALL','LEFTBEHIND_DELETE')")
    public ResponseEntity delete(@PathVariable String leftId){
        LeftbehindService.delete(leftId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("获取字典项")
    @ApiOperation(value = "获取字典项")
    @GetMapping(value = "/Leftbehind/getDict")
    @PreAuthorize("hasAnyRole('ADMIN','BUILDHEADINFO_ALL','BUILDHEADINFO_DELETE')")
    public ResponseEntity getDict() {
        return new ResponseEntity(dictService.buildDict("com.xyz.modules.biz.domain.Leftbehind"), HttpStatus.OK);
    }
}