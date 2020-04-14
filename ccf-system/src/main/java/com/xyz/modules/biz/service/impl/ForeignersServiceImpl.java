package com.xyz.modules.biz.service.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.Foreigners;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.*;
import com.xyz.modules.biz.repository.ForeignersRepository;
import com.xyz.modules.biz.service.ForeignersService;
import com.xyz.modules.biz.service.dto.ForeignersDTO;
import com.xyz.modules.biz.service.dto.ForeignersQueryCriteria;
import com.xyz.modules.biz.service.mapper.ForeignersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Override
    @Transactional
    public Object queryAll(ForeignersQueryCriteria criteria, Pageable pageable){
        log.info("查询列表实有人口/境外人员信息--开始");
        Page<Foreigners> page = ForeignersRepository.findAll(audit.genSpecification(criteria),pageable);
        List<ForeignersDTO> foreignersList = ForeignersMapper.toDto(page.getContent());
        for (ForeignersDTO mid: foreignersList) {
            String dd = dictDetailService.transDict(DictEnum.JGCJ.getDistName(), mid.getLastname());
            mid.setLastnameStr(dd == null ? "无数据":dd); // 外文姓
            dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), mid.getPersonSex());
            mid.setPersonSexStr(dd == null ? "无数据":dd); //性别
            dd = dictDetailService.transDict(DictEnum.GJ_DQ.getDistName(), mid.getCountry());
            mid.setCountryStr(dd == null ? "无数据":dd); // 国籍
            dd = dictDetailService.transDict(DictEnum.ZJXY.getDistName(), mid.getFaithType());
            mid.setFaithTypeStr(dd == null ? "无数据":dd); //  宗教信仰
            dd = dictDetailService.transDict(DictEnum.ZJDM.getDistName(), mid.getCardType());
            mid.setCardTypeStr(dd == null ? "无数据":dd); //  证件代码
            dd = dictDetailService.transDict(DictEnum.ZYLB.getDistName(), mid.getVocationCode());
            mid.setVocationCodeStr(dd == null ? "无数据":dd); //  职业类别
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), mid.getResidence());
            mid.setResidenceStr(dd == null ? "无数据":dd); //  现住地

            dd = deptRepository.findNameByCode(mid.getUnitCode());
            mid.setUnitCodeStr(dd);
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