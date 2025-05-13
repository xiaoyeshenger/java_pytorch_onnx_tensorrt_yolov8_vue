package cn.kafuka.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * @Author: zhangyong
 * description: 1.shiro配置,设置过滤器，白名单等
 * @Date: 2019-12-11 11:51
 * @Param:
 * @Return:
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        //1.初始化ShiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //2.设置 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //3.设置自定义的 JwtFilter
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //4.配置放行名单，其余所有请求都要经过JwtFilter过滤器处理( filterChainDefinitionMap.put("/**", "jwt");需要放到最后，因为这是顺序验证的)
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //(1).外部调用(此处和gateway全局拦截放行名单一致)
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/sys/auth/getToken", "anon");
        filterChainDefinitionMap.put("/sys/auth/login", "anon");
        filterChainDefinitionMap.put("/sys/auth/getSmsCode", "anon");
        filterChainDefinitionMap.put("/sys/httpPushLog/receiveHttpData", "anon");

        //(2).其余的所有请求都要经过JwtFilter过滤器处理(即必须有token才能访问)
        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
 }

    //注册SecurityManager
    @Bean
    public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置 SecurityManager，并注入 shiroRealm
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    //注册ShiroRealm
     @Bean
     public ShiroRealm shiroRealm() {
        return new ShiroRealm();
     }

    //开启shiro注解
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
     return authorizationAttributeSourceAdvisor;
    }

    // 防止 Spring 将 JwtFilter 注册为全局过滤器
    // 没有这个的话请求会被 JwtFilter 拦截两次
    @Bean
    public FilterRegistrationBean<Filter> registration(JwtFilter filter) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>(filter);
        registration.setEnabled(false);
        return registration;
    }
}

