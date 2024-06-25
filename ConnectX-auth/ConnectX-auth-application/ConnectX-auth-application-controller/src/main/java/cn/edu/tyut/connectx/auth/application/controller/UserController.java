package cn.edu.tyut.connectx.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.edu.tyut.connectx.auth.application.convert.AuthUserDtoConvert;
import cn.edu.tyut.connectx.auth.application.dto.AuthUserDto;
import cn.edu.tyut.connectx.auth.common.entity.Result;
import cn.edu.tyut.connectx.auth.domain.entity.AuthUserBo;
import cn.edu.tyut.connectx.auth.domain.service.AuthUserDomainService;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            checkUserInfo(authUserDto);
            AuthUserBo authUserBo = authUserDtoConvert.convertAuthUserDtoToAuthUserBo(authUserDto);
            return Result.ok(authUserDomainService.register(authUserBo));
        } catch (Exception e) {
            log.error("auth.user.register.authUserDto.error: {}", authUserDto);
            return Result.fail("注册用户失败");
        }
    }

    /**
     * 修改用户信息
     *
     * @param authUserDto 用户信息
     * @return 返回修改结果
     */
    @PostMapping("update")
    public Result<Object> update(@RequestBody AuthUserDto authUserDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.user.update.authUserDto:{}", JSON.toJSONString(authUserDto));
            }
            checkUserInfo(authUserDto);
            AuthUserBo authUserBo = authUserDtoConvert.convertAuthUserDtoToAuthUserBo(authUserDto);
            return Result.ok(authUserDomainService.update(authUserBo));
        } catch (Exception e) {
            log.error("auth.user.update.authUserDto.error: {}", authUserDto);
            return Result.fail("更新用户信息失败");
        }
    }

    /**
     * 获取用户信息
     *
     * @param authUserDto 用户信息
     * @return 回显用户信息
     */
    @PostMapping("getUserInfo")
    public Result<Object> getUserInfo(@RequestBody AuthUserDto authUserDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.user.getUserInfo.authUserDto:{}", JSON.toJSONString(authUserDto));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDto.getUserName()),
                    "用户名不能为空");
            AuthUserBo authUserBo = authUserDtoConvert.convertAuthUserDtoToAuthUserBo(authUserDto);
            AuthUserBo userInfo = authUserDomainService.getUserInfo(authUserBo);
            return Result.ok(authUserDtoConvert.convertAuthUserBoToAuthUserDto(userInfo));
        } catch (Exception e) {
            log.error("auth.user.getUserInfo.authUserDto.error: {}", authUserDto);
            return Result.fail("更新用户信息失败");
        }
    }

    /**
     * 退出登录
     *
     * @param userName 用户的openid
     * @return 返回是否退出登录成功
     */
    @PostMapping("logout")
    public Result<Object> logout(@RequestParam("username") String userName) {
        try {
            // 这里不使用判断日志级别的if判断主要是因为没有对象序列化的操作
            // 之前为了节省性能，会先判断日志级别，再进行对象序列化，能节省性能
            // 这里不需要对象序列化，也就不需要判断日志级别的if判断
            log.info("auth.user.logout.username:{}", userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName),
                    "用户名不能为空");
            StpUtil.logout(userName);
            return Result.ok();
        } catch (Exception e) {
            log.error("auth.user.logout.username.error: {}", userName);
            return Result.fail("退出登录失败");
        }
    }

    /**
     * 删除用户
     *
     * @param authUserDto 用户信息
     * @return 返回是否删除成功
     */
    @PostMapping("delete")
    public Result<Object> delete(@RequestBody AuthUserDto authUserDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.user.delete.authUserDto:{}", JSON.toJSONString(authUserDto));
            }
            checkUserInfo(authUserDto);
            AuthUserBo authUserBo = authUserDtoConvert.convertAuthUserDtoToAuthUserBo(authUserDto);
            return Result.ok(authUserDomainService.delete(authUserBo));
        } catch (Exception e) {
            log.error("auth.user.delete.authUserDto.error: {}", authUserDto);
            return Result.fail("删除用户信息失败");
        }
    }

    /**
     * 用户的启用和禁用
     *
     * @param authUserDto 用户信息
     * @return 返回是否修改成功
     */
    @PostMapping("changeStatus")
    public Result<Object> changeStatus(@RequestBody AuthUserDto authUserDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.user.changeStatus.authUserDto:{}", JSON.toJSONString(authUserDto));
            }
            Preconditions.checkNotNull(authUserDto.getStatus(), "用户状态不能为空");
            AuthUserBo authUserBo = authUserDtoConvert.convertAuthUserDtoToAuthUserBo(authUserDto);
            return Result.ok(authUserDomainService.changeStatus(authUserBo));
        } catch (Exception e) {
            log.error("auth.user.changeStatus.authUserDto.error: {}", authUserDto);
            return Result.fail("用户启用/禁用状态切换失败");
        }
    }

    private void checkUserInfo(@RequestBody @NotNull AuthUserDto authUserDto) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDto.getUserName()), "用户名不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDto.getEmail()), "邮箱不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDto.getPassword()), "密码不能为空");
    }

    /**
     * 测试登录，浏览器访问： <a href="http://localhost:8081/user/doLogin?username=zhang&password=123456">...</a>
     */
    @RequestMapping("doLogin")
    public Result<Object> doLogin(@RequestParam("validCode") String validCode) {
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(validCode),
                    "验证码不能为空!");
            SaTokenInfo saTokenInfo = authUserDomainService.doLogin(validCode);
            return Result.ok(saTokenInfo);
        } catch (Exception e) {
            log.error("UserController.doLogin.error: {}", e.getMessage());
            return Result.fail("用户登录失败");
        }
    }

    /**
     * 查询登录状态，浏览器访问： <a href="http://localhost:5000/auth/user/isLogin">...</a>
     */
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    /**
     * 测试会话登录接口，生成 sa token
      */
    @RequestMapping("testDoLogin")
    public SaResult doLogin(String name, String pwd) {
        // 第一步：比对前端提交的账号名称、密码
        String username = "zhang";
        String password = "123456";
        if(username.equals(name) && password.equals(pwd)) {
            // 第二步：根据账号id，进行登录
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }
}
