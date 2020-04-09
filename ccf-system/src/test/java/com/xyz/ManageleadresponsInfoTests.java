package com.xyz;


import com.xyz.modules.biz.domain.ManageleadresponsInfo;
import com.xyz.modules.biz.repository.ManageleadresponsInfoRepository;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoQueryCriteria;
import com.xyz.modules.biz.service.impl.ManageleadresponsInfoServiceImpl;
import com.xyz.modules.biz.service.mapper.ManageleadresponsInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageleadresponsInfoTests {

    @Autowired
    private ManageleadresponsInfoServiceImpl manageleadresponsInfoServiceImpl;

    @Test
    public void manageleadresponsInfoAdd() throws Exception {
        //实体类
        ManageleadresponsInfo info = new ManageleadresponsInfo();
        info.setAreaGrage("1");
        info.setCreator("xjh");
        info.setImpledareaCode("1");
        info.setImpledareaName("测试123");
        info.setImplementerGrage("1");
        info.setPolicyType("11");
        info.setUnitCode("11");
        info.setImplementerName("5454");
        manageleadresponsInfoServiceImpl.create(info);
    }
    @Test
    public void manageleadresponsInfoUpdate() throws Exception {
        //实体类
        ManageleadresponsInfo info = new ManageleadresponsInfo();
        info.setAreaGrage("1");
        info.setModifier("邢家华");
        info.setImpledareaCode("1");
        info.setImpledareaName("测试123");
        info.setImplementerGrage("1");
        info.setPolicyType("11");
        info.setUnitCode("11");
        info.setImplementerName("5454");
        info.setId("e24cd7718a03488ba175773c426294a9");
        manageleadresponsInfoServiceImpl.update(info);
    }

    @Test
    public void manageleadresponsInfoDelete() throws Exception {
       String id="99d6856ecce4470392d3de4bb9fe21e1";
        manageleadresponsInfoServiceImpl.delete(id);

    }
    @Test
    public void manageleadresponsInfoDetails() throws Exception {
        String id="e9504ebf2b2a4f74a3071fa93d97e597";
        manageleadresponsInfoServiceImpl.findById(id);

    }

    @Test
    public void manageleadresponsInfo() throws Exception {
        ManageleadresponsInfoQueryCriteria criteria = new ManageleadresponsInfoQueryCriteria();
        criteria.setCreator("xjh");
        List<String> createTime = new ArrayList<String>();
        createTime.add("2020-4-9 14:48:41");
        createTime.add("2020-4-9 15:05:35");
        criteria.setCreateTime(createTime);
        criteria.setImplementerGrage("");
        criteria.setModifier("");
        criteria.setPolicyType("");
        manageleadresponsInfoServiceImpl.queryAll(criteria);

    }

}
