package com.xyz.modules.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.mapper.DeptMapper;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.system.domain.Dept;
import com.xyz.modules.system.service.dto.DeptQueryCriteria;
import com.xyz.modules.system.service.DeptService;
import com.xyz.modules.system.service.dto.DeptDTO;
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
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<DeptDTO> queryAll(DeptQueryCriteria criteria) {
        return deptMapper.toDto(deptRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DeptDTO findById(String id) {
        Optional<Dept> dept = deptRepository.findById(id);
        ValidationUtil.isNull(dept,"Dept","id",id);
        return deptMapper.toDto(dept.get());
    }

    @Override
    public List<Dept> findByPid(String pid) {
        return deptRepository.findByPid(pid);
    }

    @Override
    public Set<Dept> findByRoleIds(String id) {
        return deptRepository.findByRoles_Id(id);
    }

    @Override
    public Object buildTree(List<DeptDTO> deptDTOS) {
        Set<DeptDTO> trees = new LinkedHashSet<>();
        Set<DeptDTO> depts= new LinkedHashSet<>();
        List<String> deptNames = deptDTOS.stream().map(DeptDTO::getName).collect(Collectors.toList());
        Boolean isChild;
        for (DeptDTO deptDTO : deptDTOS) {
            isChild = false;
            if ("0".equals(deptDTO.getPid().toString())) {
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
            if(isChild)
                depts.add(deptDTO);
            else if(!deptNames.contains(deptRepository.findNameById(deptDTO.getPid())))
                depts.add(deptDTO);
        }

        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }

        Integer totalElements = deptDTOS!=null?deptDTOS.size():0;

        Map map = new HashMap();
        map.put("totalElements",totalElements);
        map.put("content",CollectionUtils.isEmpty(trees)?deptDTOS:trees);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeptDTO create(Dept resources) {
        return deptMapper.toDto(deptRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Dept resources) {
        if(resources.getId().equals(resources.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        Optional<Dept> optionalDept = deptRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalDept,"Dept","id",resources.getId());
        Dept dept = optionalDept.get();
        resources.setId(dept.getId());
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
    public List<String> getDownGradeDeptCodes(String code) {
        deptRepository.getChildList(code);
        return deptRepository.getDeptDownGradeCodes();

    }

    private static void tree2list(Dept root, List<String> list){
        if (root == null) {
            return ;
        }
        list.add(root.getCode());
        if (root.getDepts()==null){
            return;
        }
        for (Dept sub:root.getDepts()){
            tree2list(sub, list);
        }
    }
}