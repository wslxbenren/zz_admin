package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.ManagecenterInfo;
import com.xyz.modules.system.domain.Dict;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DictDetailRepository;
import com.xyz.modules.system.repository.DictRepository;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.ManagecenterInfoRepository;
import com.xyz.modules.biz.service.ManagecenterInfoService;
import com.xyz.modules.biz.service.dto.ManagecenterInfoDTO;
import com.xyz.modules.biz.service.dto.ManagecenterInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ManagecenterInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

/**
* @author dadovicn
* @date 2020-04-05
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ManagecenterInfoServiceImpl implements ManagecenterInfoService {

    @Autowired
    private ManagecenterInfoRepository ManagecenterInfoRepository;

    @Autowired
    private ManagecenterInfoMapper ManagecenterInfoMapper;

    @Autowired
    DictDetailRepository dictDetailRepository;
    @Autowired
    DictRepository dictRepository;
    @Override
    public Object queryAll(ManagecenterInfoQueryCriteria criteria, Pageable pageable){
        Page<ManagecenterInfo> page = ManagecenterInfoRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        Dict jgcj = dictRepository.findByName(DictEnum.JGCJ.getDistName());// 机构层级
        Dict address = dictRepository.findByName(DictEnum.ADDRESS.getDistName());// 地区
        List<DictDetail> grageList = dictDetailRepository.findByDictId(jgcj.getId());
        List<DictDetail> addrList = dictDetailRepository.findByDictId(address.getId());
        List<ManagecenterInfoDTO> midList = ManagecenterInfoMapper.toDto(page.getContent());
        for (ManagecenterInfoDTO mid:midList ) {
            Stream<DictDetail> detailStream= null;
            detailStream = grageList.stream().filter(d -> {
                return d.getValue().equals(mid.getGrage());
            });
            List<DictDetail> collect = detailStream.collect(Collectors.toList());
            mid.setGrageString( collect.size() == 0 ? "无数据":collect.get(0).getLabel());
            detailStream = addrList.stream().filter(d -> {
                return d.getValue().equals(mid.getAddr());
            });
            collect = detailStream.collect(Collectors.toList());
            mid.setAddrString(collect.size() == 0 ? "无数据":collect.get(0).getLabel());
        }
        Map map = new HashMap();
        map.put("content", midList);
        map.put("totalElements", page.getTotalPages());
        return map;
    }

    @Override
    public Object queryAll(ManagecenterInfoQueryCriteria criteria){
        return ManagecenterInfoMapper.toDto(ManagecenterInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ManagecenterInfoDTO findById(String id) {
        Optional<ManagecenterInfo> ManagecenterInfo = ManagecenterInfoRepository.findById(id);
        ValidationUtil.isNull(ManagecenterInfo,"ManagecenterInfo","id",id);
        return ManagecenterInfoMapper.toDto(ManagecenterInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ManagecenterInfoDTO create(ManagecenterInfo resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return ManagecenterInfoMapper.toDto(ManagecenterInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ManagecenterInfo resources) {
        Optional<ManagecenterInfo> optionalManagecenterInfo = ManagecenterInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalManagecenterInfo,"ManagecenterInfo","id",resources.getId());
        ManagecenterInfo ManagecenterInfo = optionalManagecenterInfo.get();
        ManagecenterInfo.copy(resources);
        ManagecenterInfoRepository.save(ManagecenterInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        ManagecenterInfoRepository.deleteById(id);
    }


}