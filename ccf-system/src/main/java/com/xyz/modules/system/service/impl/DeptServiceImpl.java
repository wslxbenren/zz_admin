package com.xyz.modules.system.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.system.domain.Dept;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.service.dto.DeptDTO;
import com.xyz.modules.system.service.dto.DeptQueryCriteria;
import com.xyz.modules.system.service.mapper.DeptMapper;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Zheng Jie
 * @date 2019-03-25
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptMapper deptMapper;



    @Autowired
    private DictDetailService dictDetailService;


    @Override
    @Transactional
    public List<DeptDTO> queryAll(DeptQueryCriteria criteria) {
        log.info("单位信息--开始");
        List<DeptDTO> deptDTOList = deptMapper.toDto(deptRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
        for (DeptDTO f : deptDTOList) {
            String dd = dictDetailService.transDict(DictEnum.JGLX.getDistName(), f.getInstiType());
            f.setInstiTypeStr(dd == null ? "无数据" : dd);// 机构类型
            dd = dictDetailService.transDict(DictEnum.SHZZLX.getDistName(), f.getOrganType());
            f.setOrganTypeStr(dd == null ? "无数据" : dd);// 组织类型

            dd = deptRepository.findNameByCode(f.getGuideUnit());
            f.setGuideUnit(dd);//所属单位
        }
        return deptDTOList;
    }

    @Override
    public List<DeptDTO> queryWithoutDict(DeptQueryCriteria criteria) {
        return deptMapper.toDto(deptRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public DeptDTO findById(String id) {
        Optional<Dept> dept = deptRepository.findById(id);
        ValidationUtil.isNull(dept, "Dept", "id", id);
        return deptMapper.toDto(dept.get());
    }

    @Override
    public List<Dept> findByPid(String pid) {
        return deptRepository.findByPid(pid);
    }

    @Override
    public Set<Dept> findByRoleIds(Long id) {
        return deptRepository.findByRoles_Id(id);
    }

    @Override
    public Object buildTree(List<DeptDTO> deptDTOS) {
        Set<DeptDTO> trees = new LinkedHashSet<>();
        Set<DeptDTO> depts = new LinkedHashSet<>();
        List<String> deptNames = deptDTOS.stream().map(DeptDTO::getName).collect(Collectors.toList());
        Boolean isChild;
        for (DeptDTO deptDTO : deptDTOS) {
            isChild = false;
            if ("-1".equals(deptDTO.getPid().toString())) {
                trees.add(deptDTO);
            }
            for (DeptDTO it : deptDTOS) {
                if (it.getPid().equals(deptDTO.getId())) {
                    isChild = true;
                    if (deptDTO.getChildren() == null) {
                        deptDTO.setChildren(new ArrayList<DeptDTO>());
                    }
                    deptDTO.getChildren().add(it);
                }
            }
            if (isChild)
                depts.add(deptDTO);
            else if (!deptNames.contains(deptRepository.findNameById(deptDTO.getPid())))
                depts.add(deptDTO);
        }

        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }

        Integer totalElements = deptDTOS != null ? deptDTOS.size() : 0;

        Map map = new HashMap();
        map.put("totalElements", totalElements);
        map.put("content", CollectionUtils.isEmpty(trees) ? deptDTOS : trees);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeptDTO create(Dept resources) {
        resources.setId(IdUtil.simpleUUID());
//        resources.setCreator(SecurityUtils.getUsername());
//        resources.setModifier(SecurityUtils.getUsername());
        return deptMapper.toDto(deptRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Dept resources) {
        if (resources.getId().equals(resources.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        Optional<Dept> optionalDept = deptRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalDept, "Dept", "id", resources.getId());
        Dept dept = optionalDept.get();
        resources.setId(dept.getId());
        resources.setModifier(SecurityUtils.getUsername());
        deptRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        deptRepository.deleteById(id);
    }


    @Override
    public List<Dept> getHttpDeptList(String thirdld, String lastTime, String pageNo, String pageSize) {

        List<Dept> deptList = new ArrayList<>();
        String url = "http://62.64.11.7:9010/pams/sso/basicinfosynchronizecontrol/departmentinfosyn.do";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("thirdld", thirdld);
        map.add("lastTime", lastTime);
        map.add("pageNo", pageNo);
        map.add("pageSize", pageSize);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);


        JSONObject body = JSONObject.parseObject(response.getBody());
        String list = body.getString("list");
        deptList = JSONObject.parseArray(list, Dept.class);

        return deptList;
    }


    @Override
    @Transactional
    public int saveDeptIncrement() {
        long currentTime = System.currentTimeMillis();
        String lastTime = String.valueOf(currentTime);

        List<Dept> zzDeptList = this.getHttpDeptList("htxf", lastTime, "1", "200");
        if (zzDeptList.size() > 0) {
            for (Dept dept : zzDeptList) {//
                dept.setEnabled(true);
                dept.setPid(dept.getParentId());
                deptRepository.save(dept);
            }
        }
        return zzDeptList.size();
    }

    @Override
    @Transactional
    public List<String> getDownGradeDeptCodes(String code) {
        String res = deptRepository.getDeptDownGradeCodes(code);
        if(res.length() > 2) {
            String tmp = res.substring(0, res.length() - 2);
            return CollectionUtils.arrayToList(tmp.split(","));
        }
        return Collections.emptyList();
    }

    @Override
    public String getGradeByCode(String code) {
        return deptRepository.getDeptDownGradeCodes(code);
    }
}