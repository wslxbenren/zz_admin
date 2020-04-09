package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Rentalhouse;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.RentalhouseRepository;
import com.xyz.modules.biz.service.RentalhouseService;
import com.xyz.modules.biz.service.dto.RentalhouseDTO;
import com.xyz.modules.biz.service.dto.RentalhouseQueryCriteria;
import com.xyz.modules.biz.service.mapper.RentalhouseMapper;
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

/**
* @author lx
* @date 2020-04-09
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RentalhouseServiceImpl implements RentalhouseService {

    @Autowired
    private RentalhouseRepository RentalhouseRepository;

    @Autowired
    private RentalhouseMapper RentalhouseMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Override
    public Object queryAll(RentalhouseQueryCriteria criteria, Pageable pageable){
        Page<Rentalhouse> page = RentalhouseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<Rentalhouse> content = page.getContent();
        List<RentalhouseDTO> rentalhouseDTOS = RentalhouseMapper.toDto(content);
        for (RentalhouseDTO r:rentalhouseDTOS){
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.XING_BIE.getDistName(), r.getCardType());
            r.setCardTypeStr(dd == null ? "无数据" : dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", rentalhouseDTOS);
        map.put("totalElements", page.getTotalPages());
        return map;
    }

    @Override
    public Object queryAll(RentalhouseQueryCriteria criteria){
        return RentalhouseMapper.toDto(RentalhouseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
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
        return RentalhouseMapper.toDto(RentalhouseRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Rentalhouse resources) {
        Optional<Rentalhouse> optionalRentalhouse = RentalhouseRepository.findById(resources.getRentId());
        ValidationUtil.isNull( optionalRentalhouse,"Rentalhouse","id",resources.getRentId());
        Rentalhouse Rentalhouse = optionalRentalhouse.get();
        Rentalhouse.copy(resources);
        RentalhouseRepository.save(Rentalhouse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String rentId) {
        RentalhouseRepository.deleteById(rentId);
    }
}