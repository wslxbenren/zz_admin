package com.xyz.modules.biz.service.impl;

import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.PsychosisPerson;
import com.xyz.modules.biz.service.dto.DrugPersonDTO;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.PsychosisPersonRepository;
import com.xyz.modules.biz.service.PsychosisPersonService;
import com.xyz.modules.biz.service.dto.PsychosisPersonDTO;
import com.xyz.modules.biz.service.dto.PsychosisPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.PsychosisPersonMapper;
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
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

import javax.annotation.Resource;

/**
 * @author 刘鑫
 * @date 2020-04-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PsychosisPersonServiceImpl implements PsychosisPersonService {

    @Autowired
    private PsychosisPersonRepository PsychosisPersonRepository;

    @Resource
    private PsychosisPersonMapper PsychosisPersonMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;
    @Override
    @Transactional
    public Object queryAll(PsychosisPersonQueryCriteria criteria, Pageable pageable){
        Page<PsychosisPerson> page = PsychosisPersonRepository.findAll(audit.genSpecification(criteria),pageable);
        List<PsychosisPersonDTO> psychosisPersonDTOS = PsychosisPersonMapper.toDto(page.getContent());
        for (PsychosisPersonDTO f:psychosisPersonDTOS){
            String dd = dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), f.getPersonSex());
            f.setPersonSexStr(dd == null ? "无数据" : dd);// 性别
            dd = dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), f.getNation());
            f.setNationStr(dd == null ? "无数据" : dd);//民族
            dd = dictDetailService.transDict(DictEnum.GJ_DQ.getDistName(), f.getNativeInfo());
            f.setNativeInfoStr(dd == null ? "无数据" : dd);//籍贯
            dd = dictDetailService.transDict(DictEnum.HYZK.getDistName(), f.getMarriageFlag());
            f.setMarriageFlagStr(dd == null ? "无数据" : dd);//婚姻状况
            dd = dictDetailService.transDict(DictEnum.ZZMM.getDistName(), f.getPartyFlag());
            f.setPartyFlagStr(dd == null ? "无数据" : dd);// 政治面貌
            dd = dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), f.getEduLevel());
            f.setEduLevelStr(dd == null ? "无数据" : dd);  // 文化程度
            dd = dictDetailService.transDict(DictEnum.ZJXY.getDistName(), f.getFaithType());
            f.setFaithTypeStr(dd == null ? "无数据" : dd);// 宗教信仰
            dd = dictDetailService.transDict(DictEnum.ZYLB.getDistName(), f.getVocationCode());
            f.setVocationCodeStr(dd == null ? "无数据" : dd); // 职业类别
            dd = dictDetailService.transDict(DictEnum.ADDRESS.getDistName(), f.getRegisteredPlace());
            f.setRegisteredPlaceStr(dd == null ? "无数据" : dd);// 户籍地

            dd = dictDetailService.transDict(DictEnum.MQZDLX.getDistName(), f.getDiagnoseType());
            f.setDiagnoseTypeStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.ZLQK.getDistName(), f.getTreatFlag());
            f.setTreatFlagStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.SSZYZLYY.getDistName(), f.getInhospitalReason());
            f.setInhospitalReasonStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.CYGLRY.getDistName(), f.getJoinManager());
            f.setJoinManagerStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.BFQK.getDistName(), f.getHelpeFlag());
            f.setHelpeFlagStr(dd == null ? "无数据" : dd);

            dd = deptRepository.findNameByCode(f.getUnitCode());
            f.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", psychosisPersonDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(PsychosisPersonQueryCriteria criteria){
        return PsychosisPersonMapper.toDto(PsychosisPersonRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public PsychosisPersonDTO findById(String psychosisId) {
        Optional<PsychosisPerson> PsychosisPerson = PsychosisPersonRepository.findById(psychosisId);
        ValidationUtil.isNull(PsychosisPerson,"PsychosisPerson","psychosisId",psychosisId);
        return PsychosisPersonMapper.toDto(PsychosisPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PsychosisPersonDTO create(PsychosisPerson resources) {
        resources.setPsychosisId(IdUtil.simpleUUID()); 
        if(PsychosisPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(PsychosisPerson.class,"identity_num",resources.getIdentityNum());
        }
        return PsychosisPersonMapper.toDto(PsychosisPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PsychosisPerson resources) {
        Optional<PsychosisPerson> optionalPsychosisPerson = PsychosisPersonRepository.findById(resources.getPsychosisId());
        ValidationUtil.isNull( optionalPsychosisPerson,"PsychosisPerson","id",resources.getPsychosisId());
        PsychosisPerson PsychosisPerson = optionalPsychosisPerson.get();
        PsychosisPerson psychosisPerson = PsychosisPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(psychosisPerson != null && !psychosisPerson.getPsychosisId().equals(PsychosisPerson.getPsychosisId())){
            throw new EntityExistException(PsychosisPerson.class,"identity_num",resources.getIdentityNum());
        }
        PsychosisPerson.copy(resources);
        PsychosisPersonRepository.save(PsychosisPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String psychosisId) {
        PsychosisPersonRepository.deleteById(psychosisId);
    }
}