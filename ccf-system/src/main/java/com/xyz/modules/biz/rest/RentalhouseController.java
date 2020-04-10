package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.Rentalhouse;
import com.xyz.modules.biz.service.RentalhouseService;
import com.xyz.modules.biz.service.dto.RentalhouseQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.StringUtils;
import org.hibernate.annotations.DynamicUpdate;
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
* @date 2020-04-09
 * 实有人口/出租房信息
*/
@Api(tags = "Rentalhouse管理")
@RestController
@RequestMapping("api")
@DynamicUpdate
public class RentalhouseController {

    @Autowired
    private RentalhouseService RentalhouseService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;
    @Log("查询Rentalhouse")
    @ApiOperation(value = "查询Rentalhouse")
    @GetMapping(value = "/Rentalhouse")
    @PreAuthorize("hasAnyRole('ADMIN','RENTALHOUSE_ALL','RENTALHOUSE_SELECT')")
    public ResponseEntity getRentalhouses(RentalhouseQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(RentalhouseService.queryAll(criteria,pageable),HttpStatus.OK);
    }
    @Log("查询单个Rentalhouse")
    @ApiOperation(value = "查询单个Rentalhouse")
    @GetMapping(value = "/Rentalhouse/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','RENTALHOUSE_ALL','RENTALHOUSE_SELECT')")
    public ResponseEntity getById(@PathVariable String id){
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        return new ResponseEntity(RentalhouseService.findById(id),HttpStatus.OK);
    }

    @Log("新增Rentalhouse")
    @ApiOperation(value = "新增Rentalhouse")
    @PostMapping(value = "/Rentalhouse")
    @PreAuthorize("hasAnyRole('ADMIN','RENTALHOUSE_ALL','RENTALHOUSE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Rentalhouse resources){
        return new ResponseEntity(RentalhouseService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Rentalhouse")
    @ApiOperation(value = "修改Rentalhouse")
    @PutMapping(value = "/Rentalhouse")
    @PreAuthorize("hasAnyRole('ADMIN','RENTALHOUSE_ALL','RENTALHOUSE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Rentalhouse resources){
        if (StringUtils.isBlank(resources.getRentId())){
            throw new BadRequestException("主键ID不能为空");
        }
        RentalhouseService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Rentalhouse")
    @ApiOperation(value = "删除Rentalhouse")
    @DeleteMapping(value = "/Rentalhouse/{rentId}")
    @PreAuthorize("hasAnyRole('ADMIN','RENTALHOUSE_ALL','RENTALHOUSE_DELETE')")
    public ResponseEntity delete(@PathVariable String rentId){
        if (StringUtils.isBlank(rentId)){
            throw new BadRequestException("主键ID不能为空");
        }
        RentalhouseService.delete(rentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}