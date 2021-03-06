package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.actual.entity.Leftbehind;
import com.xyz.modules.biz.service.actual.LeftbehindService;
import com.xyz.modules.biz.service.actual.qo.LeftbehindQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
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
* @date 2020-04-08
 * 功能模块 ： 实有人口/留守人员信息
*/
@Api(tags = "Leftbehind管理")
@RestController
@RequestMapping("api")
public class LeftbehindController {

    @Autowired
    private LeftbehindService LeftbehindService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

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
        return new ResponseEntity(LeftbehindService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Leftbehind")
    @ApiOperation(value = "修改Leftbehind")
    @PutMapping(value = "/Leftbehind")
    @PreAuthorize("hasAnyRole('ADMIN','LEFTBEHIND_ALL','LEFTBEHIND_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Leftbehind resources){
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

    @Log("查询Leftbehind")
    @ApiOperation(value = "验证身份证号码存在")
    @GetMapping(value = "/Leftbehind/validateIdentityNum/{id}&{identityNum}")
    @PreAuthorize("hasAnyRole('ADMIN','NONPUBLIC_ALL','NONPUBLIC_SELECT')")
    public ResponseEntity verifyCreditCode(@PathVariable String id,@PathVariable String identityNum){
        return new ResponseEntity(LeftbehindService.validateIdentityNum(id,identityNum),HttpStatus.OK);
    }

    @Log("查询变更记录Leftbehind列表")
    @ApiOperation(value = "查询变更记录")
    @GetMapping(value = "/Leftbehind/ModifyRecords/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','FLOATPEOPLE_ALL','FLOATPEOPLE_SELECT')")
    public ResponseEntity findModifyRecordsById(@PathVariable String id){
        return new ResponseEntity(LeftbehindService.findModifyRecordsById(id),HttpStatus.OK);
    }
}