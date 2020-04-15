package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.school.entity.BizSchoolBaseinfo;
import com.xyz.modules.biz.service.school.BizSchoolBaseinfoService;
import com.xyz.modules.biz.service.school.qo.BizSchoolBaseinfoQueryCriteria;
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
@Api(tags = "BizSchoolBaseinfo管理")
@RestController
@RequestMapping("api")
public class BizSchoolBaseinfoController {

    @Autowired
    private BizSchoolBaseinfoService bizSchoolBaseinfoService;

    @Log("查询BizSchoolBaseinfo")
    @ApiOperation(value = "查询BizSchoolBaseinfo")
    @GetMapping(value = "/bizSchoolBaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLBASEINFO_ALL','BIZSCHOOLBASEINFO_SELECT')")
    public ResponseEntity getBizSchoolBaseinfos(BizSchoolBaseinfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(bizSchoolBaseinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增BizSchoolBaseinfo")
    @ApiOperation(value = "新增BizSchoolBaseinfo")
    @PostMapping(value = "/bizSchoolBaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLBASEINFO_ALL','BIZSCHOOLBASEINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody BizSchoolBaseinfo resources){
        return new ResponseEntity(bizSchoolBaseinfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改BizSchoolBaseinfo")
    @ApiOperation(value = "修改BizSchoolBaseinfo")
    @PutMapping(value = "/bizSchoolBaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLBASEINFO_ALL','BIZSCHOOLBASEINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody BizSchoolBaseinfo resources){
        bizSchoolBaseinfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BizSchoolBaseinfo")
    @ApiOperation(value = "删除BizSchoolBaseinfo")
    @DeleteMapping(value = "/bizSchoolBaseinfo/{baseId}")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLBASEINFO_ALL','BIZSCHOOLBASEINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String baseId){
        bizSchoolBaseinfoService.delete(baseId);
        return new ResponseEntity(HttpStatus.OK);
    }
}