package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.BizTeenagerBaseinfo;
import com.xyz.modules.biz.service.BizTeenagerBaseinfoService;
import com.xyz.modules.biz.service.dto.BizTeenagerBaseinfoQueryCriteria;
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
 * 功能模块：重点青少年/重点青少年基本信息
 */
@Api(tags = "BizTeenagerBaseinfo管理")
@RestController
@RequestMapping("api")
public class BizTeenagerBaseinfoController {

    @Autowired
    private BizTeenagerBaseinfoService bizTeenagerBaseinfoService;

    @Log("查询BizTeenagerBaseinfo")
    @ApiOperation(value = "查询BizTeenagerBaseinfo")
    @GetMapping(value = "/bizTeenagerBaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZTEENAGERBASEINFO_ALL','BIZTEENAGERBASEINFO_SELECT')")
    public ResponseEntity getBizTeenagerBaseinfos(BizTeenagerBaseinfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(bizTeenagerBaseinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("详情BizTeenagerBaseinfo")
    @GetMapping(value = "/bizTeenagerBaseinfo/details/{teenId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGELEADRESPONSINFO_ALL','MANAGELEADRESPONSINFO_SELECT')")
    public ResponseEntity getBizTeenagerBaseinfosDetails(@PathVariable String teenId){
        return new ResponseEntity( bizTeenagerBaseinfoService.findById(teenId),HttpStatus.OK);

    }

    @Log("新增BizTeenagerBaseinfo")
    @ApiOperation(value = "新增BizTeenagerBaseinfo")
    @PostMapping(value = "/bizTeenagerBaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZTEENAGERBASEINFO_ALL','BIZTEENAGERBASEINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody BizTeenagerBaseinfo resources){
        return new ResponseEntity(bizTeenagerBaseinfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改BizTeenagerBaseinfo")
    @ApiOperation(value = "修改BizTeenagerBaseinfo")
    @PutMapping(value = "/bizTeenagerBaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZTEENAGERBASEINFO_ALL','BIZTEENAGERBASEINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody BizTeenagerBaseinfo resources){
        bizTeenagerBaseinfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BizTeenagerBaseinfo")
    @ApiOperation(value = "删除BizTeenagerBaseinfo")
    @DeleteMapping(value = "/bizTeenagerBaseinfo/{teenId}")
    @PreAuthorize("hasAnyRole('ADMIN','BIZTEENAGERBASEINFO_ALL','BIZTEENAGERBASEINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String teenId){
        bizTeenagerBaseinfoService.delete(teenId);
        return new ResponseEntity(HttpStatus.OK);
    }
}