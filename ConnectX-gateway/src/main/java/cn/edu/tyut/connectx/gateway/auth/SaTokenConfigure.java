package cn.edu.tyut.connectx.gateway.auth;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sa-Token 权限认证 配置类
 *
 * @author click33
 */
@Configuration
public class SaTokenConfigure {
    /**
     * 注册 Sa-Token全局过滤器
     *
     * @return SaReactorFilter
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                    // 这里的排除路径是以前端请求路径为准的
                    SaRouter.match("/auth/**")
                            .notMatch("/auth/user/doLogin", "/auth/user/testDoLogin")
                            .check(r -> StpUtil.checkRole("normal_user"));
                    SaRouter.match("/oss/**", r -> StpUtil.checkLogin());
                    SaRouter.match("/subject/**", r -> StpUtil.checkLogin());
                    SaRouter.match("/subject/subject/add", r -> StpUtil.checkPermission("subject:add"));
                });
    }
}
