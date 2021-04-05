package com.codefriday.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author codefriday
 * @data 2021/4/5
 */
@Configuration
public class ShiroConfig {

    //Shiro过滤器
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        /**
         * 添加过滤器
         * anno:无需认证
         * authc:必须认证才能访问
         * user:必须有 记住我 功能才能访问
         * perms:必须有对应权限才能访问资源
         * role:拥有某个角色权限
         */
        Map<String,String> filterMap = new LinkedHashMap<>();

        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        bean.setFilterChainDefinitionMap(filterMap);

        bean.setLoginUrl("/toLogin");
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    //2、SecurityManager
    @Bean(name="securityManager")
    public DefaultSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    //1、用来取安全数据的地方
    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }

    //整合Shiro和Thymeleaf需要的类
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
