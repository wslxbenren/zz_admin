package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.BizSchoolKeypersoninfo;
import com.xyz.modules.biz.service.BizSchoolKeypersoninfoService;
import com.xyz.modules.biz.service.dto.BizSchoolKeypersoninfoQueryCriteria;
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
@Api(tags = "BizSchoolKeypersoninfo管理")
@RestController
@RequestMapping("api")
public class BizSchoolKeypersoninfoController {

    @Autowired
    private BizSchoolKeypersoninfoService bizSchoolKeypersoninfoService;

    @Log("查询BizSchoolKeypersoninfo")
    @ApiOperation(value = "查询BizSchoolKeypersoninfo")
    @GetMapping(value = "/bizSchoolKeypersoninfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLKEYPERSONINFO_ALL','BIZSCHOOLKEYPERSONINFO_SELECT')")
    public ResponseEntity getBizSchoolKeypersoninfos(BizSchoolKeypersoninfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(bizSchoolKeypersoninfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增BizSchoolKeypersoninfo")
    @ApiOperation(value = "新增BizSchoolKeypersoninfo")
    @PostMapping(value = "/bizSchoolKeypersoninfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLKEYPERSONINFO_ALL','BIZSCHOOLKEYPERSONINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody BizSchoolKeypersoninfo resources){
        return new ResponseEntity(bizSchoolKeypersoninfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改BizSchoolKeypersoninfo")
    @ApiOperation(value = "修改BizSchoolKeypersoninfo")
    @PutMapping(value = "/bizSchoolKeypersoninfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLKEYPERSONINFO_ALL','BIZSCHOOLKEYPERSONINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody BizSchoolKeypersoninfo resources){
        bizSchoolKeypersoninfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BizSchoolKeypersoninfo")
    @ApiOperation(value = "删除BizSchoolKeypersoninfo")
    @DeleteMapping(value = "/bizSchoolKeypersoninfo/{keyId}")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLKEYPERSONINFO_ALL','BIZSCHOOLKEYPERSONINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String keyId){
        bizSchoolKeypersoninfoService.delete(keyId);
        return new ResponseEntity(HttpStatus.OK);
    }
}