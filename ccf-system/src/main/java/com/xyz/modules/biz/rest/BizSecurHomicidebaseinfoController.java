package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.BizSecurHomicidebaseinfo;
import com.xyz.modules.biz.service.BizSecurHomicidebaseinfoService;
import com.xyz.modules.biz.service.dto.BizSecurHomicidebaseinfoQueryCriteria;
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
 * 功能模块：社会治安管理/命案基本信息
 */
@Api(tags = "BizSecurHomicidebaseinfo管理")
@RestController
@RequestMapping("api")
public class BizSecurHomicidebaseinfoController {

    @Autowired
    private BizSecurHomicidebaseinfoService bizSecurHomicidebaseinfoService;

    @Log("查询BizSecurHomicidebaseinfo")
    @ApiOperation(value = "查询BizSecurHomicidebaseinfo")
    @GetMapping(value = "/bizSecurHomicidebaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURHOMICIDEBASEINFO_ALL','BIZSECURHOMICIDEBASEINFO_SELECT')")
    public ResponseEntity getBizSecurHomicidebaseinfos(BizSecurHomicidebaseinfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(bizSecurHomicidebaseinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("详情BizSecurHomicidebaseinfo")
    @GetMapping(value = "/bizSecurHomicidebaseinfo/details/{caseId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_SELECT')")
    public ResponseEntity getBizSecurHomicidebaseinfosDetails(@PathVariable String caseId){
        return new ResponseEntity( bizSecurHomicidebaseinfoService.findById(caseId),HttpStatus.OK);

    }

    @Log("新增BizSecurHomicidebaseinfo")
    @ApiOperation(value = "新增BizSecurHomicidebaseinfo")
    @PostMapping(value = "/bizSecurHomicidebaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURHOMICIDEBASEINFO_ALL','BIZSECURHOMICIDEBASEINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody BizSecurHomicidebaseinfo resources){
        return new ResponseEntity(bizSecurHomicidebaseinfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改BizSecurHomicidebaseinfo")
    @ApiOperation(value = "修改BizSecurHomicidebaseinfo")
    @PutMapping(value = "/bizSecurHomicidebaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURHOMICIDEBASEINFO_ALL','BIZSECURHOMICIDEBASEINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody BizSecurHomicidebaseinfo resources){
        bizSecurHomicidebaseinfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BizSecurHomicidebaseinfo")
    @ApiOperation(value = "删除BizSecurHomicidebaseinfo")
    @DeleteMapping(value = "/bizSecurHomicidebaseinfo/{caseId}")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSECURHOMICIDEBASEINFO_ALL','BIZSECURHOMICIDEBASEINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String caseId){
        bizSecurHomicidebaseinfoService.delete(caseId);
        return new ResponseEntity(HttpStatus.OK);
    }
}