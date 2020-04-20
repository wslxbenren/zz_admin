package com.xyz.modules.biz.service.actual.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.service.actual.entity.Foreigners;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.service.actual.repo.ForeignersRepository;
import com.xyz.modules.biz.service.actual.ForeignersService;
import com.xyz.modules.biz.service.actual.dto.ForeignersDTO;
import com.xyz.modules.biz.service.actual.qo.ForeignersQueryCriteria;
import com.xyz.modules.biz.service.actual.mapper.ForeignersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* @author xjh
* @date 2020-04-08
*/
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ForeignersServiceImpl implements ForeignersService {

    @Autowired
    private ForeignersRepository ForeignersRepository;

    @Autowired
    private ForeignersMapper ForeignersMapper;

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
    public Object queryAll(ForeignersQueryCriteria criteria, Pageable pageable){
        log.info("查询列表实有人口/境外人员信息--开始");
        Page<Foreigners> page = ForeignersRepository.findAll(audit.genSpecification(criteria),pageable);
        List<ForeignersDTO> foreignersList = ForeignersMapper.toDto(page.getContent());
        for (ForeignersDTO mid: foreignersList) {
            mid.setLastnameStr(dictDetailService.transDict(DictEnum.JGCJ.getDistName(), mid.getLastname())); // 外文姓
            mid.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), mid.getPersonSex())); //性别
            mid.setCountryStr(dictDetailService.transDict(DictEnum.GJ_DQ.getDistName(), mid.getCountry())); // 国籍
            mid.setFaithTypeStr(dictDetailService.transDict(DictEnum.ZJXY.getDistName(), mid.getFaithType())); //  宗教信仰
            mid.setCardTypeStr(dictDetailService.transDict(DictEnum.ZJDM.getDistName(), mid.getCardType())); //  证件代码
            mid.setVocationCodeStr(dictDetailService.transMultistage(DictEnum.ZYLB.getDictId(), mid.getVocationCode())); // 职业类别
            mid.setResidence(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getVocationCode())); // 现住地
            mid.setCreator(userRepository.findById(mid.getCreator()).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(mid.getOperName()).orElse(new User()).getUsername());
            mid.setUnitCodeStr(deptRepository.findNameByCode(mid.getUnitCode()));
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDistName(), mid.getStatusCd()));
            mid.setIfImportStr(ConstEnum.getBoolean(mid.getIfImport()));
            mid.setServicePlaceCodeStr(dictDetailService.transDict(DictEnum.ADDRESS.getDictId(), mid.getServicePlaceCode()));
        }
        Map map = new HashMap();
        map.put("content", foreignersList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(ForeignersQueryCriteria criteria){
        return ForeignersMapper.toDto(ForeignersRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public ForeignersDTO findById(String foreId) {
            log.info("查询详情实有人口/境外人员信息--开始");
            if (StringUtils.isBlank(foreId)){
                throw new BadRequestException("主键ID不能为空");
            }
            Optional<Foreigners> Foreigners = ForeignersRepository.findById(foreId);
            ValidationUtil.isNull(Foreigners, "Foreigners", "foreId", foreId);
            return ForeignersMapper.toDto(Foreigners.get());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ForeignersDTO create(Foreigners resources) {
        log.info("新增实有人口/境外人员信息--开始");
        resources.setForeId(IdUtil.simpleUUID());
        return ForeignersMapper.toDto(ForeignersRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Foreigners resources) {
            log.info("修改实有人口/境外人员信息--开始");
            if (StringUtils.isBlank(resources.getForeId())){
                throw new BadRequestException("主键ID不能为空");
            }
            Optional<Foreigners> optionalForeigners = ForeignersRepository.findById(resources.getForeId());
            ValidationUtil.isNull(optionalForeigners, "Foreigners", "id", resources.getForeId());
            Foreigners Foreigners = optionalForeigners.get();
            Foreigners.copy(resources);
            ForeignersRepository.save(Foreigners);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String foreId) {
            log.info("删除实有人口/境外人员信息--开始");
            if (StringUtils.isBlank(foreId)){
                throw new BadRequestException("主键ID不能为空");
            }
            ForeignersRepository.deleteById(foreId);

    }
}