package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.Registpeople;
import com.xyz.modules.biz.service.RegistpeopleService;
import com.xyz.modules.biz.service.dto.RegistpeopleQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.StringUtils;
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
* @author lx
* @date 2020-04-08
 * 实有人口/户籍人员信息 CRUD，机构权限向下查询。
*/
@Api(tags = "Registpeople管理")
@RestController
@RequestMapping("api")
public class RegistpeopleController {

    @Autowired
    private RegistpeopleService RegistpeopleService;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;
    @Log("查询Registpeople")
    @ApiOperation(value = "查询Registpeople")
    @GetMapping(value = "/Registpeople")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_SELECT')")
    public ResponseEntity getRegistpeoples(RegistpeopleQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(RegistpeopleService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Registpeople")
    @ApiOperation(value = "新增Registpeople")
    @PostMapping(value = "/Registpeople")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Registpeople resources){
        return new ResponseEntity(RegistpeopleService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Registpeople")
    @ApiOperation(value = "修改Registpeople")
    @PutMapping(value = "/Registpeople")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Registpeople resources){
        if (StringUtils.isBlank(resources.getRegisId())){
            throw new BadRequestException("主键ID不能为空");
        }
        RegistpeopleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Registpeople")
    @ApiOperation(value = "删除Registpeople")
    @DeleteMapping(value = "/Registpeople/{regisId}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_DELETE')")
    public ResponseEntity delete(@PathVariable String regisId){
        if (StringUtils.isBlank(regisId)){
            throw new BadRequestException("主键ID不能为空");
        }
        RegistpeopleService.delete(regisId);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @Log("查看Registpeople")
//    @ApiOperation(value = "查看Registpeople")
//    @DeleteMapping(value = "/Registpeople/{regisId}")
//    @PreAuthorize("hasAnyRole('ADMIN','REGISTPEOPLE_ALL','REGISTPEOPLE_DELETE')")
//    public ResponseEntity getById(@PathVariable String regisId){
//        return new ResponseEntity(RegistpeopleService.findById(regisId),HttpStatus.OK);
//    }
}