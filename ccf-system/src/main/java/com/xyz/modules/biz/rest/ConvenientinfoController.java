package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.route.entity.Convenientinfo;
import com.xyz.modules.biz.service.route.ConvenientinfoService;
import com.xyz.modules.biz.service.route.qo.ConvenientinfoQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
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
 * @author 邢家华
 * @date 2020-04-10
 * 功能模块：护路护线/护路护线基本信息
 */
@Api(tags = "Convenientinfo管理")
@RestController
@RequestMapping("api")
public class ConvenientinfoController {

    @Autowired
    private ConvenientinfoService ConvenientinfoService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询Convenientinfo")
    @ApiOperation(value = "查询Convenientinfo")
    @GetMapping(value = "/Convenientinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CONVENIENTINFO_ALL','CONVENIENTINFO_SELECT')")
    public ResponseEntity getConvenientinfos(ConvenientinfoQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(ConvenientinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("详情Convenientinfo")
    @GetMapping(value = "/Convenientinfo/details/{conId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_SELECT')")
    public ResponseEntity getConvenientinfosDetails(@PathVariable String conId){
        return new ResponseEntity( ConvenientinfoService.findById(conId),HttpStatus.OK);

    }

    @Log("新增Convenientinfo")
    @ApiOperation(value = "新增Convenientinfo")
    @PostMapping(value = "/Convenientinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CONVENIENTINFO_ALL','CONVENIENTINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Convenientinfo resources){
        return new ResponseEntity(ConvenientinfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Convenientinfo")
    @ApiOperation(value = "修改Convenientinfo")
    @PutMapping(value = "/Convenientinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CONVENIENTINFO_ALL','CONVENIENTINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Convenientinfo resources){
        ConvenientinfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Convenientinfo")
    @ApiOperation(value = "删除Convenientinfo")
    @DeleteMapping(value = "/Convenientinfo/{conId}")
    @PreAuthorize("hasAnyRole('ADMIN','CONVENIENTINFO_ALL','CONVENIENTINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String conId){
        ConvenientinfoService.delete(conId);
        return new ResponseEntity(HttpStatus.OK);
    }
}