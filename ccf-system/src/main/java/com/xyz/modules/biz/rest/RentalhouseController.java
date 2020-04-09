package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.Rentalhouse;
import com.xyz.modules.biz.service.RentalhouseService;
import com.xyz.modules.biz.service.dto.RentalhouseQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author lx
* @date 2020-04-09
*/
@Api(tags = "Rentalhouse管理")
@RestController
@RequestMapping("api")
public class RentalhouseController {

    @Autowired
    private RentalhouseService RentalhouseService;

    @Log("查询Rentalhouse")
    @ApiOperation(value = "查询Rentalhouse")
    @GetMapping(value = "/Rentalhouse")
    @PreAuthorize("hasAnyRole('ADMIN','RENTALHOUSE_ALL','RENTALHOUSE_SELECT')")
    public ResponseEntity getRentalhouses(RentalhouseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(RentalhouseService.queryAll(criteria,pageable),HttpStatus.OK);
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
        RentalhouseService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Rentalhouse")
    @ApiOperation(value = "删除Rentalhouse")
    @DeleteMapping(value = "/Rentalhouse/{rentId}")
    @PreAuthorize("hasAnyRole('ADMIN','RENTALHOUSE_ALL','RENTALHOUSE_DELETE')")
    public ResponseEntity delete(@PathVariable String rentId){
        RentalhouseService.delete(rentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}