package com.example.workserver.config;

import com.example.workserver.custom.CustomAccessTokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;

/**
 * 资源服务类，解析认证token
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;
    @Autowired
    private CustomAccessTokenConverter customAccessTokenConverter;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().permitAll()
//                .antMatchers("/user/**").permitAll()
//                .antMatchers("/test/hi").hasAnyRole("USER") // 需要角色USER
//                .antMatchers("/test/hello").hasAnyRole("ADMIN")
//                .antMatchers("/test/getPrinciple").permitAll()
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//                .addFilterBefore(corsFilter(), ChannelProcessingFilter.class);
        http.csrf().disable();
        // 自定义权限访问
//        http.authorizeRequests().anyRequest().access("@customAccess.hasPermission(request, authentication)");
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 获取自定义jwt数据，添加转换器
        converter.setAccessTokenConverter(customAccessTokenConverter);
        converter.setSigningKey("key");
        return converter;
    }

    @Bean
    public Filter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Content-Type");

        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("GET");

        config.addExposedHeader("Authorization");
        config.addExposedHeader("Content-Type");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

}
