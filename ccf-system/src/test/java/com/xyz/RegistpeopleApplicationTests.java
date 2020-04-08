package com.xyz;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.xyz.modules.biz.domain.Registpeople;
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
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistpeopleApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();//初始化MockMvc对象,添加Security过滤器链
    }


    @Test
    @Rollback(value = false)
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "jwtUserDetailsService")
     public void testAdd() throws Exception {
        Registpeople registpeople = new Registpeople();
        registpeople.setRegisId(UUID.randomUUID().toString());
        registpeople.setIdentityNum("55555");
        registpeople.setPersonName("李四");
        registpeople.setUsedName("张三");
        registpeople.setPersonSex("男");
        registpeople.setDateBirth(new Timestamp(new Date(2020,5,1).getTime()));
        registpeople.setNation("");
        registpeople.setNativeInfo("");
        registpeople.setMarriageFlag("");
        registpeople.setPartyFlag("");
        registpeople.setEducationBg("");
        registpeople.setFaithType("");
        registpeople.setVocationCode("");
        registpeople.setVocation("法师");
        registpeople.setServiceAddr("郑州");
        registpeople.setContact("110");
        registpeople.setRegisteredPlace("");
        registpeople.setRegisteredAddr("");
        registpeople.setResidence("");
        registpeople.setResidenceAddr("");
        registpeople.setHouseholdId("");
        registpeople.setDoorNo("");
        registpeople.setHouseheadIdno("");
        registpeople.setHouseheadName("");
        registpeople.setHouseheadRela("");
        registpeople.setHouseheadMobile("");
        registpeople.setEffDate(new Timestamp(new Date().getTime()));
        registpeople.setExpDate(new Timestamp(new Date(2020,5,1).getTime()));
        registpeople.setStatus("");
        registpeople.setStatusCd("");
        registpeople.setOperName("");
        registpeople.setOperDate(new Timestamp(new Date().getTime()));
        registpeople.setCreator("");
        registpeople.setCreateTime(new Timestamp(new Date().getTime()));
        registpeople.setUnitCode("");
        registpeople.setNativeInfoAddr("");
        registpeople.setServicePlaceCode("");
        this.mvc.perform(post("/api/Registpeople")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(registpeople))
                .accept(MediaType.ALL))
                .andExpect(status().is(201));
    }
    @Test
    public void testQuery() throws Exception {
//        JodaTimeConverters.JodaLocalDateTimeToInstant.valueOf()
        this.mvc.perform(get("/api/Registpeople").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andExpect(status().is(200));
    }

}

