package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.AidsPerson;
import com.xyz.modules.biz.service.AidsPersonService;
import com.xyz.modules.biz.service.dto.AidsPersonQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.DictService;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

/**
 * @author 刘鑫
 * @date 2020-04-10
 * 艾滋病危险人员信息 CRUD
 */
@Api(tags = "AidsPerson管理")
@RestController
@RequestMapping("api")
public class AidsPersonController {

    @Autowired
    private AidsPersonService AidsPersonService;
    @Autowired
    private DictService dictService;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询AidsPerson")
    @ApiOperation(value = "查询AidsPerson")
    @GetMapping(value = "/AidsPerson")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_SELECT')")
    public ResponseEntity getAidsPersons(AidsPersonQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(AidsPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }
    @Log("查询详情AidsPerson")
    @ApiOperation(value = "查询详情AidsPerson")
    @GetMapping(value = "/AidsPerson/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_SELECT')")
    public ResponseEntity getById(@PathVariable String id){
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        return new ResponseEntity(AidsPersonService.findById(id),HttpStatus.OK);
    }

    @Log("新增AidsPerson")
    @ApiOperation(value = "新增AidsPerson")
    @PostMapping(value = "/AidsPerson")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_CREATE')")
    public ResponseEntity create(@Validated @RequestBody AidsPerson resources){
        return new ResponseEntity(AidsPersonService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改AidsPerson")
    @ApiOperation(value = "修改AidsPerson")
    @PutMapping(value = "/AidsPerson")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_EDIT')")
    public ResponseEntity update(@Validated @RequestBody AidsPerson resources){
        if (StringUtils.isBlank(resources.getAidsId())){
            throw new BadRequestException("主键ID不能为空");
        }
        AidsPersonService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AidsPerson")
    @ApiOperation(value = "删除AidsPerson")
    @DeleteMapping(value = "/AidsPerson/{aidsId}")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_DELETE')")
    public ResponseEntity delete(@PathVariable String aidsId){
        if (StringUtils.isBlank(aidsId)){
            throw new BadRequestException("主键ID不能为空");
        }
        AidsPersonService.delete(aidsId);
        return new ResponseEntity(HttpStatus.OK);
    }
}