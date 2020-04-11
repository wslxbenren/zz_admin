package com.xyz.modules.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.AidsPerson;
import com.xyz.modules.biz.repository.AidsPersonRepository;
import com.xyz.modules.biz.service.AidsPersonService;
import com.xyz.modules.biz.service.dto.AidsPersonDTO;
import com.xyz.modules.biz.service.dto.AidsPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.AidsPersonMapper;
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
public class AidsPersonServiceImpl implements AidsPersonService {

    @Autowired
    private AidsPersonRepository AidsPersonRepository;

    @Resource
    private AidsPersonMapper AidsPersonMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Override
    public Object queryAll(AidsPersonQueryCriteria criteria, Pageable pageable){
        Page<AidsPerson> page = AidsPersonRepository.findAll(audit.genSpecification(criteria),pageable);
        List<AidsPersonDTO> aidsPersonDTOS = AidsPersonMapper.toDto(page.getContent());
        for (AidsPersonDTO f:aidsPersonDTOS){
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.XING_BIE.getDistName(), f.getPersonSex());
            f.setPersonSexStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.MIN_ZU.getDistName(), f.getNation());
            f.setNationStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.GJ_DQ.getDistName(), f.getNativeInfo());
            f.setNativeInfoStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.HYZK.getDistName(), f.getMarriageFlag());
            f.setMarriageFlagStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZZMM.getDistName(), f.getPartyFlag());
            f.setPartyFlagStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.XUE_LI.getDistName(), f.getEduLevel());
            f.setEduLevelStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZJXY.getDistName(), f.getFaithType());
            f.setFaithTypeStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZYLB.getDistName(), f.getVocationCode());
            f.setVocationCodeStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ADDRESS.getDistName(), f.getRegisteredPlace());
            f.setRegisteredPlaceStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.AJLB.getDistName(), f.getCaseType());
            f.setCaseTypeStr(dd == null ? "无数据" : dd.getLabel());

            dd = dictDetailService.findByValueAndPName(DictEnum.GRTJ.getDistName(), f.getRoutesInfection());
            f.setRoutesInfectionStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.GZLX.getDistName(), f.getTakeType());
            f.setTakeTypeStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.BFQK.getDistName(), f.getHelpComments());
            f.setHelpCommentsStr(dd == null ? "无数据" : dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.SZQK.getDistName(), f.getDetainType());
            f.setDetainTypeStr(dd == null ? "无数据" : dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", aidsPersonDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    public Object queryAll(AidsPersonQueryCriteria criteria){
        return AidsPersonMapper.toDto(AidsPersonRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public AidsPersonDTO findById(String aidsId) {
        Optional<AidsPerson> AidsPerson = AidsPersonRepository.findById(aidsId);
        ValidationUtil.isNull(AidsPerson,"AidsPerson","aidsId",aidsId);
        return AidsPersonMapper.toDto(AidsPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AidsPersonDTO create(AidsPerson resources) {
        resources.setAidsId(IdUtil.simpleUUID()); 
        if(AidsPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(AidsPerson.class,"identity_num",resources.getIdentityNum());
        }
        return AidsPersonMapper.toDto(AidsPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AidsPerson resources) {
        Optional<AidsPerson> optionalAidsPerson = AidsPersonRepository.findById(resources.getAidsId());
        ValidationUtil.isNull( optionalAidsPerson,"AidsPerson","id",resources.getAidsId());
        AidsPerson AidsPerson = optionalAidsPerson.get();
//        AidsPerson1 = AidsPersonRepository.findByIdentityNum(resources.getIdentityNum());
//        if(AidsPerson1 != null && !AidsPerson1.getAidsId().equals(AidsPerson.getAidsId())){
//            throw new EntityExistException(AidsPerson.class,"identity_num",resources.getIdentityNum());
//        }
        AidsPerson.copy(resources);
        AidsPersonRepository.save(AidsPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String aidsId) {
        AidsPersonRepository.deleteById(aidsId);
    }
}