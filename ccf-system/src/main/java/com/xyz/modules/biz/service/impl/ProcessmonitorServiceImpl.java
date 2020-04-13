package com.xyz.modules.biz.service.impl;

import com.xyz.modules.biz.domain.Processmonitor;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.ProcessmonitorRepository;
import com.xyz.modules.biz.service.ProcessmonitorService;
import com.xyz.modules.biz.service.dto.ProcessmonitorDTO;
import com.xyz.modules.biz.service.dto.ProcessmonitorQueryCriteria;
import com.xyz.modules.biz.service.mapper.ProcessmonitorMapper;
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
public class ProcessmonitorServiceImpl implements ProcessmonitorService {

    @Autowired
    private ProcessmonitorRepository ProcessmonitorRepository;

    @Autowired
    private ProcessmonitorMapper ProcessmonitorMapper;

    @Override
    @Transactional
    public Object queryAll(ProcessmonitorQueryCriteria criteria, Pageable pageable){
        Page<Processmonitor> page = ProcessmonitorRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ProcessmonitorMapper::toDto));
    }

    @Override
    @Transactional
    public Object queryAll(ProcessmonitorQueryCriteria criteria){
        return ProcessmonitorMapper.toDto(ProcessmonitorRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ProcessmonitorDTO findById(String processId) {
        Optional<Processmonitor> Processmonitor = ProcessmonitorRepository.findById(processId);
        ValidationUtil.isNull(Processmonitor,"Processmonitor","processId",processId);
        return ProcessmonitorMapper.toDto(Processmonitor.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessmonitorDTO create(Processmonitor resources) {
        resources.setProcessId(IdUtil.simpleUUID()); 
        return ProcessmonitorMapper.toDto(ProcessmonitorRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Processmonitor resources) {
        Optional<Processmonitor> optionalProcessmonitor = ProcessmonitorRepository.findById(resources.getProcessId());
        ValidationUtil.isNull( optionalProcessmonitor,"Processmonitor","id",resources.getProcessId());
        Processmonitor Processmonitor = optionalProcessmonitor.get();
        Processmonitor.copy(resources);
        ProcessmonitorRepository.save(Processmonitor);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String processId) {
        ProcessmonitorRepository.deleteById(processId);
    }
}