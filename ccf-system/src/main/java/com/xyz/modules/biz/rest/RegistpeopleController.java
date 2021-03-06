package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.actual.entity.Registpeople;
import com.xyz.modules.biz.service.actual.RegistpeopleService;
import com.xyz.modules.biz.service.actual.qo.RegistpeopleQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author lx
* @date 2020-04-08
 * 实有人口/户籍人员信息 CRUD，机构权限向下查询。
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
    @Log("查询详情Registpeople")
    @ApiOperation(value = "查询详情Registpeople")
    @GetMapping(value = "/Registpeople/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_SELECT')")
    public ResponseEntity getById(@PathVariable String id ){
        return new ResponseEntity(RegistpeopleService.findById(id),HttpStatus.OK);
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

    @Log("查询Registpeople")
    @ApiOperation(value = "验证身份证号码存在")
    @GetMapping(value = "/Registpeople/validateIdentityNum/{id}&{identityNum}")
    @PreAuthorize("hasAnyRole('ADMIN','NONPUBLIC_ALL','NONPUBLIC_SELECT')")
    public ResponseEntity verifyCreditCode(@PathVariable String id,@PathVariable String identityNum){
        return new ResponseEntity(RegistpeopleService.validateIdentityNum(id,identityNum),HttpStatus.OK);
    }

    @Log("查询变更记录列表")
    @ApiOperation(value = "查询变更记录")
    @GetMapping(value = "/Registpeople/ModifyRecords/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','FLOATPEOPLE_ALL','FLOATPEOPLE_SELECT')")
    public ResponseEntity findModifyRecordsById(@PathVariable String id){
        return new ResponseEntity(RegistpeopleService.findModifyRecordsById(id),HttpStatus.OK);
    }

}