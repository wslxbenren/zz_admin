package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Convenientinfo;
import com.xyz.modules.biz.service.ConvenientinfoService;
import com.xyz.modules.biz.service.dto.ConvenientinfoQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Api(tags = "Convenientinfo管理")
@RestController
@RequestMapping("api")
public class ConvenientinfoController {

    @Autowired
    private ConvenientinfoService ConvenientinfoService;

    @Log("查询Convenientinfo")
    @ApiOperation(value = "查询Convenientinfo")
    @GetMapping(value = "/Convenientinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CONVENIENTINFO_ALL','CONVENIENTINFO_SELECT')")
    public ResponseEntity getConvenientinfos(ConvenientinfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(ConvenientinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Convenientinfo")
    @ApiOperation(value = "新增Convenientinfo")
    @PostMapping(value = "/Convenientinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CONVENIENTINFO_ALL','CONVENIENTINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Convenientinfo resources){
        return new ResponseEntity(ConvenientinfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Convenientinfo")
    @ApiOperation(value = "修改Convenientinfo")
    @PutMapping(value = "/Convenientinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CONVENIENTINFO_ALL','CONVENIENTINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Convenientinfo resources){
        ConvenientinfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Convenientinfo")
    @ApiOperation(value = "删除Convenientinfo")
    @DeleteMapping(value = "/Convenientinfo/{conId}")
    @PreAuthorize("hasAnyRole('ADMIN','CONVENIENTINFO_ALL','CONVENIENTINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String conId){
        ConvenientinfoService.delete(conId);
        return new ResponseEntity(HttpStatus.OK);
    }
}