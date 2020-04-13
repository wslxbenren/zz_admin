package com.xyz.modules.system.service.impl;

import com.xyz.modules.system.service.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by xiaonanfeng on 2020/4/12.
 * 每天都在写BUG！
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptServiceImplTest {

    @Resource
    private DeptService deptService;

    @Test
    public void getDownGradeDeptCodes() {

//        String deptCode = "411324100071";
        String deptCode = "411321100004";
        deptService.getDownGradeDeptCodes(deptCode);


    }
}
