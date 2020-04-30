package com.xyz.modules.biz.service.special.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.service.special.entity.DrugPerson;
import com.xyz.modules.biz.service.special.repo.DrugPersonRepository;
import com.xyz.modules.biz.service.special.DrugPersonService;
import com.xyz.modules.biz.service.special.dto.DrugPersonDTO;
import com.xyz.modules.biz.service.special.qo.DrugPersonQueryCriteria;
import com.xyz.modules.biz.service.special.mapper.DrugPersonMapper;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Object queryAll(DrugPersonQueryCriteria criteria, Pageable pageable) {
        log.info("条件查询 DrugPerson 列表-分页");
        Page<DrugPerson> page = DrugPersonRepository.findAll(audit.genSpecification(criteria), pageable);
        List<DrugPersonDTO> drugPersonDTOS = DrugPersonMapper.toDto(page.getContent());
        for (DrugPersonDTO mid : drugPersonDTOS) {
            mid.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), mid.getPersonSex()));// 性别
            mid.setNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), mid.getNation()));//民族
            mid.setNativeInfoStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getNativeInfo()));
            mid.setMarriageFlagStr(dictDetailService.transDict(DictEnum.HYZK.getDistName(), mid.getMarriageFlag()));//婚姻状况
            mid.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZZMM.getDistName(), mid.getPartyFlag()));// 政治面貌
            mid.setEduLevelStr(dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), mid.getEduLevel()));  // 文化程度
            mid.setFaithTypeStr(dictDetailService.transDict(DictEnum.ZJXY.getDistName(), mid.getFaithType()));// 宗教信仰
            mid.setVocationCodeStr(dictDetailService.transMultistage(DictEnum.ZYLB.getDictId(), mid.getVocationCode()));
            mid.setRegisteredPlaceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getRegisteredPlace()));
            mid.setDrugReasonStr(dictDetailService.transDict(DictEnum.XDYY.getDistName(), mid.getDrugReason()));
            mid.setDrugResultStr(dictDetailService.transDict(DictEnum.XDHG.getDistName(), mid.getDrugResult()));
            mid.setCreator(userRepository.findById(Optional.ofNullable(mid.getCreator()).orElse("")).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(Optional.ofNullable(mid.getOperName()).orElse("")).orElse(new User()).getUsername());
            mid.setUnitCodeStr(deptRepository.findNameByCode(mid.getUnitCode()));
            mid.setResidenceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getResidence()));
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDistName(), mid.getStatusCd()));
            mid.setIsPedigreeStr(ConstEnum.getBoolean(mid.getIsPedigree()));
            mid.setServicePlaceCodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getServicePlaceCode()));
            mid.setHelpeCommentStr(dictDetailService.transDict(DictEnum.BFQK.getDistName(), mid.getHelpeComment()));
            mid.setManageTypeStr(dictDetailService.transDict(DictEnum.GKQK.getDistName(), mid.getManageType()));
            mid.setIsPedigreeStr(ConstEnum.getBoolean(mid.getIsPedigree()));
        }
        Map map = new HashMap();
        map.put("content", drugPersonDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(DrugPersonQueryCriteria criteria) {
        log.info("条件查询 DrugPerson 列表");
        return DrugPersonMapper.toDto(DrugPersonRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public DrugPersonDTO findById(String drugId) {
        log.info(" 查询 DrugPerson 详情");
        Optional<DrugPerson> DrugPerson = DrugPersonRepository.findById(drugId);
        ValidationUtil.isNull(DrugPerson, "DrugPerson", "drugId", drugId);
        return DrugPersonMapper.toDto(DrugPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DrugPersonDTO create(DrugPerson resources) {
        log.info(" 新增 DrugPerson ");
        resources.setDrugId(IdUtil.simpleUUID());
        if (DrugPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null) {
            log.info("  DrugPerson identityNum 重复 新增失败");
            throw new EntityExistException(DrugPerson.class, "identity_num", resources.getIdentityNum());
        }
        return DrugPersonMapper.toDto(DrugPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DrugPerson resources) {
        log.info(" 修改 DrugPerson ");
        Optional<DrugPerson> optionalDrugPerson = DrugPersonRepository.findById(resources.getDrugId());
        ValidationUtil.isNull(optionalDrugPerson, "DrugPerson", "id", resources.getDrugId());
        DrugPerson DrugPerson = optionalDrugPerson.get();
        DrugPerson person = DrugPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if (person != null && !person.getDrugId().equals(DrugPerson.getDrugId())) {
            log.info("  DrugPerson identityNum 重复 修改失败");
            throw new EntityExistException(DrugPerson.class, "identity_num", resources.getIdentityNum());
        }
        DrugPerson.copy(resources);
        DrugPersonRepository.save(DrugPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String drugId) {
        log.info(" 删除 DrugPerson ");
        DrugPersonRepository.deleteById(drugId);
    }

    @Override
    public Boolean validateIdentityNum(String id, String identityNum) {
        log.info("********** 检验身份证号码是否存在  ======>");
        Long isNull = DrugPersonRepository.validateIdentityNum(identityNum);
        if (isNull == 0) {
            return false;
        } else if (isNull == 1) {
            isNull = DrugPersonRepository.validateIdentityNumById(id, identityNum);
            return isNull == 1 ? false : true;
        }else {
            return true;
        }

    }
}