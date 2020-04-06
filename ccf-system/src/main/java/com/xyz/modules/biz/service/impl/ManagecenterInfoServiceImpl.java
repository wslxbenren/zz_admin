package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.ManagecenterInfo;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DictDetailRepository;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.ManagecenterInfoRepository;
import com.xyz.modules.biz.service.ManagecenterInfoService;
import com.xyz.modules.biz.service.dto.ManagecenterInfoDTO;
import com.xyz.modules.biz.service.dto.ManagecenterInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ManagecenterInfoMapper;
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

    @Override
    public Object queryAll(ManagecenterInfoQueryCriteria criteria, Pageable pageable){
        Page<ManagecenterInfo> page = ManagecenterInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<DictDetail> grageList = dictDetailRepository.findByDictId(202L);//前端传父级字典ID
        List<DictDetail> addrList = dictDetailRepository.findByDictId(106L);//前端传父级字典ID
        List<ManagecenterInfoDTO> midList = ManagecenterInfoMapper.toDto(page.getContent());
        for (ManagecenterInfoDTO mid:midList ) {
            for (int i = 0; i < grageList.size(); i++) {
                if (mid.getGrage().equals(grageList.get(i).getValue())){
                    mid.setGrageString(grageList.get(i).getLabel());
                    break;
                }
            }
            for (int i = 0; i < addrList.size(); i++) {
                if (mid.getGrage().equals(grageList.get(i).getValue())){
                    mid.setAddrString(addrList.get(i).getLabel());
                    break;
                }
            }
        }
        Map map = new HashMap();
        map.put("content", midList);
        map.put("totalElements", midList.size());
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