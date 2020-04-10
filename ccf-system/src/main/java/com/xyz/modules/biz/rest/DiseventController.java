package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Disevent;
import com.xyz.modules.biz.service.DiseventService;
import com.xyz.modules.biz.service.dto.DiseventQueryCriteria;
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
@Api(tags = "Disevent管理")
@RestController
@RequestMapping("api")
public class DiseventController {

    @Autowired
    private DiseventService DiseventService;

    @Log("查询Disevent")
    @ApiOperation(value = "查询Disevent")
    @GetMapping(value = "/Disevent")
    @PreAuthorize("hasAnyRole('ADMIN','DISEVENT_ALL','DISEVENT_SELECT')")
    public ResponseEntity getDisevents(DiseventQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(DiseventService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Disevent")
    @ApiOperation(value = "新增Disevent")
    @PostMapping(value = "/Disevent")
    @PreAuthorize("hasAnyRole('ADMIN','DISEVENT_ALL','DISEVENT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Disevent resources){
        return new ResponseEntity(DiseventService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Disevent")
    @ApiOperation(value = "修改Disevent")
    @PutMapping(value = "/Disevent")
    @PreAuthorize("hasAnyRole('ADMIN','DISEVENT_ALL','DISEVENT_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Disevent resources){
        DiseventService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Disevent")
    @ApiOperation(value = "删除Disevent")
    @DeleteMapping(value = "/Disevent/{eventId}")
    @PreAuthorize("hasAnyRole('ADMIN','DISEVENT_ALL','DISEVENT_DELETE')")
    public ResponseEntity delete(@PathVariable String eventId){
        DiseventService.delete(eventId);
        return new ResponseEntity(HttpStatus.OK);
    }
}