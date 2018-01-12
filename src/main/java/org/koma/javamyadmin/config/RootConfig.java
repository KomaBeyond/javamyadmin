package org.koma.javamyadmin.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.koma.javamyadmin.auth.MyAuthenticator;
import org.koma.javamyadmin.auth.FilterChainMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;

/**
 * 作为 spring ContextLoaderListener 上下文中 bean 的配置类文件
 *
 * @author koma <komazhang@foxmail.com>
 */
@Configuration
public class RootConfig {
    /////////////////////////////////////// Shiro Bean 相关配置
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());

        //设置 url 拦截规则
        shiroFilter.setFilterChainDefinitionMap(FilterChainMap.mapps());
        //设置验证失败的登录地址
        shiroFilter.setLoginUrl("/login");

        return shiroFilter;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置会话管理器
        //设置缓存管理器
        //设置身份认证器
        //设置权限验证器
        //设置用户信息数据源,即: Realm
        securityManager.setRealm(new MyAuthenticator());
        return securityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }
    /////////////////////////////////////// Shiro Bean 相关配置
}
