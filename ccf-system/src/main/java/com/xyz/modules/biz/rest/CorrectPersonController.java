package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.special.entity.CorrectPerson;
import com.xyz.modules.biz.service.special.CorrectPersonService;
import com.xyz.modules.biz.service.special.qo.CorrectPersonQueryCriteria;
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
 * 特殊人群社区矫正人群 CURD
 */
@Api(tags = "CorrectPerson管理")
@RestController
@RequestMapping("api")
public class CorrectPersonController {

    @Autowired
    private CorrectPersonService CorrectPersonService;
    @Autowired
    private DictService dictService;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询CorrectPerson")
    @ApiOperation(value = "查询CorrectPerson")
    @GetMapping(value = "/CorrectPerson")
    @PreAuthorize("hasAnyRole('ADMIN','CORRECTPERSON_ALL','CORRECTPERSON_SELECT')")
    public ResponseEntity getCorrectPersons(CorrectPersonQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(CorrectPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }
    @Log("查询详情CorrectPerson")
    @ApiOperation(value = "查询详情CorrectPerson")
    @GetMapping(value = "/CorrectPerson/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CORRECTPERSON_ALL','CORRECTPERSON_SELECT')")
    public ResponseEntity getById(@PathVariable String id){
        return new ResponseEntity(CorrectPersonService.findById(id),HttpStatus.OK);
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

    @Log("查询CorrectPerson")
    @ApiOperation(value = "验证身份证号码存在")
    @GetMapping(value = "/correctPerson/validateIdentityNum/{id}&{identityNum}")
    @PreAuthorize("hasAnyRole('ADMIN','NONPUBLIC_ALL','NONPUBLIC_SELECT')")
    public ResponseEntity verifyCreditCode(@PathVariable String id,@PathVariable String identityNum) {
        return new ResponseEntity(CorrectPersonService.validateIdentityNum(id,identityNum), HttpStatus.OK);
    }
}