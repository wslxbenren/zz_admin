package com.xyz.biz.http.actual;

import com.xyz.modules.biz.service.org.repo.BuildheadInfoRepository;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Random;

/**
 * 实有人口生成测试数据模块
 */
public class MockActual {
//    @Resource
//    private DeptRepository deptService;
//    @Resource
//    private UserRepository userRepository;
//    @Resource
//    private BuildheadInfoRepository buildheadInfoRepository;

    @Test
    public void genFloatPeople() {
        for (int i = 0; i<100; i++) {
            int r = new Random().nextInt(20);
            System.out.println(r);
        }

    }
}
