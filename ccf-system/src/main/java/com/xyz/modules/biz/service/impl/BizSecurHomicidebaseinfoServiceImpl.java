package com.xyz.modules.biz.service.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.BizSecurHomicidebaseinfo;
import com.xyz.modules.biz.service.dto.BizTeenagerBaseinfoDTO;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.repository.BizSecurHomicidebaseinfoRepository;
import com.xyz.modules.biz.service.BizSecurHomicidebaseinfoService;
import com.xyz.modules.biz.service.dto.BizSecurHomicidebaseinfoDTO;
import com.xyz.modules.biz.service.dto.BizSecurHomicidebaseinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.BizSecurHomicidebaseinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BizSecurHomicidebaseinfoServiceImpl implements BizSecurHomicidebaseinfoService {

    @Autowired
    private BizSecurHomicidebaseinfoRepository bizSecurHomicidebaseinfoRepository;

    @Autowired
    private BizSecurHomicidebaseinfoMapper bizSecurHomicidebaseinfoMapper;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public Object queryAll(BizSecurHomicidebaseinfoQueryCriteria criteria, Pageable pageable){
        log.info("列表查询社会治安管理/命案基本信息--开始");
        Page<BizSecurHomicidebaseinfo> page = bizSecurHomicidebaseinfoRepository.findAll(audit.genSpecification(criteria),pageable);
        List<BizSecurHomicidebaseinfoDTO> bizSecurHomicidebaseinfoDTOList = bizSecurHomicidebaseinfoMapper.toDto(page.getContent());
        for (BizSecurHomicidebaseinfoDTO mid: bizSecurHomicidebaseinfoDTOList) {
            String dd = deptRepository.findNameByCode(mid.getUnitCode());
            mid.setUnitCodeStr(dd); // 单位编码,所属单位
        }
        Map map = new HashMap();
        map.put("content", bizSecurHomicidebaseinfoDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(BizSecurHomicidebaseinfoQueryCriteria criteria){
        return bizSecurHomicidebaseinfoMapper.toDto(bizSecurHomicidebaseinfoRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public BizSecurHomicidebaseinfoDTO findById(String caseId) {
        log.info("详情社会治安管理/命案基本信息--开始");
        if (StringUtils.isBlank(caseId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<BizSecurHomicidebaseinfo> bizSecurHomicidebaseinfo = bizSecurHomicidebaseinfoRepository.findById(caseId);
        ValidationUtil.isNull(bizSecurHomicidebaseinfo,"BizSecurHomicidebaseinfo","caseId",caseId);
        return bizSecurHomicidebaseinfoMapper.toDto(bizSecurHomicidebaseinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizSecurHomicidebaseinfoDTO create(BizSecurHomicidebaseinfo resources) {
        log.info("新增社会治安管理/命案基本信息--开始");
        resources.setCaseId(IdUtil.simpleUUID()); 
        if(bizSecurHomicidebaseinfoRepository.findByCaseCode(resources.getCaseCode()) != null){
            throw new EntityExistException(BizSecurHomicidebaseinfo.class,"case_code",resources.getCaseCode());
        }
        return bizSecurHomicidebaseinfoMapper.toDto(bizSecurHomicidebaseinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizSecurHomicidebaseinfo resources) {
        log.info("修改社会治安管理/命案基本信息--开始");
        if (StringUtils.isBlank(resources.getCaseId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<BizSecurHomicidebaseinfo> optionalBizSecurHomicidebaseinfo = bizSecurHomicidebaseinfoRepository.findById(resources.getCaseId());
        ValidationUtil.isNull( optionalBizSecurHomicidebaseinfo,"BizSecurHomicidebaseinfo","id",resources.getCaseId());
        BizSecurHomicidebaseinfo bizSecurHomicidebaseinfo = optionalBizSecurHomicidebaseinfo.get();
        BizSecurHomicidebaseinfo bizSecurHomicidebaseinfo1  = bizSecurHomicidebaseinfoRepository.findByCaseCode(resources.getCaseCode());
        if(bizSecurHomicidebaseinfo1 != null && !bizSecurHomicidebaseinfo1.getCaseId().equals(bizSecurHomicidebaseinfo.getCaseId())){
            throw new EntityExistException(BizSecurHomicidebaseinfo.class,"case_code",resources.getCaseCode());
        }
        bizSecurHomicidebaseinfo.copy(resources);
        bizSecurHomicidebaseinfoRepository.save(bizSecurHomicidebaseinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String caseId) {
        log.info("删除社会治安管理/命案基本信息--开始");
        if (StringUtils.isBlank(caseId)){
            throw new BadRequestException("主键ID不能为空");
        }
        bizSecurHomicidebaseinfoRepository.deleteById(caseId);
    }
}