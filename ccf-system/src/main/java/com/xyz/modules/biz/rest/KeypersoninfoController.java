package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.route.entity.Keypersoninfo;
import com.xyz.modules.biz.service.route.KeypersoninfoService;
import com.xyz.modules.biz.service.route.qo.KeypersoninfoQueryCriteria;
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
@Api(tags = "Keypersoninfo管理--/护路护线/线路周边重点人员管理信息表线路周边重点人员管理信息表")
@RestController
@RequestMapping("api")
public class KeypersoninfoController {

    @Autowired
    private KeypersoninfoService KeypersoninfoService;

    @Log("查询Keypersoninfo")
    @ApiOperation(value = "查询Keypersoninfo")
    @GetMapping(value = "/Keypersoninfo")
    @PreAuthorize("hasAnyRole('ADMIN','KEYPERSONINFO_ALL','KEYPERSONINFO_SELECT')")
    public ResponseEntity getKeypersoninfos(KeypersoninfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(KeypersoninfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Keypersoninfo")
    @ApiOperation(value = "新增Keypersoninfo")
    @PostMapping(value = "/Keypersoninfo")
    @PreAuthorize("hasAnyRole('ADMIN','KEYPERSONINFO_ALL','KEYPERSONINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Keypersoninfo resources){
        return new ResponseEntity(KeypersoninfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Keypersoninfo")
    @ApiOperation(value = "修改Keypersoninfo")
    @PutMapping(value = "/Keypersoninfo")
    @PreAuthorize("hasAnyRole('ADMIN','KEYPERSONINFO_ALL','KEYPERSONINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Keypersoninfo resources){
        KeypersoninfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Keypersoninfo")
    @ApiOperation(value = "删除Keypersoninfo")
    @DeleteMapping(value = "/Keypersoninfo/{keyId}")
    @PreAuthorize("hasAnyRole('ADMIN','KEYPERSONINFO_ALL','KEYPERSONINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String keyId){
        KeypersoninfoService.delete(keyId);
        return new ResponseEntity(HttpStatus.OK);
    }
}