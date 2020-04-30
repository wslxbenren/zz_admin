package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.special.entity.ReleasedPerson;
import com.xyz.modules.biz.service.special.ReleasedPersonService;
import com.xyz.modules.biz.service.special.qo.ReleasedPersonQueryCriteria;
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
 * 特殊人群刑满释放人员 CRUD
 */
@Api(tags = "ReleasedPerson管理")
@RestController
@RequestMapping("api")
public class ReleasedPersonController {

    @Autowired
    private ReleasedPersonService ReleasedPersonService;

    @Autowired
    private DictService dictService;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询ReleasedPerson")
    @ApiOperation(value = "查询ReleasedPerson")
    @GetMapping(value = "/ReleasedPerson")
    @PreAuthorize("hasAnyRole('ADMIN','RELEASEDPERSON_ALL','RELEASEDPERSON_SELECT')")
    public ResponseEntity getReleasedPersons(ReleasedPersonQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(ReleasedPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("查询详情ReleasedPerson")
    @ApiOperation(value = "查询详情ReleasedPerson")
    @GetMapping(value = "/ReleasedPerson/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','RELEASEDPERSON_ALL','RELEASEDPERSON_SELECT')")
    public ResponseEntity getById(@PathVariable String id){
        return new ResponseEntity(ReleasedPersonService.findById(id),HttpStatus.OK);
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

    @Log("查询ReleasedPerson")
    @ApiOperation(value = "验证身份证号码存在")
    @GetMapping(value = "/releasedPerson/validateIdentityNum/{id}&{identityNum}")
    @PreAuthorize("hasAnyRole('ADMIN','NONPUBLIC_ALL','NONPUBLIC_SELECT')")
    public ResponseEntity verifyCreditCode(@PathVariable String id,@PathVariable String identityNum) {
        return new ResponseEntity(ReleasedPersonService.validateIdentityNum(id,identityNum), HttpStatus.OK);
    }
}