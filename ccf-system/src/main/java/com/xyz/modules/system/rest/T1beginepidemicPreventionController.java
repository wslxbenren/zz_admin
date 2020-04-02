package com.xyz.modules.system.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.system.domain.T1beginepidemicPrevention;
import com.xyz.modules.system.service.T1beginepidemicPreventionService;
import com.xyz.modules.system.service.dto.T1beginepidemicPreventionQueryCriteria;
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
@Api(tags = "开展疫情防控工作情况日统计表")
@RestController
@RequestMapping("api")
public class T1beginepidemicPreventionController {

    @Autowired
    private T1beginepidemicPreventionService t1beginepidemicPreventionService;

    @Log("查询T1beginepidemicPrevention")
    @ApiOperation(value = "查询T1beginepidemicPrevention")
    @GetMapping(value = "/t1beginepidemicPrevention")
    @PreAuthorize("hasAnyRole('ADMIN','T1BEGINEPIDEMICPREVENTION_ALL','T1BEGINEPIDEMICPREVENTION_SELECT')")
    public ResponseEntity getT1beginepidemicPreventions(T1beginepidemicPreventionQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(t1beginepidemicPreventionService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增T1beginepidemicPrevention")
    @ApiOperation(value = "新增T1beginepidemicPrevention")
    @PostMapping(value = "/t1beginepidemicPrevention")
    @PreAuthorize("hasAnyRole('ADMIN','T1BEGINEPIDEMICPREVENTION_ALL','T1BEGINEPIDEMICPREVENTION_CREATE')")
    public ResponseEntity create(@Validated @RequestBody T1beginepidemicPrevention resources){
        return new ResponseEntity(t1beginepidemicPreventionService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改T1beginepidemicPrevention")
    @ApiOperation(value = "修改T1beginepidemicPrevention")
    @PutMapping(value = "/t1beginepidemicPrevention")
    @PreAuthorize("hasAnyRole('ADMIN','T1BEGINEPIDEMICPREVENTION_ALL','T1BEGINEPIDEMICPREVENTION_EDIT')")
    public ResponseEntity update(@Validated @RequestBody T1beginepidemicPrevention resources){
        t1beginepidemicPreventionService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除T1beginepidemicPrevention")
    @ApiOperation(value = "删除T1beginepidemicPrevention")
    @DeleteMapping(value = "/t1beginepidemicPrevention/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','T1BEGINEPIDEMICPREVENTION_ALL','T1BEGINEPIDEMICPREVENTION_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        t1beginepidemicPreventionService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
