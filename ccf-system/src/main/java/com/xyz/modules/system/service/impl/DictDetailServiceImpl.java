package com.xyz.modules.system.service.impl;

import com.xyz.modules.system.repository.DictDetailRepository;
import com.xyz.modules.system.repository.DictRepository;
import com.xyz.modules.system.service.mapper.DictDetailMapper;
import com.xyz.utils.PageUtil;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.service.dto.DictDetailQueryCriteria;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.service.dto.DictDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictDetailServiceImpl implements DictDetailService {

    @Autowired
    private DictDetailRepository dictDetailRepository;

    @Autowired
    private DictDetailMapper dictDetailMapper;

    @Override
    public Map queryAll(DictDetailQueryCriteria criteria, Pageable pageable) {
        Page<DictDetail> page = dictDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(dictDetailMapper::toDto));
    }

    public List<DictDetail> queryAll(DictDetailQueryCriteria criteria){
        return dictDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder));
    }

    @Override
    public DictDetail findByValueAndPName(String pName, String value) {
        return dictDetailRepository.findByValueAndPName(pName, value);
    }

    @Override
    public String transDict(String pName, String value) {
        String label = dictDetailRepository.transDict(pName, value);
        return  label == null ? "无数据": label;
    }

    @Override
    public String transDict(long dictTypeId, String value) {
        String label = dictDetailRepository.transDict(dictTypeId, value);
        return  label == null ? "无数据": label;
    }

    @Override
    public DictDetailDTO findById(Long id) {
        Optional<DictDetail> dictDetail = dictDetailRepository.findById(id);
        ValidationUtil.isNull(dictDetail,"DictDetail","id",id);
        return dictDetailMapper.toDto(dictDetail.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictDetailDTO create(DictDetail resources) {
        return dictDetailMapper.toDto(dictDetailRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DictDetail resources) {
        Optional<DictDetail> optionalDictDetail = dictDetailRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalDictDetail,"DictDetail","id",resources.getId());
        DictDetail dictDetail = optionalDictDetail.get();
        resources.setId(dictDetail.getId());
        dictDetailRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        dictDetailRepository.deleteById(id);
    }

    @Override
    public String transMultistage(long dictTypeId, String dictDetailValue) {
        return dictDetailRepository.getAddrParentList(dictTypeId, dictDetailValue);
    }

    @Override
    public String getLabelByValues(long dictId,   String[]  joinManager) {
        List<String> list = Arrays.asList(joinManager);
        return dictDetailRepository.getLabelByValues(dictId,list);
    }
}