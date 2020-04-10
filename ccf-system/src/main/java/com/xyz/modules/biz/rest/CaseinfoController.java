package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Caseinfo;
import com.xyz.modules.biz.service.CaseinfoService;
import com.xyz.modules.biz.service.dto.CaseinfoQueryCriteria;
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
@Api(tags = "Caseinfo管理")
@RestController
@RequestMapping("api")
public class CaseinfoController {

    @Autowired
    private CaseinfoService CaseinfoService;

    @Log("查询Caseinfo")
    @ApiOperation(value = "查询Caseinfo")
    @GetMapping(value = "/Caseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CASEINFO_ALL','CASEINFO_SELECT')")
    public ResponseEntity getCaseinfos(CaseinfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(CaseinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Caseinfo")
    @ApiOperation(value = "新增Caseinfo")
    @PostMapping(value = "/Caseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CASEINFO_ALL','CASEINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Caseinfo resources){
        return new ResponseEntity(CaseinfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Caseinfo")
    @ApiOperation(value = "修改Caseinfo")
    @PutMapping(value = "/Caseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CASEINFO_ALL','CASEINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Caseinfo resources){
        CaseinfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Caseinfo")
    @ApiOperation(value = "删除Caseinfo")
    @DeleteMapping(value = "/Caseinfo/{caseId}")
    @PreAuthorize("hasAnyRole('ADMIN','CASEINFO_ALL','CASEINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String caseId){
        CaseinfoService.delete(caseId);
        return new ResponseEntity(HttpStatus.OK);
    }
}