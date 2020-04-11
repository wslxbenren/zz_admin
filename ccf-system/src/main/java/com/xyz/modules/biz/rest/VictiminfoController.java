package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Victiminfo;
import com.xyz.modules.biz.service.VictiminfoService;
import com.xyz.modules.biz.service.dto.VictiminfoQueryCriteria;
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
@Api(tags = "Victiminfo管理")
@RestController
@RequestMapping("api")
public class VictiminfoController {

    @Autowired
    private VictiminfoService VictiminfoService;

    @Log("查询Victiminfo")
    @ApiOperation(value = "查询Victiminfo")
    @GetMapping(value = "/Victiminfo")
    @PreAuthorize("hasAnyRole('ADMIN','VICTIMINFO_ALL','VICTIMINFO_SELECT')")
    public ResponseEntity getVictiminfos(VictiminfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(VictiminfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Victiminfo")
    @ApiOperation(value = "新增Victiminfo")
    @PostMapping(value = "/Victiminfo")
    @PreAuthorize("hasAnyRole('ADMIN','VICTIMINFO_ALL','VICTIMINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Victiminfo resources){
        return new ResponseEntity(VictiminfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Victiminfo")
    @ApiOperation(value = "修改Victiminfo")
    @PutMapping(value = "/Victiminfo")
    @PreAuthorize("hasAnyRole('ADMIN','VICTIMINFO_ALL','VICTIMINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Victiminfo resources){
        VictiminfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Victiminfo")
    @ApiOperation(value = "删除Victiminfo")
    @DeleteMapping(value = "/Victiminfo/{vicId}")
    @PreAuthorize("hasAnyRole('ADMIN','VICTIMINFO_ALL','VICTIMINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String vicId){
        VictiminfoService.delete(vicId);
        return new ResponseEntity(HttpStatus.OK);
    }
}