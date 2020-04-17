package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.actual.entity.Foreigners;
import com.xyz.modules.biz.service.actual.ForeignersService;
import com.xyz.modules.biz.service.actual.qo.ForeignersQueryCriteria;
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
  * 功能模块 ： 实有人口/境外人员信息
*/
@Api(tags = "Foreigners管理")
@RestController
@RequestMapping("api")
public class ForeignersController {

    @Autowired
    private ForeignersService ForeignersService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

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
}