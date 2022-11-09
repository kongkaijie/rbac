package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Override
//    public void configure (AuthenticationManagerBuilder auth) throws Exception {
//        //User是sp.security.core.userDetails.User
//        auth.inMemoryAuthentication()
//                .withUser(User.withUsername("小明").password("123").authorities("ROLE_ADMIN"))
//                .withUser(User.withUsername("小红").password("123").authorities("read"));
//    }//通过内存的形式把用户的信息写入，规定id password 什么权限

    // User是sp.security.core.userDetails.User
//        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("").authoritiesByUsernameQuery("")

    //通过jdbc的形式把用户的信息写入，规定id password 什么权限,需要配置数据库
//}
        //其他方法只有当类；第三种方法新增加两个类;Custom data stores with userDetailsService
        // 是CustomUserDetailsService实现UserDetailsService，然后重写里面的方法
    public final UserDetailsService userDetailsService;

    public SecurityConfiguration (UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override//初始化认证相关的配置
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    //鉴权
    @Override//初始化鉴权相关的配置
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //endpoint需要的权限
                .mvcMatchers(HttpMethod.GET,"/task/users").hasAuthority("admin111")//hasRole不加ROLE_
//                .mvcMatchers(HttpMethod.GET,"/task/manUsers")
//                .access("hasRole('ADMIN') or hasRole('read')")//另一种表达式写法
                .anyRequest().authenticated()//其他接口都需要认证
                .and()
                .formLogin()
                .and()
                .httpBasic();
        //and，支持浏览器表单认证和postman client访问
    }


    @Bean//对于密码不做任何加密的意思
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();//不能不写
    }
}
