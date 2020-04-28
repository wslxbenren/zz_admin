package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.organ.entity.Nonpublic;
import com.xyz.modules.biz.service.organ.NonpublicService;
import com.xyz.modules.biz.service.organ.qo.NonpublicQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Api(tags = "Nonpublic管理--社会组织--非公有制经济组织信息")
@RestController
@RequestMapping("api")
public class NonpublicController {

    @Autowired
    private NonpublicService NonpublicService;

    @Log("查询Nonpublic")
    @ApiOperation(value = "查询Nonpublic")
    @GetMapping(value = "/Nonpublic")
    @PreAuthorize("hasAnyRole('ADMIN','NONPUBLIC_ALL','NONPUBLIC_SELECT')")
    public ResponseEntity getNonpublics(NonpublicQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(NonpublicService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Nonpublic")
    @ApiOperation(value = "新增Nonpublic")
    @PostMapping(value = "/Nonpublic")
    @PreAuthorize("hasAnyRole('ADMIN','NONPUBLIC_ALL','NONPUBLIC_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Nonpublic resources){
        return new ResponseEntity(NonpublicService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Nonpublic")
    @ApiOperation(value = "修改Nonpublic")
    @PutMapping(value = "/Nonpublic")
    @PreAuthorize("hasAnyRole('ADMIN','NONPUBLIC_ALL','NONPUBLIC_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Nonpublic resources){
        NonpublicService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Nonpublic")
    @ApiOperation(value = "删除Nonpublic")
    @DeleteMapping(value = "/Nonpublic/{nonId}")
    @PreAuthorize("hasAnyRole('ADMIN','NONPUBLIC_ALL','NONPUBLIC_DELETE')")
    public ResponseEntity delete(@PathVariable String nonId){
        NonpublicService.delete(nonId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("查询Nonpublic")
    @ApiOperation(value = "验证企业统一社会信用代码")
    @GetMapping(value = "/Nonpublic/verifyCreditCode/{creditCode}")
    @PreAuthorize("hasAnyRole('ADMIN','NONPUBLIC_ALL','NONPUBLIC_SELECT')")
    public ResponseEntity verifyCreditCode(@PathVariable String creditCode){
          return new ResponseEntity(NonpublicService.verifyCreditCode(creditCode),HttpStatus.OK);
    }
}