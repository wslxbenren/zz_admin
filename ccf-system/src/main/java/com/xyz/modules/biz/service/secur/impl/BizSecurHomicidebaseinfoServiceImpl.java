package com.xyz.modules.biz.service.secur.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.service.secur.entity.BizSecurHomicidebaseinfo;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.service.secur.repo.BizSecurHomicidebaseinfoRepository;
import com.xyz.modules.biz.service.secur.BizSecurHomicidebaseinfoService;
import com.xyz.modules.biz.service.secur.dto.BizSecurHomicidebaseinfoDTO;
import com.xyz.modules.biz.service.secur.dto.BizSecurHomicidebaseinfoQueryCriteria;
import com.xyz.modules.biz.service.secur.mapper.BizSecurHomicidebaseinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Object queryAll(BizSecurHomicidebaseinfoQueryCriteria criteria, Pageable pageable){
        log.info("列表查询社会治安管理/命案基本信息--开始");
        Page<BizSecurHomicidebaseinfo> page = bizSecurHomicidebaseinfoRepository.findAll(audit.genSpecification(criteria),pageable);
        List<BizSecurHomicidebaseinfoDTO> bizSecurHomicidebaseinfoDTOList = bizSecurHomicidebaseinfoMapper.toDto(page.getContent());
        for (BizSecurHomicidebaseinfoDTO mid: bizSecurHomicidebaseinfoDTOList) {
            String dd = deptRepository.findNameByCode(mid.getUnitCode());
            mid.setUnitCodeStr(dd); // 单位编码,所属单位
            dd = dictDetailService.transDict(DictEnum.SJZT.getDistName(),mid.getStatusCd());
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDistName(), mid.getStatusCd()));
            mid.setStatusCdStr(dd==null?"无数据":dd);//数据状态
            mid.setCreator(userRepository.findById(Optional.ofNullable(mid.getCreator()).orElse("")).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(Optional.ofNullable(mid.getOperName()).orElse("")).orElse(new User()).getUsername());
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