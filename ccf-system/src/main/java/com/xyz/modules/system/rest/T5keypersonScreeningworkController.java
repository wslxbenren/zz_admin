package com.xyz.modules.system.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.system.domain.T5keypersonScreeningwork;
import com.xyz.modules.system.service.T5keypersonScreeningworkService;
import com.xyz.modules.system.service.dto.T5keypersonScreeningworkQueryCriteria;
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
@Api(tags = "单位关注重点人员排查工作汇总表")
@RestController
@RequestMapping("api")
public class T5keypersonScreeningworkController {

    @Autowired
    private T5keypersonScreeningworkService t5keypersonScreeningworkService;

    @Log("查询T5keypersonScreeningwork")
    @ApiOperation(value = "查询T5keypersonScreeningwork")
    @GetMapping(value = "/t5keypersonScreeningwork")
    @PreAuthorize("hasAnyRole('ADMIN','T5KEYPERSONSCREENINGWORK_ALL','T5KEYPERSONSCREENINGWORK_SELECT')")
    public ResponseEntity getT5keypersonScreeningworks(T5keypersonScreeningworkQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(t5keypersonScreeningworkService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增T5keypersonScreeningwork")
    @ApiOperation(value = "新增T5keypersonScreeningwork")
    @PostMapping(value = "/t5keypersonScreeningwork")
    @PreAuthorize("hasAnyRole('ADMIN','T5KEYPERSONSCREENINGWORK_ALL','T5KEYPERSONSCREENINGWORK_CREATE')")
    public ResponseEntity create(@Validated @RequestBody T5keypersonScreeningwork resources){
        return new ResponseEntity(t5keypersonScreeningworkService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改T5keypersonScreeningwork")
    @ApiOperation(value = "修改T5keypersonScreeningwork")
    @PutMapping(value = "/t5keypersonScreeningwork")
    @PreAuthorize("hasAnyRole('ADMIN','T5KEYPERSONSCREENINGWORK_ALL','T5KEYPERSONSCREENINGWORK_EDIT')")
    public ResponseEntity update(@Validated @RequestBody T5keypersonScreeningwork resources){
        t5keypersonScreeningworkService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除T5keypersonScreeningwork")
    @ApiOperation(value = "删除T5keypersonScreeningwork")
    @DeleteMapping(value = "/t5keypersonScreeningwork/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','T5KEYPERSONSCREENINGWORK_ALL','T5KEYPERSONSCREENINGWORK_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        t5keypersonScreeningworkService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}