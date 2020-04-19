package com.xyz.modules.biz.service.special.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.service.special.entity.CorrectPerson;
import com.xyz.modules.biz.service.special.repo.CorrectPersonRepository;
import com.xyz.modules.biz.service.special.CorrectPersonService;
import com.xyz.modules.biz.service.special.dto.CorrectPersonDTO;
import com.xyz.modules.biz.service.special.qo.CorrectPersonQueryCriteria;
import com.xyz.modules.biz.service.special.mapper.CorrectPersonMapper;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
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
public class CorrectPersonServiceImpl implements CorrectPersonService {

    @Autowired
    private CorrectPersonRepository CorrectPersonRepository;

    @Resource
    private CorrectPersonMapper CorrectPersonMapper;

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
    public Object queryAll(CorrectPersonQueryCriteria criteria, Pageable pageable){
        log.info("条件查询 CorrectPerson 分页列表 ");
        Page<CorrectPerson> page = CorrectPersonRepository.findAll(audit.genSpecification(criteria),pageable);
        List<CorrectPersonDTO> correctPersonDTOS = CorrectPersonMapper.toDto(page.getContent());
        for (CorrectPersonDTO mid:correctPersonDTOS){
            mid.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), mid.getPersonSex()));// 性别
            mid.setNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), mid.getNation()));//民族
            mid.setNativeInfoStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getNativeInfo()));
            mid.setMarriageFlagStr(dictDetailService.transDict(DictEnum.HYZK.getDistName(), mid.getMarriageFlag()));//婚姻状况
            mid.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZZMM.getDistName(), mid.getPartyFlag()));// 政治面貌
            mid.setEduLevelStr(dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), mid.getEduLevel()));  // 文化程度
            mid.setFaithTypeStr(dictDetailService.transDict(DictEnum.ZJXY.getDistName(), mid.getFaithType()));// 宗教信仰
            mid.setVocationCodeStr(dictDetailService.transMultistage(DictEnum.ZYLB.getDictId(), mid.getVocationCode()));
            mid.setRegisteredPlaceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getRegisteredPlace()));
            mid.setCaseTypeStr(dictDetailService.transMultistage(DictEnum.AJLB.getDictId(), mid.getCaseType()));
            mid.setCorrectTypeStr(dictDetailService.transDict(DictEnum.JZLX.getDistName(), mid.getCorrectType()));
            mid.setReviceFlagStr(dictDetailService.transDict(DictEnum.JSFS.getDistName(), mid.getReviceFlag()));
            mid.setCreator(userRepository.findById(mid.getCreator()).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(mid.getOperName()).orElse(new User()).getUsername());
            mid.setUnitCodeStr(deptRepository.findNameByCode(mid.getUnitCode()));
            mid.setStatusStr(ConstEnum.transSync(mid.getStatus()));
            mid.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDistName(), mid.getStatusCd()));

            mid.setIsRecidivistStr(ConstEnum.getBoolean(mid.getIsRecidivist()));
            mid.setIsTeamStr(ConstEnum.getBoolean(mid.getIsTeam()));
            mid.setIsBreakmanageStr(ConstEnum.getBoolean(mid.getIsBreakmanage()));
            mid.setIsOmitStr(ConstEnum.getBoolean(mid.getIsOmit()));
            mid.setIsAgainStr(ConstEnum.getBoolean(mid.getIsAgain()));
        }
        Map map = new HashMap();
        map.put("content", correctPersonDTOS);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(CorrectPersonQueryCriteria criteria){
        log.info("条件查询 CorrectPerson 列表");
        return CorrectPersonMapper.toDto(CorrectPersonRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public CorrectPersonDTO findById(String correctId) {
        log.info(" 查询 CorrectPerson 详情");
        Optional<CorrectPerson> CorrectPerson = CorrectPersonRepository.findById(correctId);
        ValidationUtil.isNull(CorrectPerson,"CorrectPerson","correctId",correctId);
        return CorrectPersonMapper.toDto(CorrectPerson.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CorrectPersonDTO create(CorrectPerson resources) {
        log.info(" 新增 CorrectPerson ");
        resources.setCorrectId(IdUtil.simpleUUID()); 
        if(CorrectPersonRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            throw new EntityExistException(CorrectPerson.class,"identity_num",resources.getIdentityNum());
        }
        return CorrectPersonMapper.toDto(CorrectPersonRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CorrectPerson resources) {
        log.info(" 修改 CorrectPerson ");
        Optional<CorrectPerson> optionalCorrectPerson = CorrectPersonRepository.findById(resources.getCorrectId());
        ValidationUtil.isNull( optionalCorrectPerson,"CorrectPerson","id",resources.getCorrectId());
        CorrectPerson CorrectPerson = optionalCorrectPerson.get();
        CorrectPerson correctPerson = CorrectPersonRepository.findByIdentityNum(resources.getIdentityNum());
        if(correctPerson != null && !correctPerson.getCorrectId().equals(CorrectPerson.getCorrectId())){
            log.info("  CorrectPerson identity_num 重复 修改失败");
            throw new EntityExistException(CorrectPerson.class,"identity_num",resources.getIdentityNum());
        }
        CorrectPerson.copy(resources);
        CorrectPersonRepository.save(CorrectPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String correctId) {
        log.info(" 删除 CorrectPerson ");
        CorrectPersonRepository.deleteById(correctId);
    }
}