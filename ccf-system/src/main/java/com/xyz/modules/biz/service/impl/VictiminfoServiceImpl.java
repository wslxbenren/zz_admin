package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Victiminfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.VictiminfoRepository;
import com.xyz.modules.biz.service.VictiminfoService;
import com.xyz.modules.biz.service.dto.VictiminfoDTO;
import com.xyz.modules.biz.service.dto.VictiminfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.VictiminfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;

/**
 * @author 邢家华
 * @date 2020-04-10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class VictiminfoServiceImpl implements VictiminfoService {

    @Autowired
    private VictiminfoRepository VictiminfoRepository;

    @Autowired
    private VictiminfoMapper VictiminfoMapper;

    @Override
    public Object queryAll(VictiminfoQueryCriteria criteria, Pageable pageable){
        Page<Victiminfo> page = VictiminfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(VictiminfoMapper::toDto));
    }

    @Override
    public Object queryAll(VictiminfoQueryCriteria criteria){
        return VictiminfoMapper.toDto(VictiminfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public VictiminfoDTO findById(String vicId) {
        Optional<Victiminfo> Victiminfo = VictiminfoRepository.findById(vicId);
        ValidationUtil.isNull(Victiminfo,"Victiminfo","vicId",vicId);
        return VictiminfoMapper.toDto(Victiminfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VictiminfoDTO create(Victiminfo resources) {
        resources.setVicId(IdUtil.simpleUUID()); 
        return VictiminfoMapper.toDto(VictiminfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Victiminfo resources) {
        Optional<Victiminfo> optionalVictiminfo = VictiminfoRepository.findById(resources.getVicId());
        ValidationUtil.isNull( optionalVictiminfo,"Victiminfo","id",resources.getVicId());
        Victiminfo Victiminfo = optionalVictiminfo.get();
        Victiminfo.copy(resources);
        VictiminfoRepository.save(Victiminfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String vicId) {
        VictiminfoRepository.deleteById(vicId);
    }
}