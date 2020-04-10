package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.CorrectPerson;
import com.xyz.modules.biz.service.CorrectPersonService;
import com.xyz.modules.biz.service.dto.CorrectPersonQueryCriteria;
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
@Api(tags = "CorrectPerson管理")
@RestController
@RequestMapping("api")
public class CorrectPersonController {

    @Autowired
    private CorrectPersonService CorrectPersonService;

    @Log("查询CorrectPerson")
    @ApiOperation(value = "查询CorrectPerson")
    @GetMapping(value = "/CorrectPerson")
    @PreAuthorize("hasAnyRole('ADMIN','CORRECTPERSON_ALL','CORRECTPERSON_SELECT')")
    public ResponseEntity getCorrectPersons(CorrectPersonQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(CorrectPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增CorrectPerson")
    @ApiOperation(value = "新增CorrectPerson")
    @PostMapping(value = "/CorrectPerson")
    @PreAuthorize("hasAnyRole('ADMIN','CORRECTPERSON_ALL','CORRECTPERSON_CREATE')")
    public ResponseEntity create(@Validated @RequestBody CorrectPerson resources){
        return new ResponseEntity(CorrectPersonService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改CorrectPerson")
    @ApiOperation(value = "修改CorrectPerson")
    @PutMapping(value = "/CorrectPerson")
    @PreAuthorize("hasAnyRole('ADMIN','CORRECTPERSON_ALL','CORRECTPERSON_EDIT')")
    public ResponseEntity update(@Validated @RequestBody CorrectPerson resources){
        CorrectPersonService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除CorrectPerson")
    @ApiOperation(value = "删除CorrectPerson")
    @DeleteMapping(value = "/CorrectPerson/{correctId}")
    @PreAuthorize("hasAnyRole('ADMIN','CORRECTPERSON_ALL','CORRECTPERSON_DELETE')")
    public ResponseEntity delete(@PathVariable String correctId){
        CorrectPersonService.delete(correctId);
        return new ResponseEntity(HttpStatus.OK);
    }
}