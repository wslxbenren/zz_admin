package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.route.entity.Rectificmanage;
import com.xyz.modules.biz.service.route.RectificmanageService;
import com.xyz.modules.biz.service.route.qo.RectificmanageQueryCriteria;
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
@Api(tags = "Rectificmanage管理")
@RestController
@RequestMapping("api")
public class RectificmanageController {

    @Autowired
    private RectificmanageService RectificmanageService;

    @Log("查询Rectificmanage")
    @ApiOperation(value = "查询Rectificmanage")
    @GetMapping(value = "/Rectificmanage")
    @PreAuthorize("hasAnyRole('ADMIN','RECTIFICMANAGE_ALL','RECTIFICMANAGE_SELECT')")
    public ResponseEntity getRectificmanages(RectificmanageQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(RectificmanageService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Rectificmanage")
    @ApiOperation(value = "新增Rectificmanage")
    @PostMapping(value = "/Rectificmanage")
    @PreAuthorize("hasAnyRole('ADMIN','RECTIFICMANAGE_ALL','RECTIFICMANAGE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Rectificmanage resources){
        return new ResponseEntity(RectificmanageService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Rectificmanage")
    @ApiOperation(value = "修改Rectificmanage")
    @PutMapping(value = "/Rectificmanage")
    @PreAuthorize("hasAnyRole('ADMIN','RECTIFICMANAGE_ALL','RECTIFICMANAGE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Rectificmanage resources){
        RectificmanageService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Rectificmanage")
    @ApiOperation(value = "删除Rectificmanage")
    @DeleteMapping(value = "/Rectificmanage/{rectId}")
    @PreAuthorize("hasAnyRole('ADMIN','RECTIFICMANAGE_ALL','RECTIFICMANAGE_DELETE')")
    public ResponseEntity delete(@PathVariable String rectId){
        RectificmanageService.delete(rectId);
        return new ResponseEntity(HttpStatus.OK);
    }
}