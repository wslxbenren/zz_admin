package com.xyz.modules.biz.service.impl;

import com.xyz.exception.BadRequestException;
import com.xyz.modules.biz.domain.Caseinfo;
import com.xyz.modules.biz.service.dto.ConvenientinfoDTO;
import com.xyz.modules.biz.service.strategy.AuditSpecification;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.StringUtils;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.biz.repository.CaseinfoRepository;
import com.xyz.modules.biz.service.CaseinfoService;
import com.xyz.modules.biz.service.dto.CaseinfoDTO;
import com.xyz.modules.biz.service.dto.CaseinfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.CaseinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
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
 * @author 邢家华
 * @date 2020-04-10
 * 功能模块：护路护线/涉线、路案事件信息管理
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CaseinfoServiceImpl implements CaseinfoService {

    @Autowired
    private CaseinfoRepository CaseinfoRepository;

    @Autowired
    private CaseinfoMapper CaseinfoMapper;

    @Autowired
    private DictDetailService dictDetailService;

    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    @Transactional
    public Object queryAll(CaseinfoQueryCriteria criteria, Pageable pageable){
        log.info("查询列表护路护线/涉线、路案事件信息管理--开始");
        Page<Caseinfo> page = CaseinfoRepository.findAll(audit.genSpecification(criteria),pageable);
        List<CaseinfoDTO> caseinfoDTOList = CaseinfoMapper.toDto(page.getContent());
        for (CaseinfoDTO f:caseinfoDTOList){
            String dd = dictDetailService.transDict(DictEnum.AJLX.getDistName(), f.getCaseType());
            f.setCaseTypeStr(dd == null ? "无数据" : dd);// 案（事）件类型
            dd = dictDetailService.transDict(DictEnum.ZJDM.getDistName(), f.getPrinccardType());
            f.setPrinccardTypeStr(dd == null ? "无数据" : dd);//主犯（嫌疑人）证件代码

            dd = deptRepository.findNameByCode(f.getUnitCode());
            f.setUnitCodeStr(dd);
        }
        Map map = new HashMap();
        map.put("content", caseinfoDTOList);
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages",page.getTotalPages());
        return map;
    }

    @Override
    @Transactional
    public Object queryAll(CaseinfoQueryCriteria criteria){
        return CaseinfoMapper.toDto(CaseinfoRepository.findAll(audit.genSpecification(criteria)));
    }

    @Override
    public CaseinfoDTO findById(String caseId) {
        log.info("详情护路护线/涉线、路案事件信息管理--开始");
        if (StringUtils.isBlank(caseId)){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Caseinfo> Caseinfo = CaseinfoRepository.findById(caseId);
        ValidationUtil.isNull(Caseinfo,"Caseinfo","caseId",caseId);
        return CaseinfoMapper.toDto(Caseinfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CaseinfoDTO create(Caseinfo resources) {
        log.info("新增护路护线/涉线、路案事件信息管理--开始");
        resources.setCaseId(IdUtil.simpleUUID()); 
        return CaseinfoMapper.toDto(CaseinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Caseinfo resources) {
        log.info("修改护路护线/涉线、路案事件信息管理--开始");
        if (StringUtils.isBlank(resources.getCaseId())){
            throw new BadRequestException("主键ID不能为空");
        }
        Optional<Caseinfo> optionalCaseinfo = CaseinfoRepository.findById(resources.getCaseId());
        ValidationUtil.isNull( optionalCaseinfo,"Caseinfo","id",resources.getCaseId());
        Caseinfo Caseinfo = optionalCaseinfo.get();
        Caseinfo.copy(resources);
        CaseinfoRepository.save(Caseinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String caseId) {
        log.info("删除护路护线/涉线、路案事件信息管理--开始");
        if (StringUtils.isBlank(caseId)){
            throw new BadRequestException("主键ID不能为空");
        }
        CaseinfoRepository.deleteById(caseId);
    }
}