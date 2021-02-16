package com.example.auth.core.config;

import com.example.auth.core.custom.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * SpringSecurity配置类，继承WebSecurityConfigurerAdapter
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 默认实现
//        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
        // 自定义实现 密码对比
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder);
    }

    /**
     * 拦截请求，配置哪些请求需要进行拦截
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .usernameParameter("username123") // 自定义账号参数名
//                .passwordParameter("password123") // 自定义密码参数名
//                .successHandler(myAuthenticationSuccessHandler) // 自定义成功处理器
//                .failureHandler(myAuthenticationFailureHandler); // 自定义失败处理器
//        http.exceptionHandling().accessDeniedHandler(); 自定义返回值页面


        http
                .authorizeRequests()
                .antMatchers("/oauth/**", "/login/**", "/logout/**").permitAll()
//                .antMatchers("/login.html").permitAll()  // 某个请求不需要授权
//                .antMatchers("/hello").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/hello").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()  // 所有权限都需要认证
                .and()
                .formLogin().permitAll()
                .and()
                // 关闭csrf防护
                .csrf().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
