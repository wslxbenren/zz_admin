package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Resolvinginfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.ResolvinginfoRepository;
import com.xyz.modules.biz.service.ResolvinginfoService;
import com.xyz.modules.biz.service.dto.ResolvinginfoDTO;
import com.xyz.modules.biz.service.dto.ResolvinginfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ResolvinginfoMapper;
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
public class ResolvinginfoServiceImpl implements ResolvinginfoService {

    @Autowired
    private ResolvinginfoRepository ResolvinginfoRepository;

    @Autowired
    private ResolvinginfoMapper ResolvinginfoMapper;

    @Override
    @Transactional
    public Object queryAll(ResolvinginfoQueryCriteria criteria, Pageable pageable){
        Page<Resolvinginfo> page = ResolvinginfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ResolvinginfoMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(ResolvinginfoQueryCriteria criteria){
        return ResolvinginfoMapper.toDto(ResolvinginfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ResolvinginfoDTO findById(String resolvId) {
        Optional<Resolvinginfo> Resolvinginfo = ResolvinginfoRepository.findById(resolvId);
        ValidationUtil.isNull(Resolvinginfo,"Resolvinginfo","resolvId",resolvId);
        return ResolvinginfoMapper.toDto(Resolvinginfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResolvinginfoDTO create(Resolvinginfo resources) {
        resources.setResolvId(IdUtil.simpleUUID()); 
        return ResolvinginfoMapper.toDto(ResolvinginfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Resolvinginfo resources) {
        Optional<Resolvinginfo> optionalResolvinginfo = ResolvinginfoRepository.findById(resources.getResolvId());
        ValidationUtil.isNull( optionalResolvinginfo,"Resolvinginfo","id",resources.getResolvId());
        Resolvinginfo Resolvinginfo = optionalResolvinginfo.get();
        Resolvinginfo.copy(resources);
        ResolvinginfoRepository.save(Resolvinginfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String resolvId) {
        ResolvinginfoRepository.deleteById(resolvId);
    }
}