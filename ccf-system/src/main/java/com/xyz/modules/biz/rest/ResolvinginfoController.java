package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.dispute.entity.Resolvinginfo;
import com.xyz.modules.biz.service.dispute.ResolvinginfoService;
import com.xyz.modules.biz.service.dispute.qo.ResolvinginfoQueryCriteria;
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
@Api(tags = "Resolvinginfo管理")
@RestController
@RequestMapping("api")
public class ResolvinginfoController {

    @Autowired
    private ResolvinginfoService ResolvinginfoService;

    @Log("查询Resolvinginfo")
    @ApiOperation(value = "查询Resolvinginfo")
    @GetMapping(value = "/Resolvinginfo")
    @PreAuthorize("hasAnyRole('ADMIN','RESOLVINGINFO_ALL','RESOLVINGINFO_SELECT')")
    public ResponseEntity getResolvinginfos(ResolvinginfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(ResolvinginfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Resolvinginfo")
    @ApiOperation(value = "新增Resolvinginfo")
    @PostMapping(value = "/Resolvinginfo")
    @PreAuthorize("hasAnyRole('ADMIN','RESOLVINGINFO_ALL','RESOLVINGINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Resolvinginfo resources){
        return new ResponseEntity(ResolvinginfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Resolvinginfo")
    @ApiOperation(value = "修改Resolvinginfo")
    @PutMapping(value = "/Resolvinginfo")
    @PreAuthorize("hasAnyRole('ADMIN','RESOLVINGINFO_ALL','RESOLVINGINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Resolvinginfo resources){
        ResolvinginfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Resolvinginfo")
    @ApiOperation(value = "删除Resolvinginfo")
    @DeleteMapping(value = "/Resolvinginfo/{resolvId}")
    @PreAuthorize("hasAnyRole('ADMIN','RESOLVINGINFO_ALL','RESOLVINGINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String resolvId){
        ResolvinginfoService.delete(resolvId);
        return new ResponseEntity(HttpStatus.OK);
    }
}