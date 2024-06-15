package cn.edu.tyut.connectx.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.edu.tyut.connectx.auth.application.convert.AuthUserDtoConvert;
import cn.edu.tyut.connectx.auth.application.dto.AuthUserDto;
import cn.edu.tyut.connectx.auth.common.entity.Result;
import cn.edu.tyut.connectx.auth.domain.entity.AuthUserBo;
import cn.edu.tyut.connectx.auth.domain.service.AuthUserDomainService;
import cn.edu.tyut.connectx.auth.infra.service.AuthUserService;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/8
 */
@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {
    private AuthUserDtoConvert authUserDtoConvert;
    private AuthUserDomainService authUserDomainService;

    @Autowired
    public void setAuthUserDtoConvert(AuthUserDtoConvert authUserDtoConvert) {
        this.authUserDtoConvert = authUserDtoConvert;
    }

    @Autowired
    public void setAuthUserDomainService(AuthUserDomainService authUserDomainService) {
        this.authUserDomainService = authUserDomainService;
    }

    /**
     * 注册用户
     *
     * @param authUserDto 注册的用户信息
     * @return 返回是否注册成功
     */
    @PostMapping("register")
    public Result<Object> register(@RequestBody AuthUserDto authUserDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.user.register.authUserDto:{}", JSON.toJSONString(authUserDto));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDto.getUserName()), "用户名不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDto.getEmail()), "邮箱不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDto.getPassword()), "密码不能为空");
            AuthUserBo authUserBo = authUserDtoConvert.convertAuthUserDtoToAuthUserBo(authUserDto);
            return Result.ok(authUserDomainService.register(authUserBo));
        } catch (Exception e) {
            log.error("auth.user.register.authUserDto.error: {}", authUserDto);
            return Result.fail("注册用户失败");
        }
    }


    /**
     * 测试登录，浏览器访问： <a href="http://localhost:8081/user/doLogin?username=zhang&password=123456">...</a>
     */
    @RequestMapping("doLogin")
    public SaResult doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return SaResult.data(tokenInfo);
        }
        return SaResult.error("登陆失败");
    }

    /**
     * 查询登录状态，浏览器访问： <a href="http://localhost:8081/user/isLogin">...</a>
     */
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
