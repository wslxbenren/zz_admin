package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Foreigners;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoDTO;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.ForeignersRepository;
import com.xyz.modules.biz.service.ForeignersService;
import com.xyz.modules.biz.service.dto.ForeignersDTO;
import com.xyz.modules.biz.service.dto.ForeignersQueryCriteria;
import com.xyz.modules.biz.service.mapper.ForeignersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

/**
* @author xjh
* @date 2020-04-08
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ForeignersServiceImpl implements ForeignersService {

    @Autowired
    private ForeignersRepository ForeignersRepository;

    @Autowired
    private ForeignersMapper ForeignersMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Override
    public Object queryAll(ForeignersQueryCriteria criteria, Pageable pageable){
        Page<Foreigners> page = ForeignersRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<ForeignersDTO> foreignersList = ForeignersMapper.toDto(page.getContent());
        for (ForeignersDTO mid: foreignersList) {
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.JGCJ.getDistName(), mid.getLastname());
            mid.setLastnameStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.XING_BIE.getDistName(), mid.getPersonSex());
            mid.setPersonSexStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.GJ_DQ.getDistName(), mid.getCountry());
            mid.setCountryStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZJXY.getDistName(), mid.getFaithType());
            mid.setFaithTypeStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZJDM.getDistName(), mid.getCardType());
            mid.setCardTypeStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZYLB.getDistName(), mid.getVocationCode());
            mid.setVocationCodeStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ADDRESS.getDistName(), mid.getResidence());
            mid.setResidenceStr(dd == null ? "无数据":dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", foreignersList);
        map.put("totalElements", page.getTotalPages());
        return map;
    }

    @Override
    public Object queryAll(ForeignersQueryCriteria criteria){
        return ForeignersMapper.toDto(ForeignersRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ForeignersDTO findById(String foreId) {
        Optional<Foreigners> Foreigners = ForeignersRepository.findById(foreId);
        ValidationUtil.isNull(Foreigners,"Foreigners","foreId",foreId);
        return ForeignersMapper.toDto(Foreigners.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ForeignersDTO create(Foreigners resources) {
        resources.setForeId(IdUtil.simpleUUID());
        resources.setCreateTime(new Timestamp(new Date().getTime()));
        return ForeignersMapper.toDto(ForeignersRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Foreigners resources) {
        resources.setOperDate(new Timestamp(new Date().getTime()));
        Optional<Foreigners> optionalForeigners = ForeignersRepository.findById(resources.getForeId());
        ValidationUtil.isNull( optionalForeigners,"Foreigners","id",resources.getForeId());
        Foreigners Foreigners = optionalForeigners.get();
        Foreigners.copy(resources);
        ForeignersRepository.save(Foreigners);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String foreId) {
        ForeignersRepository.deleteById(foreId);
    }
}