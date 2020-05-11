package com.xyz.biz.http.secur;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.xyz.modules.biz.service.dispute.qo.DiseventQueryCriteria;
import com.xyz.modules.biz.service.org.BuildheadInfoService;
import com.xyz.modules.biz.service.org.entity.BuildheadInfo;
import com.xyz.modules.biz.service.org.qo.BuildheadInfoQueryCriteria;
import com.xyz.modules.biz.service.secur.BizSecurHomicidebaseinfoService;
import com.xyz.modules.biz.service.secur.VictiminfoService;
import com.xyz.modules.biz.service.secur.dto.BizSecurHomicidebaseinfoQueryCriteria;
import com.xyz.modules.biz.service.secur.entity.BizSecurHomicidebaseinfo;
import com.xyz.modules.biz.service.secur.entity.Victiminfo;
import com.xyz.modules.system.util.DictEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BizSecurHomicidebaseinfoApplicationTests {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private BizSecurHomicidebaseinfoService  bizSecurHomicidebaseinfoService;

    @Autowired
    private VictiminfoService victiminfoService;
    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();//初始化MockMvc对象,添加Security过滤器链
    }


    @Test
    @Rollback(value = false)
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "jwtUserDetailsService")
    public void testAdd() throws Exception {

        BizSecurHomicidebaseinfo homicidebaseinfo=new BizSecurHomicidebaseinfo();
        homicidebaseinfo.setOperName("张三");
        homicidebaseinfo.setCreator("李四");
        homicidebaseinfo.setCaseId(UUID.randomUUID().toString());
        homicidebaseinfo.setCaseCode("案件编号001");
        homicidebaseinfo.setBriefdescrip("案情摘要");
        homicidebaseinfo.setStatusCd(DictEnum.SJZT.getDistName());

        bizSecurHomicidebaseinfoService.create(homicidebaseinfo);

    }

    @Test
    @Rollback(value = false)
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "jwtUserDetailsService")
    public void testAddViciminfo() throws Exception {

        Victiminfo victiminfo=new Victiminfo();
        victiminfo.setVicId(UUID.randomUUID().toString());
//        victiminfo.setCaseCode("案件编号001");
        victiminfo.setPersonName("嫌疑人1");
        victiminfo.setEffDate(new Timestamp(new Date().getTime()));
        victiminfo.setExpDate(new Timestamp((new Date().getTime())));
        victiminfoService.create(victiminfo);

    }


    @Test

    public void testUpdate() throws Exception {
//        BuildheadInfo buildheadInfo = new BuildheadInfo();
//        buildheadInfo.setId("");
//        buildheadInfo.setModifier("张三");
//        buildheadInfo.setAddr(DictEnum.ADDRESS.getDistName());
//        buildheadInfo.setAddrDetail("郑州二七广场");
//        buildheadInfo.setBirth(new Timestamp(new Date().getTime()));
//        buildheadInfo.setBuildName("王五");
//        buildheadInfo.setBuildArea(120.5);
//        buildheadInfo.setCreator("耶稣");
//        buildheadInfo.setFixedPhone("222222222");
//        buildheadInfo.setHeadName("老王");
//        buildheadInfo.setHouseholdsNum(1);
//        buildheadInfo.setId(UUID.randomUUID().toString());
//        buildheadInfo.setEducationBg(DictEnum.XUE_LI.getDistName());
//        buildheadInfo.setLat(1.1);
//        buildheadInfo.setLng(1.2);
//        buildheadInfo.setMobile("22");
//        buildheadInfo.setLayerNum(11);
//        buildheadInfo.setPeopleNum(33);
//        buildheadInfo.setUnitCode("dd");
//        buildheadInfo.setPoliticalStatus("1");
//        buildheadInfo.setSex("男");
//        buildheadInfo.setVillageCode("100");
//        buildheadInfo.setVillageName("钱塘江小区");
//        buildheadInfo.setNational(DictEnum.MIN_ZU.getDistName());
//        buildheadInfo.setUnitNum(1);
////        this.mvc.perform(post("/api/BuildheadInfo")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(JSON.toJSONString(buildheadInfo))
////                .accept(MediaType.ALL))
////                .andExpect(status().is(201));
//        buildheadInfoService.update(buildheadInfo);


    }

    @Test
    public void testDelete() {
//        buildheadInfoService.delete("");
    }

    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "jwtUserDetailsService")
    public void testQuery() throws Exception {
        BizSecurHomicidebaseinfoQueryCriteria criteria=new BizSecurHomicidebaseinfoQueryCriteria();

   Object object= bizSecurHomicidebaseinfoService.queryAll(criteria);

        System.out.println(1);
    }


}

