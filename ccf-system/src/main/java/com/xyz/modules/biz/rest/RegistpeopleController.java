package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Registpeople;
import com.xyz.modules.biz.service.RegistpeopleService;
import com.xyz.modules.biz.service.dto.RegistpeopleQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author lx
* @date 2020-04-08
*/
@Api(tags = "Registpeople管理")
@RestController
@RequestMapping("api")
public class RegistpeopleController {

    @Autowired
    private RegistpeopleService RegistpeopleService;

    @Log("查询Registpeople")
    @ApiOperation(value = "查询Registpeople")
    @GetMapping(value = "/Registpeople")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_SELECT')")
    public ResponseEntity getRegistpeoples(RegistpeopleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(RegistpeopleService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Registpeople")
    @ApiOperation(value = "新增Registpeople")
    @PostMapping(value = "/Registpeople")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Registpeople resources){
        return new ResponseEntity(RegistpeopleService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Registpeople")
    @ApiOperation(value = "修改Registpeople")
    @PutMapping(value = "/Registpeople")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Registpeople resources){
        RegistpeopleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Registpeople")
    @ApiOperation(value = "删除Registpeople")
    @DeleteMapping(value = "/Registpeople/{regisId}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_DELETE')")
    public ResponseEntity delete(@PathVariable String regisId){
        RegistpeopleService.delete(regisId);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @Log("查看Registpeople")
//    @ApiOperation(value = "查看Registpeople")
//    @DeleteMapping(value = "/Registpeople/{regisId}")
//    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_DELETE')")
//    public ResponseEntity getById(@PathVariable String regisId){
//        return new ResponseEntity(RegistpeopleService.findById(regisId),HttpStatus.OK);
//    }
}