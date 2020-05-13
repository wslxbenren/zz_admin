package com.xyz.biz;


import com.xyz.modules.biz.service.route.CaseinfoService;
import com.xyz.modules.system.service.DictService;
import com.xyz.modules.system.service.dto.DictQueryCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConvenientinfoTest {

    @Autowired
    private CaseinfoService caseinfoService;

    @Autowired
    private DictService dictService;


    @Test
    public void test_dict()
    {

        DictQueryCriteria dictQueryCriteria=new DictQueryCriteria();

      Object o=dictService.findById((long)102);
        System.out.println(111);
    }

    @Test
    public void test_Caseinfo()
    {

        List list=new ArrayList();
    }
}
