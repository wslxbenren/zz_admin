package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Suspectinfo;
import com.xyz.modules.biz.service.SuspectinfoService;
import com.xyz.modules.biz.service.dto.SuspectinfoQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Api(tags = "Suspectinfo管理")
@RestController
@RequestMapping("api")
public class SuspectinfoController {

    @Autowired
    private SuspectinfoService SuspectinfoService;

    @Log("查询Suspectinfo")
    @ApiOperation(value = "查询Suspectinfo")
    @GetMapping(value = "/Suspectinfo")
    @PreAuthorize("hasAnyRole('ADMIN','SUSPECTINFO_ALL','SUSPECTINFO_SELECT')")
    public ResponseEntity getSuspectinfos(SuspectinfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(SuspectinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Suspectinfo")
    @ApiOperation(value = "新增Suspectinfo")
    @PostMapping(value = "/Suspectinfo")
    @PreAuthorize("hasAnyRole('ADMIN','SUSPECTINFO_ALL','SUSPECTINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Suspectinfo resources){
        return new ResponseEntity(SuspectinfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Suspectinfo")
    @ApiOperation(value = "修改Suspectinfo")
    @PutMapping(value = "/Suspectinfo")
    @PreAuthorize("hasAnyRole('ADMIN','SUSPECTINFO_ALL','SUSPECTINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Suspectinfo resources){
        SuspectinfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Suspectinfo")
    @ApiOperation(value = "删除Suspectinfo")
    @DeleteMapping(value = "/Suspectinfo/{suspId}")
    @PreAuthorize("hasAnyRole('ADMIN','SUSPECTINFO_ALL','SUSPECTINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String suspId){
        SuspectinfoService.delete(suspId);
        return new ResponseEntity(HttpStatus.OK);
    }
}