package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.DrugPerson;
import com.xyz.modules.biz.service.DrugPersonService;
import com.xyz.modules.biz.service.dto.DrugPersonQueryCriteria;
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
 * 特殊人群吸毒人员 CRUD
 */
@Api(tags = "DrugPerson管理")
@RestController
@RequestMapping("api")
public class DrugPersonController {

    @Autowired
    private DrugPersonService DrugPersonService;

    @Autowired
    private DictService dictService;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private DeptService deptService;

    @Log("查询DrugPerson")
    @ApiOperation(value = "查询DrugPerson")
    @GetMapping(value = "/DrugPerson")
    @PreAuthorize("hasAnyRole('ADMIN','DRUGPERSON_ALL','DRUGPERSON_SELECT')")
    public ResponseEntity getDrugPersons(DrugPersonQueryCriteria criteria, Pageable pageable){
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
        List<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
        criteria.setUnitCode(deptCodes);
        return new ResponseEntity(DrugPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }
    @Log("查询详情DrugPerson")
    @ApiOperation(value = "查询详情DrugPerson")
    @GetMapping(value = "/DrugPerson/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DRUGPERSON_ALL','DRUGPERSON_SELECT')")
    public ResponseEntity getById(@PathVariable String id){
        if (StringUtils.isBlank(id)){
            throw new BadRequestException("主键ID不能为空");
        }
        return new ResponseEntity(DrugPersonService.findById(id),HttpStatus.OK);
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
        if (StringUtils.isBlank(resources.getDrugId())){
            throw new BadRequestException("主键ID不能为空");
        }
        DrugPersonService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除DrugPerson")
    @ApiOperation(value = "删除DrugPerson")
    @DeleteMapping(value = "/DrugPerson/{drugId}")
    @PreAuthorize("hasAnyRole('ADMIN','DRUGPERSON_ALL','DRUGPERSON_DELETE')")
    public ResponseEntity delete(@PathVariable String drugId){
        if (StringUtils.isBlank(drugId)){
            throw new BadRequestException("主键ID不能为空");
        }
        DrugPersonService.delete(drugId);
        return new ResponseEntity(HttpStatus.OK);
    }
}