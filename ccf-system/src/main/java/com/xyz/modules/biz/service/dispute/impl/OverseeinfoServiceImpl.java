package com.xyz.modules.biz.service.dispute.impl;

import com.xyz.modules.biz.service.dispute.entity.Overseeinfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.service.dispute.repo.OverseeinfoRepository;
import com.xyz.modules.biz.service.dispute.OverseeinfoService;
import com.xyz.modules.biz.service.dispute.dto.OverseeinfoDTO;
import com.xyz.modules.biz.service.dispute.qo.OverseeinfoQueryCriteria;
import com.xyz.modules.biz.service.dispute.mapper.OverseeinfoMapper;
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
public class OverseeinfoServiceImpl implements OverseeinfoService {

    @Autowired
    private OverseeinfoRepository OverseeinfoRepository;

    @Autowired
    private OverseeinfoMapper OverseeinfoMapper;

    @Override
    @Transactional
    public Object queryAll(OverseeinfoQueryCriteria criteria, Pageable pageable){
        Page<Overseeinfo> page = OverseeinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(OverseeinfoMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(OverseeinfoQueryCriteria criteria){
        return OverseeinfoMapper.toDto(OverseeinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public OverseeinfoDTO findById(String overseeId) {
        Optional<Overseeinfo> Overseeinfo = OverseeinfoRepository.findById(overseeId);
        ValidationUtil.isNull(Overseeinfo,"Overseeinfo","overseeId",overseeId);
        return OverseeinfoMapper.toDto(Overseeinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OverseeinfoDTO create(Overseeinfo resources) {
        resources.setOverseeId(IdUtil.simpleUUID()); 
        return OverseeinfoMapper.toDto(OverseeinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Overseeinfo resources) {
        Optional<Overseeinfo> optionalOverseeinfo = OverseeinfoRepository.findById(resources.getOverseeId());
        ValidationUtil.isNull( optionalOverseeinfo,"Overseeinfo","id",resources.getOverseeId());
        Overseeinfo Overseeinfo = optionalOverseeinfo.get();
        Overseeinfo.copy(resources);
        OverseeinfoRepository.save(Overseeinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String overseeId) {
        OverseeinfoRepository.deleteById(overseeId);
    }
}