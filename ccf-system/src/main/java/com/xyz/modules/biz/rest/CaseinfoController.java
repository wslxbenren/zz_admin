package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.route.entity.Caseinfo;
import com.xyz.modules.biz.service.route.CaseinfoService;
import com.xyz.modules.biz.service.route.qo.CaseinfoQueryCriteria;
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
 * 功能模块：护路护线/涉线、路案事件信息管理
 */
@Api(tags = "Caseinfo管理")
@RestController
@RequestMapping("api")
public class CaseinfoController {

    @Autowired
    private CaseinfoService CaseinfoService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询Caseinfo")
    @ApiOperation(value = "查询Caseinfo")
    @GetMapping(value = "/Caseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CASEINFO_ALL','CASEINFO_SELECT')")
    public ResponseEntity getCaseinfos(CaseinfoQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(CaseinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("详情Caseinfo")
    @GetMapping(value = "/Caseinfo/details/{caseId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_SELECT')")
    public ResponseEntity getCaseinfosDetails(@PathVariable String caseId){
        return new ResponseEntity( CaseinfoService.findById(caseId),HttpStatus.OK);

    }

    @Log("新增Caseinfo")
    @ApiOperation(value = "新增Caseinfo")
    @PostMapping(value = "/Caseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CASEINFO_ALL','CASEINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Caseinfo resources){
        return new ResponseEntity(CaseinfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Caseinfo")
    @ApiOperation(value = "修改Caseinfo")
    @PutMapping(value = "/Caseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','CASEINFO_ALL','CASEINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Caseinfo resources){
        CaseinfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Caseinfo")
    @ApiOperation(value = "删除Caseinfo")
    @DeleteMapping(value = "/Caseinfo/{caseId}")
    @PreAuthorize("hasAnyRole('ADMIN','CASEINFO_ALL','CASEINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String caseId){
        CaseinfoService.delete(caseId);
        return new ResponseEntity(HttpStatus.OK);
    }
}