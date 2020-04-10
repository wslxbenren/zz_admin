package com.xyz.modules.biz.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xyz.modules.biz.domain.ManagecenterInfo;
import com.xyz.modules.biz.repository.ManagecenterInfoRepository;
import com.xyz.modules.biz.service.ManagecenterInfoService;
import com.xyz.modules.biz.service.dto.ManagecenterInfoDTO;
import com.xyz.modules.biz.service.dto.ManagecenterInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ManagecenterInfoMapper;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.system.domain.Dict;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DictDetailRepository;
import com.xyz.modules.system.repository.DictRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author lx
* @date 2020-04-07
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ManagecenterInfoServiceImpl implements ManagecenterInfoService {

    @Autowired
    private ManagecenterInfoRepository ManagecenterInfoRepository;

    @Autowired
    private ManagecenterInfoMapper ManagecenterInfoMapper;

    @Autowired
    private DictDetailRepository dictDetailRepository;

    @Autowired
    private DictRepository dictRepository;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Object queryAll(ManagecenterInfoQueryCriteria criteria, Pageable pageable){
        Page<ManagecenterInfo> page = ManagecenterInfoRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<ManagecenterInfoDTO> midList = ManagecenterInfoMapper.toDto(page.getContent());
        for (ManagecenterInfoDTO mid:midList ) {
            DictDetail dd = dictDetailService.findByValueAndPName(DictEnum.JGCJ.getDistName(), mid.getGrage());
            mid.setGrageStr(dd == null ? "无数据":dd.getLabel());
            dd = dictDetailService.findByValueAndPName(DictEnum.ADDRESS.getDistName(), mid.getAddr());
            mid.setAddr(dd == null ? "无数据":dd.getLabel());
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
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setCreator(u.getId());
        return ManagecenterInfoMapper.toDto(ManagecenterInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ManagecenterInfo resources) {
        Optional<ManagecenterInfo> optionalManagecenterInfo = ManagecenterInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalManagecenterInfo,"ManagecenterInfo","id",resources.getId());
        ManagecenterInfo ManagecenterInfo = optionalManagecenterInfo.get();
        JwtUser u = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        resources.setCreator(u.getId());
        ManagecenterInfo.copy(resources);
        ManagecenterInfoRepository.save(ManagecenterInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        ManagecenterInfoRepository.deleteById(id);
    }
}