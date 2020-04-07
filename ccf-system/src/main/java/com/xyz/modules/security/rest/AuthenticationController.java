package com.xyz.modules.security.rest;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.xyz.aop.log.Log;
import com.xyz.exception.BadRequestException;
import com.xyz.modules.monitor.service.RedisService;
import com.xyz.modules.security.security.AuthenticationInfo;
import com.xyz.modules.security.security.AuthorizationUser;
import com.xyz.modules.security.security.ImgResult;
import com.xyz.modules.security.security.JwtUser;
import com.xyz.modules.security.utils.JwtTokenUtil;
import com.xyz.modules.security.utils.VerifyCodeUtils;
import com.xyz.utils.EncryptUtils;
import com.xyz.utils.SecurityUtils;
import com.xyz.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 * 授权、根据token获取用户详细信息
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisService redisService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * 登录授权
     *
     * @param authorizationUser
     * @return
     */
    @Log("用户登录")
    @PostMapping(value = "${jwt.auth.path}")//yml文件配置的授权路径
    public ResponseEntity login(@Validated @RequestBody AuthorizationUser authorizationUser) {

//        // 查询验证码
//        String code = redisService.getCodeVal(authorizationUser.getUuid());
//        // 清除验证码
//        redisService.delete(authorizationUser.getUuid());
//        if (StringUtils.isBlank(code)) {
//            throw new BadRequestException("验证码已过期");
//        }
//        if (StringUtils.isBlank(authorizationUser.getCode()) || !authorizationUser.getCode().equalsIgnoreCase(code)) {
//            throw new BadRequestException("验证码错误");
//        }
        final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(authorizationUser.getUsername());

        if (!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))) {
            throw new AccountExpiredException("密码错误");
        }

        if (!jwtUser.isEnabled()) {
            throw new AccountExpiredException("账号已停用，请联系管理员");
        }

        // 生成令牌
        final String token = jwtTokenUtil.generateToken(jwtUser);

        // 返回 token
        return ResponseEntity.ok(new AuthenticationInfo(token, jwtUser));
    }

    /** 重写登陆 剔除验证码与uuid 根据用户名密码获取token
     *  @author: fuckjapan
     *  @Date: 2019/9/12 10:25
     *  @Description:
     */
    @Log("获取token")
    @PostMapping(value = "${jwt.auth.getToken}")
    public ResponseEntity login2(@Validated @RequestBody AuthorizationUser authorizationUser)
    {
        final JwtUser jwtUser=(JwtUser)userDetailsService.loadUserByUsername(authorizationUser.getUsername());
        if (!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))) {
            throw new AccountExpiredException("密码错误");
        }

        if (!jwtUser.isEnabled()) {
            throw new AccountExpiredException("账号已停用，请联系管理员");
        }
        // 生成令牌
        final String token = jwtTokenUtil.generateToken(jwtUser);
        // 返回 token
        return ResponseEntity.ok(new AuthenticationInfo(token, jwtUser));
    }



    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping(value = "${jwt.auth.account}")
    public ResponseEntity getUserInfo() {
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        return ResponseEntity.ok(jwtUser);
    }

    /**
     * 获取验证码
     */
    @GetMapping(value = "vCode")
    public ImgResult getCode(HttpServletResponse response) throws IOException {

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String uuid = IdUtil.simpleUUID();

        redisService.saveCode(uuid, verifyCode);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
            return new ImgResult(Base64.encode(stream.toByteArray()), uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            stream.close();
        }
    }
}
