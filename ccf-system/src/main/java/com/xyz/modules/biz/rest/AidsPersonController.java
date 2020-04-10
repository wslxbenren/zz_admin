package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.AidsPerson;
import com.xyz.modules.biz.service.AidsPersonService;
import com.xyz.modules.biz.service.dto.AidsPersonQueryCriteria;
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
@Api(tags = "AidsPerson管理")
@RestController
@RequestMapping("api")
public class AidsPersonController {

    @Autowired
    private AidsPersonService AidsPersonService;

    @Log("查询AidsPerson")
    @ApiOperation(value = "查询AidsPerson")
    @GetMapping(value = "/AidsPerson")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_SELECT')")
    public ResponseEntity getAidsPersons(AidsPersonQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(AidsPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增AidsPerson")
    @ApiOperation(value = "新增AidsPerson")
    @PostMapping(value = "/AidsPerson")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_CREATE')")
    public ResponseEntity create(@Validated @RequestBody AidsPerson resources){
        return new ResponseEntity(AidsPersonService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改AidsPerson")
    @ApiOperation(value = "修改AidsPerson")
    @PutMapping(value = "/AidsPerson")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_EDIT')")
    public ResponseEntity update(@Validated @RequestBody AidsPerson resources){
        AidsPersonService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AidsPerson")
    @ApiOperation(value = "删除AidsPerson")
    @DeleteMapping(value = "/AidsPerson/{aidsId}")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_DELETE')")
    public ResponseEntity delete(@PathVariable String aidsId){
        AidsPersonService.delete(aidsId);
        return new ResponseEntity(HttpStatus.OK);
    }
}