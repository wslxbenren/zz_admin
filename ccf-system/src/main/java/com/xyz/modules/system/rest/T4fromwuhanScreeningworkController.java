package com.xyz.modules.system.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.system.domain.T4fromwuhanScreeningwork;
import com.xyz.modules.system.service.T4fromwuhanScreeningworkService;
import com.xyz.modules.system.service.dto.T4fromwuhanScreeningworkQueryCriteria;
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
@Api(tags = "武汉来郑人排查工作开展情况统计表")
@RestController
@RequestMapping("api")
public class T4fromwuhanScreeningworkController {

    @Autowired
    private T4fromwuhanScreeningworkService t4fromwuhanScreeningworkService;

    @Log("查询T4fromwuhanScreeningwork")
    @ApiOperation(value = "查询T4fromwuhanScreeningwork")
    @GetMapping(value = "/t4fromwuhanScreeningwork")
    @PreAuthorize("hasAnyRole('ADMIN','T4FROMWUHANSCREENINGWORK_ALL','T4FROMWUHANSCREENINGWORK_SELECT')")
    public ResponseEntity getT4fromwuhanScreeningworks(T4fromwuhanScreeningworkQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(t4fromwuhanScreeningworkService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增T4fromwuhanScreeningwork")
    @ApiOperation(value = "新增T4fromwuhanScreeningwork")
    @PostMapping(value = "/t4fromwuhanScreeningwork")
    @PreAuthorize("hasAnyRole('ADMIN','T4FROMWUHANSCREENINGWORK_ALL','T4FROMWUHANSCREENINGWORK_CREATE')")
    public ResponseEntity create(@Validated @RequestBody T4fromwuhanScreeningwork resources){
        return new ResponseEntity(t4fromwuhanScreeningworkService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改T4fromwuhanScreeningwork")
    @ApiOperation(value = "修改T4fromwuhanScreeningwork")
    @PutMapping(value = "/t4fromwuhanScreeningwork")
    @PreAuthorize("hasAnyRole('ADMIN','T4FROMWUHANSCREENINGWORK_ALL','T4FROMWUHANSCREENINGWORK_EDIT')")
    public ResponseEntity update(@Validated @RequestBody T4fromwuhanScreeningwork resources){
        t4fromwuhanScreeningworkService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除T4fromwuhanScreeningwork")
    @ApiOperation(value = "删除T4fromwuhanScreeningwork")
    @DeleteMapping(value = "/t4fromwuhanScreeningwork/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','T4FROMWUHANSCREENINGWORK_ALL','T4FROMWUHANSCREENINGWORK_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        t4fromwuhanScreeningworkService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("T4按单位分组查询")
    @ApiOperation(value = "T4按单位分组查询")
    @GetMapping(value = "/t4fromwuhanScreeningwork/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','T4FROMWUHANSCREENINGWORK_ALL','T4FROMWUHANSCREENINGWORK_SELECT')")
    public ResponseEntity GroupBy(@PathVariable String name){
//        t4fromwuhanScreeningworkService.selectGroupBy(name);
        return new ResponseEntity(t4fromwuhanScreeningworkService.selectGroupBy(name),HttpStatus.OK);
    }
}