package com.xyz.modules.system.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.system.domain.T2socialpatrolWorkingday;
import com.xyz.modules.system.service.T2socialpatrolWorkingdayService;
import com.xyz.modules.system.service.dto.T2socialpatrolWorkingdayQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Api(tags = "社会面巡逻防控工作日统计表")
@RestController
@RequestMapping("api")
public class T2socialpatrolWorkingdayController {

    @Autowired
    private T2socialpatrolWorkingdayService t2socialpatrolWorkingdayService;

    @Log("查询T2socialpatrolWorkingday")
    @ApiOperation(value = "查询T2socialpatrolWorkingday")
    @GetMapping(value = "/t2socialpatrolWorkingday")
    @PreAuthorize("hasAnyRole('ADMIN','T2SOCIALPATROLWORKINGDAY_ALL','T2SOCIALPATROLWORKINGDAY_SELECT')")
    public ResponseEntity getT2socialpatrolWorkingdays(T2socialpatrolWorkingdayQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(t2socialpatrolWorkingdayService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增T2socialpatrolWorkingday")
    @ApiOperation(value = "新增T2socialpatrolWorkingday")
    @PostMapping(value = "/t2socialpatrolWorkingday")
    @PreAuthorize("hasAnyRole('ADMIN','T2SOCIALPATROLWORKINGDAY_ALL','T2SOCIALPATROLWORKINGDAY_CREATE')")
    public ResponseEntity create(@Validated @RequestBody T2socialpatrolWorkingday resources){
        return new ResponseEntity(t2socialpatrolWorkingdayService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改T2socialpatrolWorkingday")
    @ApiOperation(value = "修改T2socialpatrolWorkingday")
    @PutMapping(value = "/t2socialpatrolWorkingday")
    @PreAuthorize("hasAnyRole('ADMIN','T2SOCIALPATROLWORKINGDAY_ALL','T2SOCIALPATROLWORKINGDAY_EDIT')")
    public ResponseEntity update(@Validated @RequestBody T2socialpatrolWorkingday resources){
        t2socialpatrolWorkingdayService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除T2socialpatrolWorkingday")
    @ApiOperation(value = "删除T2socialpatrolWorkingday")
    @DeleteMapping(value = "/t2socialpatrolWorkingday/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','T2SOCIALPATROLWORKINGDAY_ALL','T2SOCIALPATROLWORKINGDAY_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        t2socialpatrolWorkingdayService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}