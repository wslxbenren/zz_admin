package com.xyz.gen;

import com.xyz.modules.biz.service.org.entity.BuildheadInfo;
import com.xyz.modules.biz.service.org.repo.BuildheadInfoRepository;
import com.xyz.modules.system.domain.Dept;
import com.xyz.modules.system.domain.Job;
import com.xyz.modules.system.domain.Role;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 生成测试数据
 * @author dadovicn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MockData {
    @Resource
    private DeptRepository deptService;
    @Resource
    private UserRepository userRepository;
    @Resource
    private BuildheadInfoRepository buildheadInfoRepository;

    @Test
    public void genUser() {
        Random userCount = new Random();
        List<Dept> dept =  deptService.findAll();
        dept.forEach(i -> {
//            IntStream.range(0, userCount.nextInt(20) + 1 ).forEach(j -> {
                User user = new User();
                user.setId(UUID.randomUUID().toString());
                user.setUsername(MockUtil.mockNameEn(10));
                user.setNote(MockUtil.mockNameCn(2));
                user.setAvatar("https://i.loli.net/2019/04/04/5ca5b971e1548.jpeg");
                user.setEmail(MockUtil.mockEmail(10, 20));
                user.setPhone(MockUtil.mockPhone());
                user.setEnabled(true);
                user.setPassword("e10adc3949ba59abbe56e057f20f883e");
                user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
                user.setLastPasswordResetTime(Timestamp.valueOf(LocalDateTime.now()));
                user.setDept(i);
                Role role = new Role();
                if("-1".equals(i.getGrage())  ) {
                    role.setId(1L);
                } else if("1".equals(i.getGrage())) {
                    role.setId(2L);
                }else if("2".equals(i.getGrage())) {
                    role.setId(4L);
                }else if("3".equals(i.getGrage())) {
                    role.setId(5L);
                }
                user.setRoles(new HashSet<Role>() {{ add(role); }});
                Job job = new Job();
                job.setId("1");
                user.setJob(job);
                userRepository.save(user);
//            });
        });
    }



    // 重特大案（事）件
    public void genMajorcaseInfo() {

    }
    // 综治中心信息
    public void genManagecenterInfo() {

    }

    //综治领导责任制
    public void genManageLeadresponsInfo() {

    }



}