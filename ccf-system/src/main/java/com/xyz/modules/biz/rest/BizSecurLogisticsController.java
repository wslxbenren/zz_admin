package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.secur.entity.BizSecurLogistics;
import com.xyz.modules.biz.service.secur.BizSecurLogisticsService;
import com.xyz.modules.biz.service.secur.dto.BizSecurLogisticsQueryCriteria;
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
 * 功能模块：社会治安管理/寄递物流安全信息
 */
@Api(tags = "BizSecurLogistics管理")
@RestController
@RequestMapping("api")
public class BizSecurLogisticsController {

    @Autowired
    private BizSecurLogisticsService bizSecurLogisticsService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询BizSecurLogistics")
    @ApiOperation(value = "查询BizSecurLogistics")
    @GetMapping(value = "/bizSecurLogistics")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURLOGISTICS_ALL','BIZSECURLOGISTICS_SELECT')")
    public ResponseEntity getBizSecurLogisticss(BizSecurLogisticsQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(bizSecurLogisticsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("详情BizSecurLogistics")
    @GetMapping(value = "/bizSecurLogistics/details/{logisId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_SELECT')")
    public ResponseEntity getBizSecurLogisticssDetails(@PathVariable String logisId){
        return new ResponseEntity( bizSecurLogisticsService.findById(logisId),HttpStatus.OK);

    }

    @Log("新增BizSecurLogistics")
    @ApiOperation(value = "新增BizSecurLogistics")
    @PostMapping(value = "/bizSecurLogistics")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURLOGISTICS_ALL','BIZSECURLOGISTICS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody BizSecurLogistics resources){
        return new ResponseEntity(bizSecurLogisticsService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改BizSecurLogistics")
    @ApiOperation(value = "修改BizSecurLogistics")
    @PutMapping(value = "/bizSecurLogistics")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURLOGISTICS_ALL','BIZSECURLOGISTICS_EDIT')")
    public ResponseEntity update(@Validated @RequestBody BizSecurLogistics resources){
        bizSecurLogisticsService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BizSecurLogistics")
    @ApiOperation(value = "删除BizSecurLogistics")
    @DeleteMapping(value = "/bizSecurLogistics/{logisId}")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURLOGISTICS_ALL','BIZSECURLOGISTICS_DELETE')")
    public ResponseEntity delete(@PathVariable String logisId){
        bizSecurLogisticsService.delete(logisId);
        return new ResponseEntity(HttpStatus.OK);
    }
}