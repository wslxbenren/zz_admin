package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.ReleasedPerson;
import com.xyz.modules.biz.service.ReleasedPersonService;
import com.xyz.modules.biz.service.dto.ReleasedPersonQueryCriteria;
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
@Api(tags = "ReleasedPerson管理")
@RestController
@RequestMapping("api")
public class ReleasedPersonController {

    @Autowired
    private ReleasedPersonService ReleasedPersonService;

    @Log("查询ReleasedPerson")
    @ApiOperation(value = "查询ReleasedPerson")
    @GetMapping(value = "/ReleasedPerson")
    @PreAuthorize("hasAnyRole('ADMIN','RELEASEDPERSON_ALL','RELEASEDPERSON_SELECT')")
    public ResponseEntity getReleasedPersons(ReleasedPersonQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(ReleasedPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增ReleasedPerson")
    @ApiOperation(value = "新增ReleasedPerson")
    @PostMapping(value = "/ReleasedPerson")
    @PreAuthorize("hasAnyRole('ADMIN','RELEASEDPERSON_ALL','RELEASEDPERSON_CREATE')")
    public ResponseEntity create(@Validated @RequestBody ReleasedPerson resources){
        return new ResponseEntity(ReleasedPersonService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ReleasedPerson")
    @ApiOperation(value = "修改ReleasedPerson")
    @PutMapping(value = "/ReleasedPerson")
    @PreAuthorize("hasAnyRole('ADMIN','RELEASEDPERSON_ALL','RELEASEDPERSON_EDIT')")
    public ResponseEntity update(@Validated @RequestBody ReleasedPerson resources){
        ReleasedPersonService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ReleasedPerson")
    @ApiOperation(value = "删除ReleasedPerson")
    @DeleteMapping(value = "/ReleasedPerson/{releasedId}")
    @PreAuthorize("hasAnyRole('ADMIN','RELEASEDPERSON_ALL','RELEASEDPERSON_DELETE')")
    public ResponseEntity delete(@PathVariable String releasedId){
        ReleasedPersonService.delete(releasedId);
        return new ResponseEntity(HttpStatus.OK);
    }
}