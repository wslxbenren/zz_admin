package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.Floatpeople;
import com.xyz.modules.biz.service.FloatpeopleService;
import com.xyz.modules.biz.service.dto.FloatpeopleQueryCriteria;
import com.xyz.modules.system.service.DeptService;
import com.xyz.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author lx
* @date 2020-04-09
 *实有人口/流动人口信息
*/
@Api(tags = "Floatpeople管理")
@RestController
@RequestMapping("api")
public class FloatpeopleController {

    @Autowired
    private FloatpeopleService FloatpeopleService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;


    @Log("查询Floatpeople")
    @ApiOperation(value = "查询Floatpeople")
    @GetMapping(value = "/Floatpeople")
    @PreAuthorize("hasAnyRole('ADMIN','FLOATPEOPLE_ALL','FLOATPEOPLE_SELECT')")
    public ResponseEntity getFloatpeoples(FloatpeopleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(FloatpeopleService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("查询单个Floatpeople")
    @ApiOperation(value = "查询单个Floatpeople")
    @GetMapping(value = "/Floatpeople/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','FLOATPEOPLE_ALL','FLOATPEOPLE_SELECT')")
    public ResponseEntity getById(@PathVariable String id){
        return new ResponseEntity(FloatpeopleService.findById(id),HttpStatus.OK);
    }

    @Log("新增Floatpeople")
    @ApiOperation(value = "新增Floatpeople")
    @PostMapping(value = "/Floatpeople")
    @PreAuthorize("hasAnyRole('ADMIN','FLOATPEOPLE_ALL','FLOATPEOPLE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Floatpeople resources){
        return new ResponseEntity(FloatpeopleService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Floatpeople")
    @ApiOperation(value = "修改Floatpeople")
    @PutMapping(value = "/Floatpeople")
    @PreAuthorize("hasAnyRole('ADMIN','FLOATPEOPLE_ALL','FLOATPEOPLE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Floatpeople resources){
        FloatpeopleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Floatpeople")
    @ApiOperation(value = "删除Floatpeople")
    @DeleteMapping(value = "/Floatpeople/{floatId}")
    @PreAuthorize("hasAnyRole('ADMIN','FLOATPEOPLE_ALL','FLOATPEOPLE_DELETE')")
    public ResponseEntity delete(@PathVariable String floatId){
        FloatpeopleService.delete(floatId);
        return new ResponseEntity(HttpStatus.OK);
    }
}