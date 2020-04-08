package com.xyz.biz;

import com.xyz.modules.biz.domain.BuildheadInfo;
import com.xyz.modules.biz.service.BuildheadInfoService;
import com.xyz.modules.biz.service.dto.BuildheadInfoQueryCriteria;
import com.xyz.modules.system.domain.Dept;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.service.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 权限相关测试类
 *
 * @author dadovicn
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback(false)
public class AuditTest {
    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptService deptService;

    @Autowired
    private BuildheadInfoService buildheadInfoService;

    @Test
    public void testBuildhead() {
        List<String> deptCodes = deptService.getDownGradeDeptCodes("2e7061d1-74a9-11ea-9c4d-00163e00af9c");
        BuildheadInfoQueryCriteria criteria = new BuildheadInfoQueryCriteria();
        criteria.setCreator("f14bb76c-963a-4764-84ad-21da5fd75978");
        criteria.setUnitCode(deptCodes);

        List<BuildheadInfo> mm = (List<BuildheadInfo>) buildheadInfoService.queryAll(criteria);
        System.out.println(mm);
        System.out.println("asdf");
    }

    /**
     * 测试部门数据向下递归
     */
    @Test
    public void testTree() {
        Dept dept = deptRepository.findById("f14bb76c-963a-4764-84ad-21da5fd75978").get();
        List<String> ids = new ArrayList<>();
        tree2list(dept, ids);
        System.out.println(ids);
    }

    public static void tree2list(Dept root, List<String> list){
        if (root == null) {
            return ;
        }
        list.add(root.getCode());
        if (root.getDepts()==null){
            return;
        }
        for (Dept sub:root.getDepts()){
            tree2list(sub, list);
        }
    }



}
