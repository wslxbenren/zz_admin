package com.xyz.gen;

import com.xyz.gen.MockUtil;
import com.xyz.modules.biz.domain.BuildheadInfo;
import com.xyz.modules.biz.repository.BuildheadInfoRepository;
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
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

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
        List<Dept> dept =  deptService.findAll();
        dept.forEach(i -> {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setUsername(MockUtil.mockNameCn(2));
            user.setAvatar("https://i.loli.net/2019/04/04/5ca5b971e1548.jpeg");
            user.setEmail(MockUtil.mockEmail(10, 20));
            user.setPhone(MockUtil.mockPhone());
            user.setEnabled(true);
            user.setPassword("e10adc3949ba59abbe56e057f20f883e");
            user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            user.setLastPasswordResetTime(Timestamp.valueOf(LocalDateTime.now()));
            user.setDept(i);
            Role role = new Role();
            role.setId(1l);
            user.setRoles(new HashSet<Role>() {{ add(role); }});
            Job job = new Job();
            job.setId("1");
            user.setJob(job);
            userRepository.save(user);
        });
    }


    // 楼栋长
    @Test
    public void genBuildheadInfo() {
        List<User> userList = userRepository.findAll();
        AtomicInteger a = new AtomicInteger(0);
        userList.forEach(i -> {
            BuildheadInfo buildheadInfo = new BuildheadInfo();
            buildheadInfo.setId(UUID.randomUUID().toString());
            buildheadInfo.setVillageCode("0001");
            buildheadInfo.setVillageName("南阳一号");
            buildheadInfo.setBuildName("南阳一号"+ a.get()+ "号楼");
            buildheadInfo.setBuildArea(1000.4d);
            buildheadInfo.setLayerNum(32);
            buildheadInfo.setUnitNum(2);
            buildheadInfo.setHouseholdsNum(150);
            buildheadInfo.setPeopleNum(500);
            buildheadInfo.setHeadName(MockUtil.mockNameCn(2));
            buildheadInfo.setSex("1");
            buildheadInfo.setNational("01");
            buildheadInfo.setPoliticalStatus("01");
            buildheadInfo.setBirth(Timestamp.valueOf(LocalDateTime.now().minusYears(40)));
            buildheadInfo.setEducationBg("10");
            buildheadInfo.setMobile(MockUtil.mockPhone());
            buildheadInfo.setFixedPhone(MockUtil.mockPhone());
            buildheadInfo.setAddr("河南省南阳市");
            buildheadInfo.setAddrDetail("某县某市");
            buildheadInfo.setLng(1.11111);
            buildheadInfo.setLat(1.11111);
            buildheadInfo.setCreator(i.getId());
            buildheadInfo.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            buildheadInfo.setModifier(i.getId());
            buildheadInfo.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            buildheadInfo.setUnitCode(i.getDept().getCode());
            a.incrementAndGet();
            buildheadInfoRepository.save(buildheadInfo);
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