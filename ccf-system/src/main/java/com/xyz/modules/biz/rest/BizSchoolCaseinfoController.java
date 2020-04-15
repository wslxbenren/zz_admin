package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.school.entity.BizSchoolCaseinfo;
import com.xyz.modules.biz.service.school.BizSchoolCaseinfoService;
import com.xyz.modules.biz.service.school.qo.BizSchoolCaseinfoQueryCriteria;
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
@Api(tags = "BizSchoolCaseinfo管理")
@RestController
@RequestMapping("api")
public class BizSchoolCaseinfoController {

    @Autowired
    private BizSchoolCaseinfoService bizSchoolCaseinfoService;

    @Log("查询BizSchoolCaseinfo")
    @ApiOperation(value = "查询BizSchoolCaseinfo")
    @GetMapping(value = "/bizSchoolCaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLCASEINFO_ALL','BIZSCHOOLCASEINFO_SELECT')")
    public ResponseEntity getBizSchoolCaseinfos(BizSchoolCaseinfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(bizSchoolCaseinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增BizSchoolCaseinfo")
    @ApiOperation(value = "新增BizSchoolCaseinfo")
    @PostMapping(value = "/bizSchoolCaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLCASEINFO_ALL','BIZSCHOOLCASEINFO_CREATE')")
    public ResponseEntity create(@Validated @RequestBody BizSchoolCaseinfo resources){
        return new ResponseEntity(bizSchoolCaseinfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改BizSchoolCaseinfo")
    @ApiOperation(value = "修改BizSchoolCaseinfo")
    @PutMapping(value = "/bizSchoolCaseinfo")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLCASEINFO_ALL','BIZSCHOOLCASEINFO_EDIT')")
    public ResponseEntity update(@Validated @RequestBody BizSchoolCaseinfo resources){
        bizSchoolCaseinfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BizSchoolCaseinfo")
    @ApiOperation(value = "删除BizSchoolCaseinfo")
    @DeleteMapping(value = "/bizSchoolCaseinfo/{caseId}")
    @PreAuthorize("hasAnyRole('ADMIN','BIZSCHOOLCASEINFO_ALL','BIZSCHOOLCASEINFO_DELETE')")
    public ResponseEntity delete(@PathVariable String caseId){
        bizSchoolCaseinfoService.delete(caseId);
        return new ResponseEntity(HttpStatus.OK);
    }
}