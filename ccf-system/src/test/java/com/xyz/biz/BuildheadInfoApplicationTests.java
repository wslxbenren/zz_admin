package com.xyz.biz;

import com.alibaba.fastjson.JSON;
import com.xyz.modules.biz.domain.BuildheadInfo;
import com.xyz.modules.biz.service.BuildheadInfoService;
import com.xyz.modules.biz.service.dto.BuildheadInfoQueryCriteria;
import com.xyz.modules.system.util.DictEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuildheadInfoApplicationTests {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private BuildheadInfoService buildheadInfoService;
    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();//初始化MockMvc对象,添加Security过滤器链
    }


    @Test
    @Rollback(value = false)
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "jwtUserDetailsService")
    public void testAdd() throws Exception {
        BuildheadInfo buildheadInfo = new BuildheadInfo();
        buildheadInfo.setModifier("张三");
        buildheadInfo.setAddr(DictEnum.ADDRESS.getDistName());
        buildheadInfo.setAddrDetail("郑州二七广场");
        buildheadInfo.setBirth(new Timestamp(new Date().getTime()));
        buildheadInfo.setBuildName("王五");
        buildheadInfo.setBuildArea(120.5);
        buildheadInfo.setCreator("耶稣");
        buildheadInfo.setFixedPhone("222222222");
        buildheadInfo.setHeadName("老王");
        buildheadInfo.setHouseholdsNum(1);
        buildheadInfo.setId(UUID.randomUUID().toString());
        buildheadInfo.setEducationBg(DictEnum.XUE_LI.getDistName());
        buildheadInfo.setLat(1.1);
        buildheadInfo.setLng(1.2);
        buildheadInfo.setMobile("22");
        buildheadInfo.setLayerNum(11);
        buildheadInfo.setPeopleNum(33);
        buildheadInfo.setUnitCode("dd");
        buildheadInfo.setPoliticalStatus("1");
        buildheadInfo.setSex("男");
        buildheadInfo.setVillageCode("100");
        buildheadInfo.setVillageName("钱塘江小区");
        buildheadInfo.setNational(DictEnum.MIN_ZU.getDistName());
        buildheadInfo.setUnitNum(1);
//        this.mvc.perform(post("/api/BuildheadInfo")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JSON.toJSONString(buildheadInfo))
//                .accept(MediaType.ALL))
//                .andExpect(status().is(201));
        buildheadInfoService.create(buildheadInfo);
    }

    @Test

    public void testUpdate() throws Exception {
        BuildheadInfo buildheadInfo = new BuildheadInfo();
        buildheadInfo.setId("");
        buildheadInfo.setModifier("张三");
        buildheadInfo.setAddr(DictEnum.ADDRESS.getDistName());
        buildheadInfo.setAddrDetail("郑州二七广场");
        buildheadInfo.setBirth(new Timestamp(new Date().getTime()));
        buildheadInfo.setBuildName("王五");
        buildheadInfo.setBuildArea(120.5);
        buildheadInfo.setCreator("耶稣");
        buildheadInfo.setFixedPhone("222222222");
        buildheadInfo.setHeadName("老王");
        buildheadInfo.setHouseholdsNum(1);
        buildheadInfo.setId(UUID.randomUUID().toString());
        buildheadInfo.setEducationBg(DictEnum.XUE_LI.getDistName());
        buildheadInfo.setLat(1.1);
        buildheadInfo.setLng(1.2);
        buildheadInfo.setMobile("22");
        buildheadInfo.setLayerNum(11);
        buildheadInfo.setPeopleNum(33);
        buildheadInfo.setUnitCode("dd");
        buildheadInfo.setPoliticalStatus("1");
        buildheadInfo.setSex("男");
        buildheadInfo.setVillageCode("100");
        buildheadInfo.setVillageName("钱塘江小区");
        buildheadInfo.setNational(DictEnum.MIN_ZU.getDistName());
        buildheadInfo.setUnitNum(1);
//        this.mvc.perform(post("/api/BuildheadInfo")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JSON.toJSONString(buildheadInfo))
//                .accept(MediaType.ALL))
//                .andExpect(status().is(201));
        buildheadInfoService.update(buildheadInfo);
    }

    @Test
    public void testDelete() {
        buildheadInfoService.delete("");
    }

    @Test
    public void testQuery() throws Exception {
        BuildheadInfoQueryCriteria b = new BuildheadInfoQueryCriteria();
        b.setCreateTime(Arrays.asList("2020-02-02 20:20:20","2020-05-02 20:20:20"));
        b.setUpdateTime(Arrays.asList("2020-02-02 20:20:20","2020-05-02 20:20:20"));
        b.setSex("男");
        b.setNational("1");
        b.setPoliticalStatus("1");
        b.setEducationBg("1");
        b.setAddr("ss");
        b.setCreator("dd");
        b.setUnitCode(Arrays.asList("02","03"));
        b.setAddrDetail("0");
        Object o = buildheadInfoService.queryAll(b);
    }


}

