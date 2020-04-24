package com.xyz.modules.system.rest;

import com.xyz.aop.log.Log;
import com.xyz.config.DataScope;
import com.xyz.modules.system.domain.Dept;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.dto.DeptDTO;
import com.xyz.modules.system.service.dto.DeptQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Zheng Jie
 * @date 2019-03-25
 */
@RestController
@RequestMapping("api")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DataScope dataScope;

    private static final String ENTITY_NAME = "dept";

    @Log("查询部门")
    @GetMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT','DEPT_ALL','DEPT_SELECT')")
    public ResponseEntity getDepts(DeptQueryCriteria criteria){
        criteria.setCodes(Optional.ofNullable(dataScope.getDeptCodesWithRole()).orElse(new HashSet<>()));
        List<DeptDTO> deptDTOS = deptService.queryAll(criteria);
        return new ResponseEntity(deptService.buildTree(deptDTOS),HttpStatus.OK);
    }

    @GetMapping(value = "/getDeptWithoutDict")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT','DEPT_ALL','DEPT_SELECT')")
    public ResponseEntity getDeptWithoutDict(DeptQueryCriteria criteria) {
        criteria.setCodes(Optional.ofNullable(dataScope.getDeptCodesWithRole()).orElse(new HashSet<>()));
        List<DeptDTO> deptDTOS = deptService.queryWithoutDict(criteria);
        return new ResponseEntity(deptService.buildTree(deptDTOS),HttpStatus.OK);
    }

    @Log("查询部门")
    @GetMapping(value = "/deptMsg")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT','DEPT_ALL','DEPT_SELECT')")
    public ResponseEntity getDept(DeptQueryCriteria criteria){
        List<DeptDTO> deptDTOS = deptService.queryAll(criteria);
        return new ResponseEntity(deptDTOS,HttpStatus.OK);
    }

    @Log("新增部门")
    @PostMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Dept resources){
        return new ResponseEntity(deptService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改部门")
    @PutMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_EDIT')")
    public ResponseEntity update(@Validated(Dept.Update.class) @RequestBody Dept resources){
        deptService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除部门")
    @DeleteMapping(value = "/dept/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        deptService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}