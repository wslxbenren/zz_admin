package com.xyz.modules.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xyz.exception.EntityExistException;
import com.xyz.exception.EntityNotFoundException;
import com.xyz.modules.biz.audit.AuditSpecification;
import com.xyz.modules.monitor.service.RedisService;
import com.xyz.modules.system.repository.DeptRepository;
import com.xyz.modules.system.repository.UserRepository;
import com.xyz.modules.system.service.DictDetailService;
import com.xyz.modules.system.util.DictEnum;
import com.xyz.utils.ValidationUtil;
import com.xyz.modules.system.domain.User;
import com.xyz.modules.system.service.UserService;
import com.xyz.modules.system.service.dto.UserDTO;
import com.xyz.modules.system.service.dto.UserQueryCriteria;
import com.xyz.modules.system.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private DeptRepository deptRepository;


    @Autowired
    private AuditSpecification audit;

    @Autowired
    private DictDetailService dictDetailService;

    @Override
    @Transactional
    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll(audit.genSpecification(criteria), pageable);
        List<UserDTO> userDTOList = userMapper.toDto(page.getContent());
        for (UserDTO f : userDTOList) {

            String dd = dictDetailService.transDict(DictEnum.ZZMM.getDistName(), f.getPoliticalStatus());
            f.setPoliticalStatus(dd == null ? "无数据" : dd);// 政治面貌
//
            dd = dictDetailService.transDict(DictEnum.ZJDM.getDistName(), f.getCardCode());
            f.setCardCode(dd == null ? "无数据" : dd);// 证件代码
//
            dd = dictDetailService.transDict(DictEnum.XUE_LI.getDistName(), f.getEducationBg());
            f.setEducationBg(dd == null ? "无数据" : dd);// 学历

            dd = dictDetailService.transDict(DictEnum.MIN_ZU.getDistName(), f.getNational());
            f.setNational(dd == null ? "无数据" : dd);//民族

        }


        Map map = new HashMap();
        map.put("content", userDTOList);
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    public UserDTO findById(String id) {
        Optional<User> user = userRepository.findById(id);
        ValidationUtil.isNull(user, "User", "id", id);
        return userMapper.toDto(user.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO create(User resources) {

        if (userRepository.findByUsername(resources.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }

        if (userRepository.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        if (resources.getId() == null) {
            resources.setId(UUID.randomUUID().toString());
        }
        // 默认密码 123456，此密码是加密后的字符
        resources.setPassword("e10adc3949ba59abbe56e057f20f883e");
//        resources.setAvatar("https://i.loli.net/2019/04/04/5ca5b971e1548.jpeg");
        return userMapper.toDto(userRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {
        Optional<User> userOptional = userRepository.findById(resources.getId());
        ValidationUtil.isNull(userOptional, "User", "id", resources.getId());

        User user = userOptional.get();

        User user1 = userRepository.findByUsername(user.getUsername());
        User user2 = userRepository.findByEmail(user.getEmail());

        if (user1 != null && !user.getId().equals(user1.getId())) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }

        if (user2 != null && !user.getId().equals(user2.getId())) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }

        // 如果用户的角色改变了，需要手动清理下缓存
        if (!resources.getRoles().equals(user.getRoles())) {
            String key = "role::loadPermissionByUser:" + user.getUsername();
            redisService.delete(key);
            key = "role::findByUsers_Id:" + user.getId();
            redisService.delete(key);
        }
        user.setCardNumber(resources.getCardNumber());
        user.setNational(resources.getNational());
        user.setCardCode(resources.getCardCode());
        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setRoles(resources.getRoles());
        user.setDept(resources.getDept());
        user.setJob(resources.getJob());
        user.setPhone(resources.getPhone());
        user.setCreator(user.getCreator());
        user.setBirth(resources.getBirth());
        user.setModifier(resources.getModifier());
        user.setEducationBg(resources.getEducationBg());
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findByName(String userName) {
        User user = null;
        if (ValidationUtil.isEmail(userName)) {
            user = userRepository.findByEmail(userName);
        } else {
            user = userRepository.findByUsername(userName);
        }
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userMapper.toDto(user);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String pass) {
        userRepository.updatePass(username, pass, new Date());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAvatar(String username, String url) {
        userRepository.updateAvatar(username, url);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String username, String email) {
        userRepository.updateEmail(username, email);
    }


    @Override
    public List<User> getHttpPersoninfo(String thirdld, String lastTime, String pageNo, String pageSize) {
        List<User> userList = new ArrayList<>();
        String url = "http://62.64.11.7:9010/pams/sso/basicinfosynchronizecontrol/personinfosyn.do";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("thirdld", thirdld);
        map.add("lastTime", lastTime);
        map.add("pageNo", pageNo);
        map.add("pageSize", pageSize);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);


        JSONObject body = JSONObject.parseObject(response.getBody());
        String list = body.getString("list");
        userList = JSONObject.parseArray(list, User.class);

        return userList;
    }


    @Override
    public int savePoliceIncrement() {
        List<User> zzUserList = this.getHttpPersoninfo("htcf", "0", "1", "50000");

        if (zzUserList.size() > 0) {

            for (User user : zzUserList) {
                // 默认密码 123456，此密码是加密后的字符
                user.setPassword("e10adc3949ba59abbe56e057f20f883e");
                user.setEnabled(true);
                user.setDept(deptRepository.getOne(user.getDepId()));
                userRepository.save(user);
            }
        }
        return 0;
    }


}
