package com.xyz.modules.system.rest;


import com.xyz.aop.log.Log;
import com.xyz.modules.system.domain.SuspectedPersonnelScreening;
import com.xyz.modules.system.domain.vo.SuspectedPersonnelScreeningVo;
import com.xyz.modules.system.service.SuspectedPersonnelScreeningService;
import com.xyz.modules.system.service.dto.SuspectedPersonnelScreeningQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author dadovicn
* @date 2020-02-07
*/
@Api(tags = "SuspectedPersonnelScreening管理")
@RestController
@RequestMapping("api/m")
public class SuspectedPersonnelScreeningMobileController {

    @Autowired
    private SuspectedPersonnelScreeningService suspectedPersonnelScreeningService;

    @Log("查询SuspectedPersonnelScreening")
    @ApiOperation(value = "查询SuspectedPersonnelScreening")
    @GetMapping(value = "/suspectedPersonnelScreening")
    public ResponseEntity getSuspectedPersonnelScreenings(SuspectedPersonnelScreeningQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(suspectedPersonnelScreeningService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增SuspectedPersonnelScreening")
    @ApiOperation(value = "新增SuspectedPersonnelScreening")
    @PostMapping(value = "/suspectedPersonnelScreening")
    public ResponseEntity create(@Validated @RequestBody SuspectedPersonnelScreeningVo resources) throws IllegalAccessException {
        return new ResponseEntity(suspectedPersonnelScreeningService.create(resources.copy()),HttpStatus.CREATED);
    }

    @Log("修改SuspectedPersonnelScreening")
    @ApiOperation(value = "修改SuspectedPersonnelScreening")
    @PutMapping(value = "/suspectedPersonnelScreening")
    public ResponseEntity update(@Validated @RequestBody SuspectedPersonnelScreening resources){
        suspectedPersonnelScreeningService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除SuspectedPersonnelScreening")
    @ApiOperation(value = "删除SuspectedPersonnelScreening")
    @DeleteMapping(value = "/suspectedPersonnelScreening/{id}")
    public ResponseEntity delete(@PathVariable String id){
        suspectedPersonnelScreeningService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}