package com.xyz.modules.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.domain.DrugPerson;
import com.xyz.modules.biz.repository.DrugPersonRepository;
import com.xyz.modules.biz.service.DrugPersonService;
import com.xyz.modules.biz.service.dto.DrugPersonDTO;
import com.xyz.modules.biz.service.dto.DrugPersonQueryCriteria;
import com.xyz.modules.biz.service.mapper.DrugPersonMapper;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DeptRepository;
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
public class DrugPersonServiceImpl implements DrugPersonService {

    @Autowired
    private DrugPersonRepository DrugPersonRepository;

    @Resource
    private DrugPersonMapper DrugPersonMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;
    
    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public Object queryAll(DrugPersonQueryCriteria criteria, Pageable pageable){
        Page<DrugPerson> page = DrugPersonRepository.findAll(audit.genSpecification(criteria),pageable);
        List<DrugPersonDTO> drugPersonDTOS = DrugPersonMapper.toDto(page.getContent());
        for (DrugPersonDTO f:drugPersonDTOS){
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

            dd = dictDetailService.transDict(DictEnum.XDYY.getDistName(), f.getDrugReason());
            f.setDrugReasonStr(dd == null ? "无数据" : dd);
            dd = dictDetailService.transDict(DictEnum.XDHG.getDistName(), f.getDrugResult());
            f.setDrugResultStr(dd == null ? "无数据" : dd);

            dd = deptRepository.findNameByCode(f.getUnitCode());
            f.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", drugPersonDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(DrugPersonQueryCriteria criteria){
        return DrugPersonMapper.toDto(DrugPersonRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public DrugPersonDTO findById(String drugId) {
        Optional<DrugPerson> DrugPerson = DrugPersonRepository.findById(drugId);
        ValidationUtil.isNull(DrugPerson,"DrugPerson","drugId",drugId);
        return DrugPersonMapper.toDto(DrugPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DrugPersonDTO create(DrugPerson resources) {
        resources.setDrugId(IdUtil.simpleUUID()); 
        if(DrugPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(DrugPerson.class,"identity_num",resources.getIdentityNum());
        }
        return DrugPersonMapper.toDto(DrugPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DrugPerson resources) {
        Optional<DrugPerson> optionalDrugPerson = DrugPersonRepository.findById(resources.getDrugId());
        ValidationUtil.isNull( optionalDrugPerson,"DrugPerson","id",resources.getDrugId());
        DrugPerson DrugPerson = optionalDrugPerson.get();
        DrugPerson person= DrugPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(person != null && !person.getDrugId().equals(DrugPerson.getDrugId())){
            throw new EntityExistException(DrugPerson.class,"identity_num",resources.getIdentityNum());
        }
        DrugPerson.copy(resources);
        DrugPersonRepository.save(DrugPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String drugId) {
        DrugPersonRepository.deleteById(drugId);
    }
}