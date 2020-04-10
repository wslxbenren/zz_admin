package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.BizSecurKeyareas;
import com.xyz.modules.biz.service.BizSecurKeyareasService;
import com.xyz.modules.biz.service.dto.BizSecurKeyareasQueryCriteria;
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
@Api(tags = "BizSecurKeyareas管理")
@RestController
@RequestMapping("api")
public class BizSecurKeyareasController {

    @Autowired
    private BizSecurKeyareasService bizSecurKeyareasService;

    @Log("查询BizSecurKeyareas")
    @ApiOperation(value = "查询BizSecurKeyareas")
    @GetMapping(value = "/bizSecurKeyareas")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURKEYAREAS_ALL','BIZSECURKEYAREAS_SELECT')")
    public ResponseEntity getBizSecurKeyareass(BizSecurKeyareasQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(bizSecurKeyareasService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增BizSecurKeyareas")
    @ApiOperation(value = "新增BizSecurKeyareas")
    @PostMapping(value = "/bizSecurKeyareas")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURKEYAREAS_ALL','BIZSECURKEYAREAS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody BizSecurKeyareas resources){
        return new ResponseEntity(bizSecurKeyareasService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改BizSecurKeyareas")
    @ApiOperation(value = "修改BizSecurKeyareas")
    @PutMapping(value = "/bizSecurKeyareas")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURKEYAREAS_ALL','BIZSECURKEYAREAS_EDIT')")
    public ResponseEntity update(@Validated @RequestBody BizSecurKeyareas resources){
        bizSecurKeyareasService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BizSecurKeyareas")
    @ApiOperation(value = "删除BizSecurKeyareas")
    @DeleteMapping(value = "/bizSecurKeyareas/{keyId}")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURKEYAREAS_ALL','BIZSECURKEYAREAS_DELETE')")
    public ResponseEntity delete(@PathVariable String keyId){
        bizSecurKeyareasService.delete(keyId);
        return new ResponseEntity(HttpStatus.OK);
    }
}