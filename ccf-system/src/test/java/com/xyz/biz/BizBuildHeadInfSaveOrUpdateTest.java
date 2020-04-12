package com.xyz.biz;

import com.xyz.modules.biz.domain.BuildheadInfo;
import com.xyz.modules.biz.repository.BuildheadInfoRepository;
import com.xyz.modules.system.util.DictEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BizBuildHeadInfSaveOrUpdateTest {


    @Resource
    private BuildheadInfoRepository buildheadInfoRepository;



    private BuildheadInfo makeEntity(){
        BuildheadInfo buildheadInfo = new BuildheadInfo();
        buildheadInfo.setModifier("张三save");
        buildheadInfo.setAddr("123");
        buildheadInfo.setAddrDetail("郑州二七广场");
        buildheadInfo.setBirth(new Timestamp(new Date().getTime()));
        buildheadInfo.setBuildName("王五");
        buildheadInfo.setBuildArea(120.5);
        buildheadInfo.setCreator("耶稣");
        buildheadInfo.setFixedPhone("222222222");
        buildheadInfo.setHeadName("老王");
        buildheadInfo.setHouseholdsNum(1);
        buildheadInfo.setId(UUID.randomUUID().toString());
        buildheadInfo.setEducationBg(DictEnum.XUE_LI.getDistName());
        buildheadInfo.setLat(1.1);
        buildheadInfo.setLng(1.2);
        buildheadInfo.setMobile("22");
        buildheadInfo.setLayerNum(11);
        buildheadInfo.setPeopleNum(33);
        buildheadInfo.setUnitCode("dd");
        buildheadInfo.setPoliticalStatus("1");
        buildheadInfo.setSex("男");
        buildheadInfo.setVillageCode("100");
        buildheadInfo.setVillageName("钱塘江小区");
        buildheadInfo.setNational(DictEnum.MIN_ZU.getDistName());
        buildheadInfo.setUnitNum(1);
        return buildheadInfo;
    }

    private BuildheadInfo makeUpdateEntity(){
        String id = "cfee306e-c17c-45ed-bc39-1d9004839cfa";
        BuildheadInfo buildheadInfo = new BuildheadInfo();
        //前端在更新时，比如传入Id
        buildheadInfo.setId(id);
        buildheadInfo.setBuildName("华林");
        buildheadInfo.setModifier("张三update");
        buildheadInfo.setAddr("123");
        buildheadInfo.setAddrDetail("郑州二七广场update");
        buildheadInfo.setBirth(new Timestamp(new Date().getTime()));
        buildheadInfo.setBuildName("王五update");
        buildheadInfo.setBuildArea(120.5);
        buildheadInfo.setCreator("耶稣update");
        buildheadInfo.setFixedPhone("222222222update");
        buildheadInfo.setHeadName("老王update");
        buildheadInfo.setHouseholdsNum(1);
        buildheadInfo.setEducationBg(DictEnum.XUE_LI.getDistName());
        buildheadInfo.setLat(1.1);
        buildheadInfo.setLng(1.2);
        buildheadInfo.setMobile("22");
        buildheadInfo.setLayerNum(11);
        buildheadInfo.setPeopleNum(33);
        buildheadInfo.setUnitCode("dd");
        buildheadInfo.setPoliticalStatus("1");
        buildheadInfo.setSex("男");
        buildheadInfo.setVillageCode("100");
        buildheadInfo.setVillageName("钱塘江小区update");
        buildheadInfo.setNational(DictEnum.MIN_ZU.getDistName());
        buildheadInfo.setUnitNum(1);
        return buildheadInfo;
    }


    @Test
    public void testSave(){
        BuildheadInfo buildheadInfo = this.makeEntity();
        buildheadInfoRepository.save(buildheadInfo);
    }

    @Test
    public void testUpdate(){
        //数据更新时，前端一定会传ID场景如下
        /**
         * 1,前端获取ID
         * 2,前端封装新的数据对象
         * 3,调用后台接口
         * 4,根据id查找到原数据
         * 5,根据ID更新
         */
        BuildheadInfo buildheadInfo = this.makeUpdateEntity();
//        String id = buildheadInfo.getId();
//        BuildheadInfo buildheadInfo_inDB = this.buildheadInfoRepository.findById(id).get(); //非必要,因为@Dany 注解会自动找到
//        buildheadInfo_inDB  ---》  copy(buildheadInfo); //没必要
        //@DynamicUpdate  程序开发只要无脑save即可
        buildheadInfoRepository.save(buildheadInfo);
        //日志如下，
        /**
         * Hibernate: select buildheadi0_.id as id1_9_0_, buildheadi0_.addr as addr2_9_0_, buildheadi0_.addr_detail as addr_det3_9_0_, buildheadi0_.birth as birth4_9_0_, buildheadi0_.build_area as build_ar5_9_0_, buildheadi0_.build_name as build_na6_9_0_, buildheadi0_.create_time as create_t7_9_0_, buildheadi0_.creator as creator8_9_0_, buildheadi0_.education_bg as educatio9_9_0_, buildheadi0_.fixed_phone as fixed_p10_9_0_, buildheadi0_.head_name as head_na11_9_0_, buildheadi0_.households_num as househo12_9_0_, buildheadi0_.lat as lat13_9_0_, buildheadi0_.layer_num as layer_n14_9_0_, buildheadi0_.lng as lng15_9_0_, buildheadi0_.mobile as mobile16_9_0_, buildheadi0_.modifier as modifie17_9_0_, buildheadi0_.national as nationa18_9_0_, buildheadi0_.people_num as people_19_9_0_, buildheadi0_.political_status as politic20_9_0_, buildheadi0_.sex as sex21_9_0_, buildheadi0_.unit_code as unit_co22_9_0_, buildheadi0_.unit_num as unit_nu23_9_0_, buildheadi0_.update_time as update_24_9_0_, buildheadi0_.village_code as village25_9_0_, buildheadi0_.village_name as village26_9_0_ from biz_org_buildhead_info buildheadi0_ where buildheadi0_.id=?
         * - | [] | [20200412 15:15:22.433] | [INFO] | [DESKTOP-7G65AKD] | [main] | [jdbc.sqlonly] | --> select buildheadi0_.id as id1_9_0_, buildheadi0_.addr as addr2_9_0_, buildheadi0_.addr_detail
         * as addr_det3_9_0_, buildheadi0_.birth as birth4_9_0_, buildheadi0_.build_area as build_ar5_9_0_,
         * buildheadi0_.build_name as build_na6_9_0_, buildheadi0_.create_time as create_t7_9_0_, buildheadi0_.creator
         * as creator8_9_0_, buildheadi0_.education_bg as educatio9_9_0_, buildheadi0_.fixed_phone as
         * fixed_p10_9_0_, buildheadi0_.head_name as head_na11_9_0_, buildheadi0_.households_num as househo12_9_0_,
         * buildheadi0_.lat as lat13_9_0_, buildheadi0_.layer_num as layer_n14_9_0_, buildheadi0_.lng
         * as lng15_9_0_, buildheadi0_.mobile as mobile16_9_0_, buildheadi0_.modifier as modifie17_9_0_,
         * buildheadi0_.national as nationa18_9_0_, buildheadi0_.people_num as people_19_9_0_, buildheadi0_.political_status
         * as politic20_9_0_, buildheadi0_.sex as sex21_9_0_, buildheadi0_.unit_code as unit_co22_9_0_,
         * buildheadi0_.unit_num as unit_nu23_9_0_, buildheadi0_.update_time as update_24_9_0_, buildheadi0_.village_code
         * as village25_9_0_, buildheadi0_.village_name as village26_9_0_ from biz_org_buildhead_info
         * buildheadi0_ where buildheadi0_.id='cfee306e-c17c-45ed-bc39-1d9004839cfa'
         * |
         * - | [] | [20200412 15:15:22.473] | [INFO] | [DESKTOP-7G65AKD] | [main] | [jdbc.resultsettable] | -->
         * |---------|-----|------------|----------------------|-----------|-----------|----------------------|--------|-------------|------------|----------|---------------|----|----------|----|-------|---------|---------|-----------|-----------------|----|----------|---------|----------------------|-------------|-------------|
         * |id       |addr |addr_detail |birth                 |build_area |build_name |create_time           |creator |education_bg |fixed_phone |head_name |households_num |lat |layer_num |lng |mobile |modifier |national |people_num |political_status |sex |unit_code |unit_num |update_time           |village_code |village_name |
         * |---------|-----|------------|----------------------|-----------|-----------|----------------------|--------|-------------|------------|----------|---------------|----|----------|----|-------|---------|---------|-----------|-----------------|----|----------|---------|----------------------|-------------|-------------|
         * |[unread] |123  |郑州二七广场      |2020-04-12 00:00:00.0 |120.5      |王五         |2020-04-12 15:03:33.0 |耶稣      |学历           |222222222   |老王        |1              |1.1 |11        |1.2 |22     |张三save   |民族       |33         |1                |男   |dd        |1        |2020-04-12 15:03:33.0 |100          |钱塘江小区        |
         * |---------|-----|------------|----------------------|-----------|-----------|----------------------|--------|-------------|------------|----------|---------------|----|----------|----|-------|---------|---------|-----------|-----------------|----|----------|---------|----------------------|-------------|-------------|
         * |
         * Hibernate: update biz_org_buildhead_info set addr=?, addr_detail=?, birth=?, build_name=?, fixed_phone=?, head_name=?, modifier=?, update_time=?, village_name=? where id=?
         * - | [] | [20200412 15:15:22.573] | [INFO] | [DESKTOP-7G65AKD] | [main] | [jdbc.sqlonly] | --> update biz_org_buildhead_info set addr='123', addr_detail='郑州二七广场update', birth='04/12/2020
         * 15:15:22.381', build_name='王五update', fixed_phone='222222222update', head_name='老王update',
         * modifier='张三update', update_time='04/12/2020 15:15:22.536', village_name='钱塘江小区update' where
         * id='cfee306e-c17c-45ed-bc39-1d9004839cfa'
         * |
         */
    }



}




