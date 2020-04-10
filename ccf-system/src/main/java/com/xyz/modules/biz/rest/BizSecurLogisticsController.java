package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.BizSecurLogistics;
import com.xyz.modules.biz.service.BizSecurLogisticsService;
import com.xyz.modules.biz.service.dto.BizSecurLogisticsQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Api(tags = "BizSecurLogistics管理")
@RestController
@RequestMapping("api")
public class BizSecurLogisticsController {

    @Autowired
    private BizSecurLogisticsService bizSecurLogisticsService;

    @Log("查询BizSecurLogistics")
    @ApiOperation(value = "查询BizSecurLogistics")
    @GetMapping(value = "/bizSecurLogistics")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURLOGISTICS_ALL','BIZSECURLOGISTICS_SELECT')")
    public ResponseEntity getBizSecurLogisticss(BizSecurLogisticsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(bizSecurLogisticsService.queryAll(criteria,pageable),HttpStatus.OK);
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