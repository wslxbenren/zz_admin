package com.xyz.modules.biz.service.actual.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.service.actual.entity.Registpeople;
import com.xyz.modules.biz.service.actual.repo.RegistpeopleRepository;
import com.xyz.modules.biz.service.actual.RegistpeopleService;
import com.xyz.modules.biz.service.actual.dto.RegistpeopleDTO;
import com.xyz.modules.biz.service.actual.qo.RegistpeopleQueryCriteria;
import com.xyz.modules.biz.service.actual.mapper.RegistpeopleMapper;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author lx
* @date 2020-04-08
*/
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RegistpeopleServiceImpl implements RegistpeopleService {

    @Autowired
    private RegistpeopleRepository RegistpeopleRepository;

    @Autowired
    private RegistpeopleMapper RegistpeopleMapper;

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
    public Object queryAll(RegistpeopleQueryCriteria criteria, Pageable pageable){
        DateTime startTime = DateUtil.date(new Date().getTime());
        log.info("**********户籍人员信息列表查询开始**********");
        Page<Registpeople> page = RegistpeopleRepository.findAll(audit.genSpecification(criteria),pageable);
        List<Registpeople> content = page.getContent();
        List<RegistpeopleDTO> registpeopleDTOS = RegistpeopleMapper.toDto(content);
        for (RegistpeopleDTO mid:registpeopleDTOS){
            mid.setPersonSexStr(dictDetailService.transDict(DictEnum.XING_BIE.getDistName(), mid.getPersonSex()));
            mid.setNationStr(dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), mid.getNation()));
            mid.setNativeInfoStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getNativeInfo())); // 籍贯
            mid.setMarriageFlagStr(dictDetailService.transDict(DictEnum.HYZK.getDistName(), mid.getMarriageFlag()));
            mid.setPartyFlagStr(dictDetailService.transDict(DictEnum.ZZMM.getDistName(), mid.getPartyFlag()));
            mid.setEducationBgStr(dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), mid.getEducationBg()));
            mid.setFaithTypeStr(dictDetailService.transDict(DictEnum.ZJXY.getDistName(), mid.getFaithType()));
            mid.setVocationCodeStr(dictDetailService.transMultistage(DictEnum.ZYLB.getDictId(), mid.getVocationCode())); // 职业类别
            mid.setRegisteredPlaceStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), mid.getRegisteredPlace())); // 户籍地
            mid.setHouseheadRelaStr(dictDetailService.transDict(DictEnum.YHZGX.getDistName(), mid.getHouseheadRela()));
            mid.setCreator(userRepository.findById(mid.getCreator()).orElse(new User()).getUsername());
            mid.setOperName(userRepository.findById(mid.getOperName()).orElse(new User()).getUsername());
            mid.setUnitCodeStr(deptRepository.findNameByCode(mid.getUnitCode()));
        }
        Map map = new HashMap();
        map.put("content", registpeopleDTOS);
        map.put("totalElements", page.getTotalElements());
        DateTime endTime = DateUtil.date(new Date().getTime());
        log.info("**********户籍人员信息列表查询用时"+(DateUtil.betweenMs(startTime, endTime))+"毫秒结束**********");
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(RegistpeopleQueryCriteria criteria){
        log.info("**********条件查询 Registpeople 列表**********");
        return RegistpeopleMapper.toDto(RegistpeopleRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public RegistpeopleDTO findById(String regisId) {
        log.info("********** 查询 Registpeople 详情**********");
        Optional<Registpeople> Registpeople = RegistpeopleRepository.findById(regisId);
        ValidationUtil.isNull(Registpeople,"Registpeople","regisId",regisId);
        return RegistpeopleMapper.toDto(Registpeople.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegistpeopleDTO create(Registpeople resources) {
        log.info("********** 新增 Registpeople  **********");
        resources.setRegisId(IdUtil.simpleUUID());
        if(RegistpeopleRepository.findByIdentityNum(resources.getIdentityNum()) != null){
            log.info("**********   Registpeople identity_num 重复 新增失败  **********");
            throw new EntityExistException(Registpeople.class,"identity_num",resources.getIdentityNum());
        }
        return RegistpeopleMapper.toDto(RegistpeopleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Registpeople resources) {
        log.info("********** 修改 Registpeople  **********");
        Optional<Registpeople> optionalRegistpeople = RegistpeopleRepository.findById(resources.getRegisId());
        ValidationUtil.isNull( optionalRegistpeople,"Registpeople","id",resources.getRegisId());
        Registpeople Registpeople = optionalRegistpeople.get();
        Registpeople Registpeople1 = null;
        Registpeople1 = RegistpeopleRepository.findByIdentityNum(resources.getIdentityNum());
        if(Registpeople1 != null && !Registpeople1.getRegisId().equals(Registpeople.getRegisId())){
            log.info("**********   Registpeople identity_num 重复 修改失败  **********");
            throw new EntityExistException(Registpeople.class,"identity_num",resources.getIdentityNum());
        }
        Registpeople.copy(resources);
        RegistpeopleRepository.save(Registpeople);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String regisId) {
        log.info("********** 删除 Registpeople  **********");
        RegistpeopleRepository.deleteById(regisId);
    }
}