package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Suspectinfo;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.SuspectinfoRepository;
import com.xyz.modules.biz.service.SuspectinfoService;
import com.xyz.modules.biz.service.dto.SuspectinfoDTO;
import com.xyz.modules.biz.service.dto.SuspectinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.SuspectinfoMapper;
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
public class SuspectinfoServiceImpl implements SuspectinfoService {

    @Autowired
    private SuspectinfoRepository SuspectinfoRepository;

    @Autowired
    private SuspectinfoMapper SuspectinfoMapper;

    @Override
    @Transactional
    public Object queryAll(SuspectinfoQueryCriteria criteria, Pageable pageable){
        Page<Suspectinfo> page = SuspectinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(SuspectinfoMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(SuspectinfoQueryCriteria criteria){
        return SuspectinfoMapper.toDto(SuspectinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public SuspectinfoDTO findById(String suspId) {
        Optional<Suspectinfo> Suspectinfo = SuspectinfoRepository.findById(suspId);
        ValidationUtil.isNull(Suspectinfo,"Suspectinfo","suspId",suspId);
        return SuspectinfoMapper.toDto(Suspectinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SuspectinfoDTO create(Suspectinfo resources) {
        resources.setSuspId(IdUtil.simpleUUID()); 
        return SuspectinfoMapper.toDto(SuspectinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Suspectinfo resources) {
        Optional<Suspectinfo> optionalSuspectinfo = SuspectinfoRepository.findById(resources.getSuspId());
        ValidationUtil.isNull( optionalSuspectinfo,"Suspectinfo","id",resources.getSuspId());
        Suspectinfo Suspectinfo = optionalSuspectinfo.get();
        Suspectinfo.copy(resources);
        SuspectinfoRepository.save(Suspectinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String suspId) {
        SuspectinfoRepository.deleteById(suspId);
    }
}