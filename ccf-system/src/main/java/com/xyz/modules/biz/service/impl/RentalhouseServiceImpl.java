package com.xyz.modules.biz.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xyz.modules.biz.domain.Rentalhouse;
import com.xyz.modules.biz.repository.RentalhouseRepository;
import com.xyz.modules.biz.service.RentalhouseService;
import com.xyz.modules.biz.service.dto.RentalhouseDTO;
import com.xyz.modules.biz.service.dto.RentalhouseQueryCriteria;
import com.xyz.modules.biz.service.mapper.RentalhouseMapper;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private AuditSpecification audit;

    @Override
    public Object queryAll(RentalhouseQueryCriteria criteria, Pageable pageable){
        DateTime startTime = DateUtil.date(new Date().getTime());
        log.debug("**********出租房信息列表查询开始**********");
        Page<Rentalhouse> page = RentalhouseRepository.findAll(audit.genSpecification(criteria),pageable);
        List<Rentalhouse> content = page.getContent();
        List<RentalhouseDTO> rentalhouseDTOS = RentalhouseMapper.toDto(content);
        for (RentalhouseDTO r:rentalhouseDTOS){
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.XING_BIE.getDistName(), r.getCardType());
            r.setCardTypeStr(dd == null ? "无数据" : dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", rentalhouseDTOS);
        map.put("totalElements", page.getTotalElements());
        DateTime endTime = DateUtil.date(new Date().getTime());
        log.debug("**********出租房信息列表查询用时"+(DateUtil.betweenMs(startTime, endTime))+"毫秒结束**********");
        return map;
    }

    @Override
    public Object queryAll(RentalhouseQueryCriteria criteria){
        return RentalhouseMapper.toDto(RentalhouseRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public RentalhouseDTO findById(String rentId) {
        Optional<Rentalhouse> Rentalhouse = RentalhouseRepository.findById(rentId);
        ValidationUtil.isNull(Rentalhouse,"Rentalhouse","rentId",rentId);
        return RentalhouseMapper.toDto(Rentalhouse.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RentalhouseDTO create(Rentalhouse resources) {
        resources.setRentId(IdUtil.simpleUUID());
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setCreator(u.getId());
        return RentalhouseMapper.toDto(RentalhouseRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Rentalhouse resources) {
        Optional<Rentalhouse> optionalRentalhouse = RentalhouseRepository.findById(resources.getRentId());
        ValidationUtil.isNull( optionalRentalhouse,"Rentalhouse","id",resources.getRentId());
        Rentalhouse Rentalhouse = optionalRentalhouse.get();
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setOperName(u.getId());
        Rentalhouse.copy(resources);
        RentalhouseRepository.save(Rentalhouse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String rentId) {
        RentalhouseRepository.deleteById(rentId);
    }
}