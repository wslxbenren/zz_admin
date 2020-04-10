package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.domain.DrugPerson;
import com.xyz.modules.biz.service.DrugPersonService;
import com.xyz.modules.biz.service.dto.DrugPersonQueryCriteria;
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
@Api(tags = "DrugPerson管理")
@RestController
@RequestMapping("api")
public class DrugPersonController {

    @Autowired
    private DrugPersonService DrugPersonService;

    @Log("查询DrugPerson")
    @ApiOperation(value = "查询DrugPerson")
    @GetMapping(value = "/DrugPerson")
    @PreAuthorize("hasAnyRole('ADMIN','DRUGPERSON_ALL','DRUGPERSON_SELECT')")
    public ResponseEntity getDrugPersons(DrugPersonQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(DrugPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增DrugPerson")
    @ApiOperation(value = "新增DrugPerson")
    @PostMapping(value = "/DrugPerson")
    @PreAuthorize("hasAnyRole('ADMIN','DRUGPERSON_ALL','DRUGPERSON_CREATE')")
    public ResponseEntity create(@Validated @RequestBody DrugPerson resources){
        return new ResponseEntity(DrugPersonService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改DrugPerson")
    @ApiOperation(value = "修改DrugPerson")
    @PutMapping(value = "/DrugPerson")
    @PreAuthorize("hasAnyRole('ADMIN','DRUGPERSON_ALL','DRUGPERSON_EDIT')")
    public ResponseEntity update(@Validated @RequestBody DrugPerson resources){
        DrugPersonService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除DrugPerson")
    @ApiOperation(value = "删除DrugPerson")
    @DeleteMapping(value = "/DrugPerson/{drugId}")
    @PreAuthorize("hasAnyRole('ADMIN','DRUGPERSON_ALL','DRUGPERSON_DELETE')")
    public ResponseEntity delete(@PathVariable String drugId){
        DrugPersonService.delete(drugId);
        return new ResponseEntity(HttpStatus.OK);
    }
}