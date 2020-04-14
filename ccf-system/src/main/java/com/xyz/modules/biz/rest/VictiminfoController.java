package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Victiminfo;
import com.xyz.modules.biz.service.VictiminfoService;
import com.xyz.modules.biz.service.dto.VictiminfoQueryCriteria;
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
 * 功能模块：社会治安管理/命案受害人信息
 */
@Api(tags = "Victiminfo管理")
@RestController
@RequestMapping("api")
public class VictiminfoController {

    @Autowired
    private VictiminfoService VictiminfoService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询Victiminfo")
    @ApiOperation(value = "查询Victiminfo")
    @GetMapping(value = "/Victiminfo")
    @PreAuthorize("hasAnyRole('ADMIN','VICTIMINFO_ALL','VICTIMINFO_SELECT')")
    public ResponseEntity getVictiminfos(VictiminfoQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(VictiminfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("详情Victiminfo")
    @GetMapping(value = "/Victiminfo/details/{vicId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_SELECT')")
    public ResponseEntity getVictiminfosDetails(@PathVariable String vicId){
        return new ResponseEntity( VictiminfoService.findById(vicId),HttpStatus.OK);

    }

    @Log("新增Victiminfo")
    @ApiOperation(value = "新增Victiminfo")
    @PostMapping(value = "/Victiminfo")
    @PreAuthorize("hasAnyRole('ADMIN','VICTIMINFO_ALL','VICTIMINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Victiminfo resources){
        return new ResponseEntity(VictiminfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Victiminfo")
    @ApiOperation(value = "修改Victiminfo")
    @PutMapping(value = "/Victiminfo")
    @PreAuthorize("hasAnyRole('ADMIN','VICTIMINFO_ALL','VICTIMINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Victiminfo resources){
        VictiminfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Victiminfo")
    @ApiOperation(value = "删除Victiminfo")
    @DeleteMapping(value = "/Victiminfo/{vicId}")
    @PreAuthorize("hasAnyRole('ADMIN','VICTIMINFO_ALL','VICTIMINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String vicId){
        VictiminfoService.delete(vicId);
        return new ResponseEntity(HttpStatus.OK);
    }
}