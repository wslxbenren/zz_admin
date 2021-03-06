package com.xyz.modules.biz.service.actual.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.exception.EntityExistException;
import com.xyz.modules.biz.audit.mongo.ModifyRecords;
import com.xyz.modules.biz.audit.mongo.service.ModifyRecordsRepo;
import com.xyz.modules.biz.service.actual.entity.Rentalhouse;
import com.xyz.modules.biz.service.actual.repo.RentalhouseRepository;
import com.xyz.modules.biz.service.actual.RentalhouseService;
import com.xyz.modules.biz.service.actual.dto.RentalhouseDTO;
import com.xyz.modules.biz.service.actual.qo.RentalhouseQueryCriteria;
import com.xyz.modules.biz.service.actual.mapper.RentalhouseMapper;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.CompareFieldsService;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.ConstEnum;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
* @author lx
* @date 2020-04-09
*/
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RentalhouseServiceImpl implements RentalhouseService {

    @Autowired
    private RentalhouseRepository RentalhouseRepository;

    @Autowired
    private RentalhouseMapper RentalhouseMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModifyRecordsRepo recordsRepo;

    @Autowired
    private CompareFieldsService compareFieldsService;


    @Override
    @Transactional
    public Object queryAll(RentalhouseQueryCriteria criteria, Pageable pageable){
        DateTime startTime = DateUtil.date(new Date().getTime());
        log.info("**********出租房信息列表查询开始**********");
        Page<Rentalhouse> page = RentalhouseRepository.findAll(audit.genSpecification(criteria),pageable);
        List<Rentalhouse> content = page.getContent();
        List<RentalhouseDTO> rentalhouseDTOS = RentalhouseMapper.toDto(content);
        for (RentalhouseDTO r:rentalhouseDTOS){
            String dd = dictDetailService.transDict(DictEnum.YHLX.getDistName(), r.getHazardType());
            r.setHazardTypeStr(dd == null ? "无数据" : dd );
             dd = dictDetailService.transDict(DictEnum.JZYT.getDistName(), r.getConstrPurpose());
            r.setConstrPurposeStr(dd == null ? "无数据" : dd );
             dd = dictDetailService.transDict(DictEnum.CZYT.getDistName(), r.getRentalPurposes());
            r.setRentalPurposesStr(dd == null ? "无数据" : dd );
            dd = dictDetailService.transDict(DictEnum.ZJDM.getDistName(), r.getCardType());
            r.setCardTypeStr(dd == null ? "无数据" : dd );
            dd = deptRepository.findNameByCode(r.getUnitCode());
            r.setUnitCodeStr(dd);
            r.setCardTypeStr(dictDetailService.transDict(DictEnum.ZJDM.getDictId(), r.getCardType()) );
            r.setCreator(userRepository.findById(Optional.ofNullable(r.getCreator()).orElse("")).orElse(new User()).getUsername());
            r.setOperName(userRepository.findById(Optional.ofNullable(r.getOperName()).orElse("")).orElse(new User()).getUsername());
            r.setStatusStr(ConstEnum.transSync(r.getStatus()));
            r.setStatusCdStr(dictDetailService.transDict(DictEnum.SJZT.getDictId(), r.getStatusCd()));
            r.setUnitCodeStr(deptRepository.findNameByCode(r.getUnitCode()));
            r.setHomeownerAddrcodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), r.getHomeownerAddrcode()));
            r.setHouseAddrcodeStr(dictDetailService.transMultistage(DictEnum.ADDRESS.getDictId(), r.getHouseAddrcode()));
        }
        Map map = new HashMap();
        map.put("content", rentalhouseDTOS);
        map.put("totalElements", page.getTotalElements());
        DateTime endTime = DateUtil.date(new Date().getTime());
        log.info("**********出租房信息列表查询用时"+(DateUtil.betweenMs(startTime, endTime))+"毫秒结束**********");
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(RentalhouseQueryCriteria criteria){
        log.info("********** 条件查询 Rentalhouse 列表**********");
        return RentalhouseMapper.toDto(RentalhouseRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public RentalhouseDTO findById(String rentId) {
        log.info("**********  查询 Rentalhouse 详情**********");
        Optional<Rentalhouse> Rentalhouse = RentalhouseRepository.findById(rentId);
        ValidationUtil.isNull(Rentalhouse,"Rentalhouse","rentId",rentId);
        return RentalhouseMapper.toDto(Rentalhouse.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RentalhouseDTO create(Rentalhouse resources) {
        log.info("**********  新增 Rentalhouse  **********");
        resources.setRentId(IdUtil.simpleUUID());
        if(RentalhouseRepository.findByCardNo(resources.getCardNo()) != null){
            log.info("**********  Rentalhouse  card_no重复 新增失败  **********");
            throw new EntityExistException(Rentalhouse.class,"card_no",resources.getCardNo());
        }
        return RentalhouseMapper.toDto(RentalhouseRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Rentalhouse resources) {
        log.info("**********  修改 Rentalhouse  **********");
        Optional<Rentalhouse> optionalRentalhouse = RentalhouseRepository.findById(resources.getRentId());
        ValidationUtil.isNull( optionalRentalhouse,"Rentalhouse","id",resources.getRentId());
        Rentalhouse Rentalhouse = optionalRentalhouse.get();

        ModifyRecords modifyRecords = new ModifyRecords();
        modifyRecords.setModifyContent(compareFieldsService.
                compareModifyRecords(Rentalhouse, resources, new String[]{"rentId","effDate","expDate","operDate","operName","createTime","creator"}));
        modifyRecords.setEntityId(Rentalhouse.getRentId());
        modifyRecords.setId(IdUtil.simpleUUID());
        modifyRecords.setOperName(SecurityUtils.getUsername());
        modifyRecords.setOperTime(LocalDateTime.now());
        modifyRecords.setDeptName(deptRepository.findNameByCode(Rentalhouse.getUnitCode()));
        modifyRecords.setCreateTime(Rentalhouse.getCreateTime().toLocalDateTime());
        modifyRecords.setCreator(Rentalhouse.getCreator());
        recordsRepo.save(modifyRecords);

        Rentalhouse.copy(resources);
        RentalhouseRepository.save(Rentalhouse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String rentId) {
        log.info("**********  删除 Rentalhouse  **********");
        RentalhouseRepository.deleteById(rentId);
    }

    @Override
    public List<ModifyRecords> findModifyRecordsById(String rentId) {
        List<ModifyRecords> records = recordsRepo.findAllByEntityId(rentId);
        return records;
    }
}