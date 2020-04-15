package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.organ.entity.Socialorgan;
import com.xyz.modules.biz.service.organ.SocialorganService;
import com.xyz.modules.biz.service.organ.qo.SocialorganQueryCriteria;
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
@Api(tags = "Socialorgan管理")
@RestController
@RequestMapping("api")
public class SocialorganController {

    @Autowired
    private SocialorganService SocialorganService;

    @Log("查询Socialorgan")
    @ApiOperation(value = "查询Socialorgan")
    @GetMapping(value = "/Socialorgan")
    @PreAuthorize("hasAnyRole('ADMIN','SOCIALORGAN_ALL','SOCIALORGAN_SELECT')")
    public ResponseEntity getSocialorgans(SocialorganQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(SocialorganService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Socialorgan")
    @ApiOperation(value = "新增Socialorgan")
    @PostMapping(value = "/Socialorgan")
    @PreAuthorize("hasAnyRole('ADMIN','SOCIALORGAN_ALL','SOCIALORGAN_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Socialorgan resources){
        return new ResponseEntity(SocialorganService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Socialorgan")
    @ApiOperation(value = "修改Socialorgan")
    @PutMapping(value = "/Socialorgan")
    @PreAuthorize("hasAnyRole('ADMIN','SOCIALORGAN_ALL','SOCIALORGAN_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Socialorgan resources){
        SocialorganService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Socialorgan")
    @ApiOperation(value = "删除Socialorgan")
    @DeleteMapping(value = "/Socialorgan/{sociaId}")
    @PreAuthorize("hasAnyRole('ADMIN','SOCIALORGAN_ALL','SOCIALORGAN_DELETE')")
    public ResponseEntity delete(@PathVariable String sociaId){
        SocialorganService.delete(sociaId);
        return new ResponseEntity(HttpStatus.OK);
    }
}