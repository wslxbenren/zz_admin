package com.xyz.modules.system.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.system.domain.T3commandcenterIndustry;
import com.xyz.modules.system.service.T3commandcenterIndustryService;
import com.xyz.modules.system.service.dto.T3commandcenterIndustryQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author dadovicn
* @date 2020-02-02
*/
@Api(tags = "市县两级指挥中心接报-发热、咳嗽等疑似新型冠状病毒感染引起的肺炎-警情统计表)")
@RestController
@RequestMapping("api")
public class T3commandcenterIndustryController {

    @Autowired
    private T3commandcenterIndustryService t3commandcenterIndustryService;

    @Log("查询T3commandcenterIndustry")
    @ApiOperation(value = "查询T3commandcenterIndustry")
    @GetMapping(value = "/t3commandcenterIndustry")
    @PreAuthorize("hasAnyRole('ADMIN','T3COMMANDCENTERINDUSTRY_ALL','T3COMMANDCENTERINDUSTRY_SELECT')")
    public ResponseEntity getT3commandcenterIndustrys(T3commandcenterIndustryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(t3commandcenterIndustryService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增T3commandcenterIndustry")
    @ApiOperation(value = "新增T3commandcenterIndustry")
    @PostMapping(value = "/t3commandcenterIndustry")
    @PreAuthorize("hasAnyRole('ADMIN','T3COMMANDCENTERINDUSTRY_ALL','T3COMMANDCENTERINDUSTRY_CREATE')")
    public ResponseEntity create(@Validated @RequestBody T3commandcenterIndustry resources){
        return new ResponseEntity(t3commandcenterIndustryService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改T3commandcenterIndustry")
    @ApiOperation(value = "修改T3commandcenterIndustry")
    @PutMapping(value = "/t3commandcenterIndustry")
    @PreAuthorize("hasAnyRole('ADMIN','T3COMMANDCENTERINDUSTRY_ALL','T3COMMANDCENTERINDUSTRY_EDIT')")
    public ResponseEntity update(@Validated @RequestBody T3commandcenterIndustry resources){
        t3commandcenterIndustryService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除T3commandcenterIndustry")
    @ApiOperation(value = "删除T3commandcenterIndustry")
    @DeleteMapping(value = "/t3commandcenterIndustry/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','T3COMMANDCENTERINDUSTRY_ALL','T3COMMANDCENTERINDUSTRY_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        t3commandcenterIndustryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}