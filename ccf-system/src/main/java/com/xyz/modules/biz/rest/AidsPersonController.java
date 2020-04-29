package com.xyz.modules.biz.rest;

import com.xyz.aop.log.Log;
import com.xyz.modules.biz.service.special.entity.AidsPerson;
import com.xyz.modules.biz.service.special.AidsPersonService;
import com.xyz.modules.biz.service.special.qo.AidsPersonQueryCriteria;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.DictService;
import com.xyz.utils.SecurityUtils;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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
        return new ResponseEntity(AidsPersonService.queryAll(criteria,pageable),HttpStatus.OK);
    }
    @Log("查询详情AidsPerson")
    @ApiOperation(value = "查询详情AidsPerson")
    @GetMapping(value = "/AidsPerson/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_SELECT')")
    public ResponseEntity getById(@PathVariable String id){
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
        AidsPersonService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AidsPerson")
    @ApiOperation(value = "删除AidsPerson")
    @DeleteMapping(value = "/AidsPerson/{aidsId}")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_DELETE')")
    public ResponseEntity delete(@PathVariable  String aidsId){
        AidsPersonService.delete(aidsId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("导出AidsPerson")
    @ApiOperation(value = "导出AidsPerson")
    @GetMapping(value = "/AidsPerson/export")
    @PreAuthorize("hasAnyRole('ADMIN','AIDSPERSON_ALL','AIDSPERSON_DELETE')")
    public ResponseEntity export(HttpServletResponse response , AidsPersonQueryCriteria criteria) throws IOException {
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        String deptId = u.getDeptDto().getId();
//        Set<String> deptCodes = deptService.getDownGradeDeptCodes(deptId);
        criteria.setCreator(u.getId());
//        criteria.setUnitCode(deptCodes);
        AidsPersonService.export(response,criteria);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("查询AidsPerson")
    @ApiOperation(value = "验证身份证号码存在")
    @GetMapping(value = "/AidsPerson/validateIdentityNum/{id}&{identityNum}")
    @PreAuthorize("hasAnyRole('ADMIN','NONPUBLIC_ALL','NONPUBLIC_SELECT')")
    public ResponseEntity verifyCreditCode(@PathVariable String id,@PathVariable String identityNum) {
        return new ResponseEntity(AidsPersonService.validateIdentityNum(id,identityNum), HttpStatus.OK);
    }

}