package com.xyz.modules.biz.service.secur.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.service.secur.entity.BizSecurKeyareas;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.service.secur.repo.BizSecurKeyareasRepository;
import com.xyz.modules.biz.service.secur.BizSecurKeyareasService;
import com.xyz.modules.biz.service.secur.dto.BizSecurKeyareasDTO;
import com.xyz.modules.biz.service.secur.dto.BizSecurKeyareasQueryCriteria;
import com.xyz.modules.biz.service.secur.mapper.BizSecurKeyareasMapper;
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
public class BizSecurKeyareasServiceImpl implements BizSecurKeyareasService {

    @Autowired
    private BizSecurKeyareasRepository bizSecurKeyareasRepository;

    @Autowired
    private BizSecurKeyareasMapper bizSecurKeyareasMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;


    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Object queryAll(BizSecurKeyareasQueryCriteria criteria, Pageable pageable){
        log.info("查询列表社会治安管理/重点地区排查整治信息--开始");
        Page<BizSecurKeyareas> page = bizSecurKeyareasRepository.findAll(audit.genSpecification(criteria),pageable);
        List<BizSecurKeyareasDTO> bizSecurKeyareasDTOList = bizSecurKeyareasMapper.toDto(page.getContent());
        for (BizSecurKeyareasDTO mid: bizSecurKeyareasDTOList) {

            mid.setOutproblemStr(dictDetailService.transDict(DictEnum.ZATCWT.getDictId(), mid.getOutproblem())); // 治安突出问题
            mid.setAreaTypeStr(dictDetailService.transDict(DictEnum.SJQYLX.getDictId(), mid.getAreaType())); // 涉及区域类型
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDictId(), mid.getStatusCd())); // 数据状态
            mid.setEvaluationStr(dictDetailService.transDict(DictEnum.XGPG.getDictId(), mid.getEvaluation())); //效果评估
            mid.setUnitCodeStr(deptRepository.findNameByCode(mid.getUnitCode()));
            mid.setCreator(userRepository.findById(Optional.ofNullable(mid.getCreator()).orElse("")).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(Optional.ofNullable(mid.getOperName()).orElse("")).orElse(new User()).getUsername());


        }
        Map map = new HashMap();
        map.put("content", bizSecurKeyareasDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(BizSecurKeyareasQueryCriteria criteria){
        return bizSecurKeyareasMapper.toDto(bizSecurKeyareasRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public BizSecurKeyareasDTO findById(String keyId) {
        log.info("详情社会治安管理/重点地区排查整治信息--开始");
        if (StringUtils.isBlank(keyId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<BizSecurKeyareas> bizSecurKeyareas = bizSecurKeyareasRepository.findById(keyId);
        ValidationUtil.isNull(bizSecurKeyareas,"BizSecurKeyareas","keyId",keyId);
        return bizSecurKeyareasMapper.toDto(bizSecurKeyareas.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizSecurKeyareasDTO create(BizSecurKeyareas resources) {
        log.info("新增社会治安管理/重点地区排查整治信息--开始");
        resources.setKeyId(IdUtil.simpleUUID());
        return bizSecurKeyareasMapper.toDto(bizSecurKeyareasRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BizSecurKeyareas resources) {
        log.info("修改社会治安管理/重点地区排查整治信息--开始");
        if (StringUtils.isBlank(resources.getKeyId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<BizSecurKeyareas> optionalBizSecurKeyareas = bizSecurKeyareasRepository.findById(resources.getKeyId());
        ValidationUtil.isNull( optionalBizSecurKeyareas,"BizSecurKeyareas","id",resources.getKeyId());
        BizSecurKeyareas bizSecurKeyareas = optionalBizSecurKeyareas.get();
        bizSecurKeyareas.copy(resources);
        bizSecurKeyareasRepository.save(bizSecurKeyareas);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String keyId) {
        log.info("删除社会治安管理/重点地区排查整治信息--开始");
        if (StringUtils.isBlank(keyId)){
            throw new BadRequestException("主键ID不能为空");
        }
        bizSecurKeyareasRepository.deleteById(keyId);
    }
}