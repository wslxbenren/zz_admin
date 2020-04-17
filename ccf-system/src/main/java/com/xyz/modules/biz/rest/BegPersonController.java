package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.special.entity.BegPerson;
import com.xyz.modules.biz.service.special.BegPersonService;
import com.xyz.modules.biz.service.special.qo.BegPersonQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.DictService;
import com.xyz.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Api(tags = "BegPerson管理")
@RestController
@RequestMapping("api")
public class BegPersonController {

    @Autowired
    private BegPersonService BegPersonService;

    @Autowired
    private DictService dictService;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询BegPerson")
    @ApiOperation(value = "查询BegPerson")
    @GetMapping(value = "/BegPerson")
    @PreAuthorize("hasAnyRole('ADMIN','BEGPERSON_ALL','BEGPERSON_SELECT')")
    public ResponseEntity getBegPersons(BegPersonQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(BegPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增BegPerson")
    @ApiOperation(value = "新增BegPerson")
    @PostMapping(value = "/BegPerson")
    @PreAuthorize("hasAnyRole('ADMIN','BEGPERSON_ALL','BEGPERSON_CREATE')")
    public ResponseEntity create(@Validated @RequestBody BegPerson resources){
        return new ResponseEntity(BegPersonService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改BegPerson")
    @ApiOperation(value = "修改BegPerson")
    @PutMapping(value = "/BegPerson")
    @PreAuthorize("hasAnyRole('ADMIN','BEGPERSON_ALL','BEGPERSON_EDIT')")
    public ResponseEntity update(@Validated @RequestBody BegPerson resources){
        BegPersonService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BegPerson")
    @ApiOperation(value = "删除BegPerson")
    @DeleteMapping(value = "/BegPerson/{begId}")
    @PreAuthorize("hasAnyRole('ADMIN','BEGPERSON_ALL','BEGPERSON_DELETE')")
    public ResponseEntity delete(@PathVariable String begId){
        BegPersonService.delete(begId);
        return new ResponseEntity(HttpStatus.OK);
    }
}