package com.xyz.biz;

import com.xyz.modules.biz.domain.ManagecenterInfo;
import com.xyz.modules.biz.repository.ManagecenterInfoRepository;
import com.xyz.modules.biz.service.dto.ManagecenterInfoDTO;
import com.xyz.modules.biz.service.dto.ManagecenterInfoQueryCriteria;
import com.xyz.modules.biz.service.mapper.ManagecenterInfoMapper;
import com.xyz.modules.system.domain.DictDetail;
import com.xyz.modules.system.repository.DictDetailRepository;
import com.xyz.modules.system.repository.DictRepository;
import com.xyz.utils.QueryHelp;
import com.xyz.utils.ValidationUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageCenterApplicationTests {
    @Autowired
    ManagecenterInfoMapper managecenterInfoMapper;
    @Autowired
    ManagecenterInfoRepository managecenterInfoRepository;
    @Autowired
    DictRepository dictRepository;
    @Autowired
    DictDetailRepository dictDetailRepository;
    @Test
    public void contextLoads() {
    }
    @Test
    public void testAdd() {
        ManagecenterInfo managecenterInfo = new ManagecenterInfo();
        managecenterInfo.setId(UUID.randomUUID().toString());
        managecenterInfo.setAddr("1");
        managecenterInfo.setAddrDetail("2");
        managecenterInfo.setCenterCode("测试添加");
        managecenterInfo.setCenterMobile("0");
        managecenterInfo.setCenterName("0");
        managecenterInfo.setDeptId("0");
        managecenterInfo.setGrage("1");
        managecenterInfo.setLat(1.1d);
        managecenterInfo.setLng(1.1d);
        managecenterInfo.setUnitCode("0");
        managecenterInfo.setUsername("0");
        managecenterInfo.setUserMobile("0");
        managecenterInfo.setModifier("0");
        managecenterInfo.setUsercode("0");
        managecenterInfo.setCenterName("0");
        managecenterInfo.setCreator("0");
        managecenterInfoRepository.save(managecenterInfo);
    }
    @Test
    public void testUpdate() {
        ManagecenterInfo managecenterInfo = new ManagecenterInfo();
        managecenterInfo.setId("8a93755d-1148-47e9-bf04-270d0d35c4dc");
        managecenterInfo.setAddr("1");
        managecenterInfo.setCreator("0");
        managecenterInfo.setAddrDetail("2");
        managecenterInfo.setCenterCode("测试更新");
        managecenterInfo.setCenterMobile("0");
        managecenterInfo.setCenterName("0");
        managecenterInfo.setDeptId("0");
        managecenterInfo.setCreateTime(new Timestamp(new Date().getTime()));
        managecenterInfo.setGrage("1");
        managecenterInfo.setLat(1.1d);
        managecenterInfo.setLng(1.1d);
        managecenterInfo.setUnitCode("0");
        managecenterInfo.setUsername("0");
        managecenterInfo.setUserMobile("0");
        managecenterInfo.setModifier("0");
        managecenterInfo.setUsercode("0");
        managecenterInfo.setCenterName("0");
        managecenterInfo.setUpdateTime(new Timestamp(new Date().getTime()));
        managecenterInfoRepository.saveAndFlush(managecenterInfo);
    }
    @Test
    public void testDelete() {
        ManagecenterInfo managecenterInfo = new ManagecenterInfo();
        managecenterInfo.setId("09f6bd80-3ca1-48e0-a45e-245285f99016");
        managecenterInfoRepository.delete(managecenterInfo);
    }
    @Test
    public void testFind() {
        Optional<ManagecenterInfo> byId = managecenterInfoRepository.findById("aa4feaa8-556e-49dc-bc70-7ac9ddc14e2c");
        ValidationUtil.isNull(byId,"ManagecenterInfo","id","aa4feaa8-556e-49dc-bc70-7ac9ddc14e2c");
        ManagecenterInfoDTO managecenterInfoDTO = managecenterInfoMapper.toDto(byId.get());
        System.out.println(managecenterInfoDTO.getCreateTime());
        ManagecenterInfoQueryCriteria criteria = new ManagecenterInfoQueryCriteria();
        criteria.setCreateTime(Arrays.asList("2020-04-09","2020-04-15"));
        criteria.setUpdateTime(Arrays.asList("2020-04-09","2020-04-15"));
        criteria.setAddr("sdd");
        criteria.setGrage("0");
        criteria.setCenterCode("1");
        criteria.setDeptId("22");
        criteria.setCenterName("ccc");
        criteria.setUsername("");

        List list = managecenterInfoMapper.toDto(managecenterInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));

        List<DictDetail> grageList = dictDetailRepository.findByDictId(202L);
        List<DictDetail> addrList = dictDetailRepository.findByDictId(106L);
        List<ManagecenterInfoDTO> midList = list;
        for (ManagecenterInfoDTO mid:midList ) {
            Stream<DictDetail> detailStream= null;
            detailStream = grageList.stream().filter(d -> {
                return d.getValue().equals(mid.getGrage());
            });
            List<DictDetail> collect = detailStream.collect(Collectors.toList());
            mid.setGrageStr( collect.size() == 0 ? "无数据":collect.get(0).getLabel());

             detailStream = addrList.stream().filter(d -> {
                return d.getValue().equals(mid.getAddr());
            });
            collect = detailStream.collect(Collectors.toList());
            mid.setAddrStr(collect.size() == 0 ? "无数据":collect.get(0).getLabel());
        }
        System.out.println(midList);
    }
}

