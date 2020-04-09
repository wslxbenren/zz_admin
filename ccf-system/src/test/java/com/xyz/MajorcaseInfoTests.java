package com.xyz;

import com.alibaba.fastjson.JSON;
import com.xyz.modules.biz.domain.MajorcaseInfo;
import com.xyz.modules.biz.service.dto.MajorcaseInfoQueryCriteria;
import com.xyz.modules.biz.service.dto.ManageleadresponsInfoQueryCriteria;
import com.xyz.modules.biz.service.impl.MajorcaseInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MajorcaseInfoTests {

    @Autowired
    private  MajorcaseInfoServiceImpl majorcaseInfoServiceImpl;

    @Test
    public void manageleadresponsInfoAdd() throws Exception {
        //实体类
        MajorcaseInfo info = new MajorcaseInfo();
        info.setCreator("xjh");
        info.setUnitCode("11");
        info.setCaseCode("11");
        info.setCaseGrage("15");
        info.setCaseName("测试789");
        info.setCaseInfo("14454");
        info.setCaseType("54");
        info.setOccurAddr("545");
        info.setOccurAddrdetail("474");
        majorcaseInfoServiceImpl.create(info);

    }
    @Test
    public void manageleadresponsInfoUpdate() throws Exception {
        //实体类
        MajorcaseInfo info = new MajorcaseInfo();
        info.setModifier("邢家华");
        info.setUnitCode("11");
        info.setCaseCode("11");
        info.setCaseGrage("15");
        info.setCaseName("测试789");
        info.setCaseInfo("14454");
        info.setCaseType("54");
        info.setOccurAddr("545");
        info.setOccurAddrdetail("474");
        info.setId("399780754a7b488dbc9b7821ad89ff10");
        majorcaseInfoServiceImpl.update(info);

    }

    @Test
    public void manageleadresponsInfoDelete() throws Exception {
        String id="399780754a7b488dbc9b7821ad89ff10";
        majorcaseInfoServiceImpl.delete(id);

    }
    @Test
    public void manageleadresponsInfoDetails() throws Exception {
        String id="399780754a7b488dbc9b7821ad89ff10";
        majorcaseInfoServiceImpl.findById(id);

    }

    @Test
    public void testQuery() throws Exception {
        MajorcaseInfoQueryCriteria criteria = new MajorcaseInfoQueryCriteria();
        criteria.setCreator("邢家华");
        List<String> createTime = new ArrayList<String>();
        createTime.add("2020-4-9 14:48:41");
        createTime.add("2020-4-9 15:05:35");
        criteria.setCreateTime(createTime);
        criteria.setCaseGrage("");
        criteria.setCaseName("");
        criteria.setCaseType("");
        criteria.setModifier("");
        criteria.setOccurAddr("");
        majorcaseInfoServiceImpl.queryAll(criteria);
    }
}
