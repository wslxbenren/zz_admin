package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.PsychosisPerson;
import com.xyz.modules.biz.service.PsychosisPersonService;
import com.xyz.modules.biz.service.dto.PsychosisPersonQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.DictService;
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
 * @author 刘鑫
 * @date 2020-04-10
 * 特殊人群有精神病肇事人员 CRUD
 */
@Api(tags = "PsychosisPerson管理")
@RestController
@RequestMapping("api")
public class PsychosisPersonController {

    @Autowired
    private PsychosisPersonService PsychosisPersonService;
    @Autowired
    private DictService dictService;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询PsychosisPerson")
    @ApiOperation(value = "查询PsychosisPerson")
    @GetMapping(value = "/PsychosisPerson")
    @PreAuthorize("hasAnyRole('ADMIN','PSYCHOSISPERSON_ALL','PSYCHOSISPERSON_SELECT')")
    public ResponseEntity getPsychosisPersons(PsychosisPersonQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(PsychosisPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }
    @Log("查询详情PsychosisPerson")
    @ApiOperation(value = "查询详情PsychosisPerson")
    @GetMapping(value = "/PsychosisPerson/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PSYCHOSISPERSON_ALL','PSYCHOSISPERSON_SELECT')")
    public ResponseEntity getById(@PathVariable String id){
        return new ResponseEntity(PsychosisPersonService.findById(id),HttpStatus.OK);
    }

    @Log("新增PsychosisPerson")
    @ApiOperation(value = "新增PsychosisPerson")
    @PostMapping(value = "/PsychosisPerson")
    @PreAuthorize("hasAnyRole('ADMIN','PSYCHOSISPERSON_ALL','PSYCHOSISPERSON_CREATE')")
    public ResponseEntity create(@Validated @RequestBody PsychosisPerson resources){
        return new ResponseEntity(PsychosisPersonService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改PsychosisPerson")
    @ApiOperation(value = "修改PsychosisPerson")
    @PutMapping(value = "/PsychosisPerson")
    @PreAuthorize("hasAnyRole('ADMIN','PSYCHOSISPERSON_ALL','PSYCHOSISPERSON_EDIT')")
    public ResponseEntity update(@Validated @RequestBody PsychosisPerson resources){
        PsychosisPersonService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除PsychosisPerson")
    @ApiOperation(value = "删除PsychosisPerson")
    @DeleteMapping(value = "/PsychosisPerson/{psychosisId}")
    @PreAuthorize("hasAnyRole('ADMIN','PSYCHOSISPERSON_ALL','PSYCHOSISPERSON_DELETE')")
    public ResponseEntity delete(@PathVariable String psychosisId){
        PsychosisPersonService.delete(psychosisId);
        return new ResponseEntity(HttpStatus.OK);
    }
}