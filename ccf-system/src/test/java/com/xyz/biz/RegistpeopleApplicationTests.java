package com.xyz.biz;

import com.alibaba.fastjson.JSON;
import com.xyz.modules.biz.domain.Registpeople;
import com.xyz.modules.biz.service.RegistpeopleService;
import com.xyz.modules.biz.service.dto.RegistpeopleDTO;
import com.xyz.modules.biz.service.dto.RegistpeopleQueryCriteria;
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
public class RegistpeopleApplicationTests {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private RegistpeopleService registpeopleService;
    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();//初始化MockMvc对象,添加Security过滤器链
    }


    @Test
     public void testAdd() throws Exception {
        Registpeople registpeople = new Registpeople();
        registpeople.setRegisId(UUID.randomUUID().toString());
        registpeople.setIdentityNum("55555");
        registpeople.setPersonName("李四");
        registpeople.setUsedName("张三");
        registpeople.setPersonSex("男");
        registpeople.setCreator("青蛙");
        registpeople.setDateBirth(new Timestamp(new Date().getTime()));
        registpeople.setNation("2");
        registpeople.setNativeInfo("1");
        registpeople.setMarriageFlag("");
        registpeople.setPartyFlag("1");
        registpeople.setEducationBg("1");
        registpeople.setFaithType("1");
        registpeople.setVocationCode("112");
        registpeople.setVocation("法师");
        registpeople.setServiceAddr("郑州");
        registpeople.setContact("110");
        registpeople.setRegisteredPlace("223");
        registpeople.setRegisteredAddr("1");
        registpeople.setResidence("22");
        registpeople.setResidenceAddr("332");
        registpeople.setHouseholdId("2");
        registpeople.setDoorNo("1");
        registpeople.setHouseheadIdno("1");
        registpeople.setHouseheadName("122");
        registpeople.setHouseheadRela("33");
        registpeople.setHouseheadMobile("222111112");
        registpeople.setEffDate(new Timestamp(new Date().getTime()));
        registpeople.setExpDate(new Timestamp(new Date().getTime()));
        registpeople.setStatus("1");
        registpeople.setStatusCd("1");
        registpeople.setOperName("2");
        registpeople.setCreator("1");
        registpeople.setUnitCode("2");
        registpeople.setNativeInfoAddr("12");
        registpeople.setServicePlaceCode("33");
        registpeopleService.create(registpeople);
//        this.mvc.perform(post("/api/Registpeople")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JSON.toJSONString(registpeople))
//                .accept(MediaType.ALL))
//                .andExpect(status().is(201));
    }
    @Test
    public void testUpdate() throws Exception {
        Registpeople registpeople = new Registpeople();
        registpeople.setRegisId("70dbce8445134dcbb8718f7dbfc17821");
        registpeople.setIdentityNum("1111");
        registpeople.setPersonName("李四");
        registpeople.setUsedName("张三");
        registpeople.setPersonSex("男");
        registpeople.setDateBirth(new Timestamp(new Date().getTime()));
        registpeople.setNation("1");
        registpeople.setNativeInfo("12s");
        registpeople.setMarriageFlag("1");
        registpeople.setPartyFlag("1");
        registpeople.setEducationBg("0");
        registpeople.setFaithType("23");
        registpeople.setVocationCode("dd");
        registpeople.setVocation("法师");
        registpeople.setServiceAddr("郑州");
        registpeople.setContact("110");
        registpeople.setRegisteredPlace("12");
        registpeople.setRegisteredAddr("登封嵩山");
        registpeople.setResidence("22");
        registpeople.setResidenceAddr("12");
        registpeople.setHouseholdId("1");
        registpeople.setDoorNo("123");
        registpeople.setHouseheadIdno("1");
        registpeople.setHouseheadName("22");
        registpeople.setHouseheadRela("33");
        registpeople.setHouseheadMobile("2");
        registpeople.setEffDate(new Timestamp(new Date().getTime()));
        registpeople.setExpDate(new Timestamp(new Date().getTime()));
        registpeople.setStatus("1");
        registpeople.setStatusCd("2");
        registpeople.setOperName("合法");
        registpeople.setOperDate(new Timestamp(new Date().getTime()));
        registpeople.setCreator("陈晨");
        registpeople.setCreateTime(new Timestamp(new Date().getTime()));
        registpeople.setUnitCode("发范德萨打发");
        registpeople.setNativeInfoAddr("疯疯癫癫");
        registpeople.setServicePlaceCode("12");
        registpeopleService.update(registpeople);
    }

    @Test
    public void testDelete() {
        registpeopleService.delete("");
    }

    @Test
    public void testQuery() throws Exception {
        RegistpeopleDTO byId = registpeopleService.findById("70dbce8445134dcbb8718f7dbfc17821");
        RegistpeopleQueryCriteria q = new RegistpeopleQueryCriteria();
        q.setDateBirth(Arrays.asList("2020-02-02 20-20-20","2020-04-09 10:10:10"));
        q.setNation("1");
        q.setPersonName("是的");
        q.setUsedName("dfd");
        q.setNativeInfo("1");
        q.setMarriageFlag("1");
        q.setPartyFlag("1");
        q.setEducationBg("1");
        q.setFaithType("1");
        q.setVocationCode("1");
        q.setRegisteredPlace("1");
        q.setHouseholdId("2");
        q.setHouseheadName("是啥");
        q.setEffDate(Arrays.asList("2020-02-02 20-20-20","2020-04-09 10:10:10"));
        q.setExpDate(Arrays.asList("2020-02-02 20-20-20","2020-04-09 10:10:10"));
        q.setOperDate(Arrays.asList("2020-02-02 20-20-20","2020-04-09 10:10:10"));
        q.setCreateTime(Arrays.asList("2020-02-02 20-20-20","2020-04-09 10:10:10"));
        Object o = registpeopleService.queryAll(q);

    }

}

