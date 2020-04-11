package com.xyz.modules.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.CorrectPerson;
import com.xyz.modules.biz.repository.CorrectPersonRepository;
import com.xyz.modules.biz.service.CorrectPersonService;
import com.xyz.modules.biz.service.dto.CorrectPersonDTO;
import com.xyz.modules.biz.service.dto.CorrectPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.CorrectPersonMapper;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CorrectPersonServiceImpl implements CorrectPersonService {

    @Autowired
    private CorrectPersonRepository CorrectPersonRepository;

    @Resource
    private CorrectPersonMapper CorrectPersonMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Override
    @Transactional
    public Object queryAll(CorrectPersonQueryCriteria criteria, Pageable pageable){
        Page<CorrectPerson> page = CorrectPersonRepository.findAll(audit.genSpecification(criteria),pageable);
        List<CorrectPersonDTO> correctPersonDTOS = CorrectPersonMapper.toDto(page.getContent());
        for (CorrectPersonDTO f:correctPersonDTOS){
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.XING_BIE.getDistName(), f.getPersonSex());
            f.setPersonSexStr(dd == null ? "无数据" : dd.getLabel());// 性别
            dd = dictDetailService.findByValueAndPName(DictEnum.MIN_ZU.getDistName(), f.getNation());
            f.setNationStr(dd == null ? "无数据" : dd.getLabel());//民族
            dd = dictDetailService.findByValueAndPName(DictEnum.GJ_DQ.getDistName(), f.getNativeInfo());
            f.setNativeInfoStr(dd == null ? "无数据" : dd.getLabel());//籍贯
            dd = dictDetailService.findByValueAndPName(DictEnum.HYZK.getDistName(), f.getMarriageFlag());
            f.setMarriageFlagStr(dd == null ? "无数据" : dd.getLabel());//婚姻状况
            dd = dictDetailService.findByValueAndPName(DictEnum.ZZMM.getDistName(), f.getPartyFlag());
            f.setPartyFlagStr(dd == null ? "无数据" : dd.getLabel());// 政治面貌
            dd = dictDetailService.findByValueAndPName(DictEnum.XUE_LI.getDistName(), f.getEduLevel());
            f.setEduLevelStr(dd == null ? "无数据" : dd.getLabel());  // 文化程度
            dd = dictDetailService.findByValueAndPName(DictEnum.ZJXY.getDistName(), f.getFaithType());
            f.setFaithTypeStr(dd == null ? "无数据" : dd.getLabel());// 宗教信仰
            dd = dictDetailService.findByValueAndPName(DictEnum.ZYLB.getDistName(), f.getVocationCode());
            f.setVocationCodeStr(dd == null ? "无数据" : dd.getLabel()); // 职业类别
            dd = dictDetailService.findByValueAndPName(DictEnum.ADDRESS.getDistName(), f.getRegisteredPlace());
            f.setRegisteredPlaceStr(dd == null ? "无数据" : dd.getLabel());// 户籍地
            dd = dictDetailService.findByValueAndPName(DictEnum.AJLB.getDistName(), f.getCaseType());
            f.setCaseTypeStr(dd == null ? "无数据" : dd.getLabel());

            dd = dictDetailService.findByValueAndPName(DictEnum.JZLX.getDistName(), f.getCorrectType());
            f.setCorrectTypeStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.JSFS.getDistName(), f.getReviceFlag());
            f.setReviceFlagStr(dd == null ? "无数据" : dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", correctPersonDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(CorrectPersonQueryCriteria criteria){
        return CorrectPersonMapper.toDto(CorrectPersonRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public CorrectPersonDTO findById(String correctId) {
        Optional<CorrectPerson> CorrectPerson = CorrectPersonRepository.findById(correctId);
        ValidationUtil.isNull(CorrectPerson,"CorrectPerson","correctId",correctId);
        return CorrectPersonMapper.toDto(CorrectPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CorrectPersonDTO create(CorrectPerson resources) {
        resources.setCorrectId(IdUtil.simpleUUID()); 
        if(CorrectPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(CorrectPerson.class,"identity_num",resources.getIdentityNum());
        }
        return CorrectPersonMapper.toDto(CorrectPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CorrectPerson resources) {
        Optional<CorrectPerson> optionalCorrectPerson = CorrectPersonRepository.findById(resources.getCorrectId());
        ValidationUtil.isNull( optionalCorrectPerson,"CorrectPerson","id",resources.getCorrectId());
        CorrectPerson CorrectPerson = optionalCorrectPerson.get();
        CorrectPerson correctPerson = CorrectPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(correctPerson != null && !correctPerson.getCorrectId().equals(CorrectPerson.getCorrectId())){
            throw new EntityExistException(CorrectPerson.class,"identity_num",resources.getIdentityNum());
        }
        CorrectPerson.copy(resources);
        CorrectPersonRepository.save(CorrectPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String correctId) {
        CorrectPersonRepository.deleteById(correctId);
    }
}