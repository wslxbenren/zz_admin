package com.xyz.modules.biz.service.impl;

import cn.hutool.db.sql.Direction;
import com.xyz.modules.biz.domain.ManageleadresponsInfo;
import com.xyz.modules.biz.service.dto.ManagecenterInfoDTO;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DictDetailRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.ManageleadresponsInfoRepository;
import com.xyz.modules.biz.service.ManageleadresponsInfoService;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoDTO;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ManageleadresponsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

/**
* @author xjh
* @date 2020-04-05
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ManageleadresponsInfoServiceImpl implements ManageleadresponsInfoService {

    @Autowired
    private ManageleadresponsInfoRepository ManageleadresponsInfoRepository;

    @Autowired
    private ManageleadresponsInfoMapper ManageleadresponsInfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Override
    public Object queryAll(ManageleadresponsInfoQueryCriteria criteria, Pageable pageable){
        //Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        //pageable.getSortOr(sort);
        Page<ManageleadresponsInfo> page = ManageleadresponsInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<ManageleadresponsInfoDTO> manageleadresponsInfoList = ManageleadresponsInfoMapper.toDto(page.getContent());
        for (ManageleadresponsInfoDTO mid: manageleadresponsInfoList) {
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.JGCJ.getDistName(), mid.getAreaGrage());
            mid.setAreaGrageStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.JGCJ.getDistName(), mid.getImplementerGrage());
            mid.setImplementerGrageStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ZCZL.getDistName(), mid.getPolicyType());
            mid.setPolicyTypeStr(dd == null ? "无数据":dd.getLabel());
        }
        Map map = new HashMap();
        map.put("content", manageleadresponsInfoList);
        map.put("totalElements", page.getTotalPages());
        return map;

    }

    @Override
    public Object queryAll(ManageleadresponsInfoQueryCriteria criteria){
        return ManageleadresponsInfoMapper.toDto(ManageleadresponsInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ManageleadresponsInfoDTO findById(String id) {
        Optional<ManageleadresponsInfo> ManageleadresponsInfo = ManageleadresponsInfoRepository.findById(id);
        ValidationUtil.isNull(ManageleadresponsInfo,"ManageleadresponsInfo","id",id);
        return ManageleadresponsInfoMapper.toDto(ManageleadresponsInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ManageleadresponsInfoDTO create(ManageleadresponsInfo resources) {
        resources.setId(IdUtil.simpleUUID());
        resources.setCreateTime(new Timestamp(new Date().getTime()));
        return ManageleadresponsInfoMapper.toDto(ManageleadresponsInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ManageleadresponsInfo resources) {
        resources.setUpdateTime(new Timestamp(new Date().getTime()));
        Optional<ManageleadresponsInfo> optionalManageleadresponsInfo = ManageleadresponsInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalManageleadresponsInfo,"ManageleadresponsInfo","id",resources.getId());
        ManageleadresponsInfo ManageleadresponsInfo = optionalManageleadresponsInfo.get();
        ManageleadresponsInfo.copy(resources);
        ManageleadresponsInfoRepository.save(ManageleadresponsInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        ManageleadresponsInfoRepository.deleteById(id);
    }
}