package com.xyz;
/**
 * 作者：xjh
 * 时间：2020-4-9 15:14:43
 */

import com.xyz.modules.biz.domain.Leftbehind;
import com.xyz.modules.biz.domain.MajorcaseInfo;
import com.xyz.modules.biz.service.dto.LeftbehindQueryCriteria;
import com.xyz.modules.biz.service.dto.MajorcaseInfoQueryCriteria;
import com.xyz.modules.biz.service.impl.LeftbehindServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeftbehindTests {


    @Autowired
    private LeftbehindServiceImpl leftbehindServiceImpl;

    @Test
    public void LeftbehindAdd() throws Exception {
        //实体类
        Leftbehind info = new Leftbehind();
        info.setCreator("xjh");
        info.setUnitCode("11");
        info.setAnnualIncome("112");
        info.setAnnualPerincome("454");
        info.setContact("562");
        info.setDateBirth(new Timestamp(new Date().getTime()));
        info.setDemand("1");
        info.setEducationBg("1");
        info.setEffDate(new Timestamp(new Date().getTime()));
        info.setExpDate(new Timestamp(new Date().getTime()));
        info.setFaithType("1");
        info.setHealthy("1");
        info.setHelpeComment("01");
        info.setIdentityNum("02");
        info.setLeftbehindType("03");
        info.setMainmemAddr("122");
        info.setMainmemHealth("2");
        info.setMainmemAddrcode("1");
        info.setMainmemIdno("1");
        info.setMainmemName("1");
        info.setMainmemRela("1");
        info.setMarriageFlag("1");
        info.setNation("1");
        info.setNativeInfo("1");
        info.setNativeInfoAddr("1");
        info.setPartyFlag("1");
        info.setPersonSex("1");
        info.setRegisteredAddr("1");
        info.setRegisteredPlace("1");
        info.setResidence("1");
        info.setServiceAddr("1");
        info.setServicePlaceCode("1");
        info.setStatus("1");
        info.setStatusCd("1");
        info.setUnitCode("1");
        info.setUsedName("xjh");
        info.setVocation("1");
        info.setVocationCode("1");
        info.setPersonName("12");
        info.setResidenceAddr("1");
        info.setHouseholdId("1");
        info.setMainmemMobile("1");
        leftbehindServiceImpl.create(info);
    }

    @Test
    public void LeftbehindUpdate() throws Exception {
        Leftbehind info = new Leftbehind();
        info.setOperName("邢家华");
        info.setUnitCode("11");
        info.setAnnualIncome("112");
        info.setAnnualPerincome("454");
        info.setContact("562");
        info.setDateBirth(new Timestamp(new Date().getTime()));
        info.setDemand("1");
        info.setEducationBg("1");
        info.setEffDate(new Timestamp(new Date().getTime()));
        info.setExpDate(new Timestamp(new Date().getTime()));
        info.setFaithType("1");
        info.setHealthy("1");
        info.setHelpeComment("01");
        info.setIdentityNum("02");
        info.setLeftbehindType("03");
        info.setMainmemAddr("122");
        info.setMainmemHealth("2");
        info.setMainmemAddrcode("1");
        info.setMainmemIdno("1");
        info.setMainmemName("1");
        info.setMainmemRela("1");
        info.setMarriageFlag("1");
        info.setNation("1");
        info.setNativeInfo("1");
        info.setNativeInfoAddr("1");
        info.setPartyFlag("1");
        info.setPersonSex("1");
        info.setRegisteredAddr("1");
        info.setRegisteredPlace("1");
        info.setResidence("1");
        info.setServiceAddr("1");
        info.setServicePlaceCode("1");
        info.setStatus("1");
        info.setStatusCd("1");
        info.setUnitCode("1");
        info.setUsedName("xjh");
        info.setVocation("1");
        info.setVocationCode("1");
        info.setPersonName("12");
        info.setResidenceAddr("1");
        info.setHouseholdId("1");
        info.setMainmemMobile("1");
        info.setLeftId("803a0bb16ff84975a9c40bac302ecd6c");
        leftbehindServiceImpl.update(info);
    }

    @Test
    public void LeftbehindDelete() throws Exception {
        String id="803a0bb16ff84975a9c40bac302ecd6c";
        leftbehindServiceImpl.delete(id);

    }
    @Test
    public void LeftbehindDetails() throws Exception {
        String id="803a0bb16ff84975a9c40bac302ecd6c";
        leftbehindServiceImpl.findById(id);

    }

    @Test
    public void testQuery() throws Exception {
        LeftbehindQueryCriteria criteria = new LeftbehindQueryCriteria();
        criteria.setCreator("邢家华");
        List<String> createTime = new ArrayList<String>();
        createTime.add("2020-4-9 14:48:41");
        createTime.add("2020-4-9 15:05:35");
        criteria.setCreateTime(createTime);
        criteria.setEducationBg("");
        criteria.setNation("");
        criteria.setPartyFlag("");
        criteria.setPersonName("");
        leftbehindServiceImpl.queryAll(criteria);
    }
}
