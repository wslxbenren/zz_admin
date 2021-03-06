package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.org.entity.MajorcaseInfo;
import com.xyz.modules.biz.service.org.MajorcaseInfoService;
import com.xyz.modules.biz.service.org.qo.MajorcaseInfoQueryCriteria;
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
* @date 2020-04-05
 *功能模块 ： 综治组织/重大案件事件
*/
@Api(tags = "MajorcaseInfo管理")
@RestController
@RequestMapping("api")
public class MajorcaseInfoController {

    @Autowired
    private MajorcaseInfoService MajorcaseInfoService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询MajorcaseInfo")
    @ApiOperation(value = "查询MajorcaseInfo")
    @GetMapping(value = "/MajorcaseInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MAJORCASEINFO_ALL','MAJORCASEINFO_SELECT')")
    public ResponseEntity getMajorcaseInfos(MajorcaseInfoQueryCriteria criteria, Pageable pageable){
        // todo 这种条件过滤暂时不要
        return new ResponseEntity(MajorcaseInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("详情MajorcaseInfo")
    @ApiOperation(value = "详情MajorcaseInfo")
    @GetMapping(value = "/MajorcaseInfo/details/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MAJORCASEINFO_ALL','MAJORCASEINFO_SELECT')")
    public ResponseEntity getMajorcaseInfosDetails(@PathVariable String id){
        return new ResponseEntity(MajorcaseInfoService.findById(id),HttpStatus.OK);
    }


    @Log("新增MajorcaseInfo")
    @ApiOperation(value = "新增MajorcaseInfo")
    @PostMapping(value = "/MajorcaseInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MAJORCASEINFO_ALL','MAJORCASEINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody MajorcaseInfo resources){
        return new ResponseEntity(MajorcaseInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改MajorcaseInfo")
    @ApiOperation(value = "修改MajorcaseInfo")
    @PutMapping(value = "/MajorcaseInfo")
    @PreAuthorize("hasAnyRole('ADMIN','MAJORCASEINFO_ALL','MAJORCASEINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody MajorcaseInfo resources){
        MajorcaseInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除MajorcaseInfo")
    @ApiOperation(value = "删除MajorcaseInfo")
    @DeleteMapping(value = "/MajorcaseInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MAJORCASEINFO_ALL','MAJORCASEINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        MajorcaseInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}