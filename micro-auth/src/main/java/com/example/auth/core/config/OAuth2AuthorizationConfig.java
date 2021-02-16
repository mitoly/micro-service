package com.example.auth.core.config;

import com.example.auth.core.custom.CustomClientDetailsService;
import com.example.auth.core.custom.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

/**
 * OAuth2 授权配置读取类
 */
//@EnableResourceServer
@Configuration
@EnableAuthorizationServer //开启授权服务的功能
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    @Qualifier("dataSource")
//    private DataSource dataSource;

    //将Token存储在内存中
    //private TokenStore tokenStore = new InMemoryTokenStore();

//    JdbcTokenStore tokenStore=new JdbcTokenStore(dataSource);
//    TokenStore tokenStore=new InMemoryTokenStore();

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenStore jwtTokenStore;

    @Autowired
    @Qualifier("accessTokenConverter")
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenEnhancer tokenEnhancer;

    @Autowired
    private CustomClientDetailsService customClientDetailsService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customClientDetailsService); // 从数据库中查询信息
//        clients.inMemory() //将客户端的信息存储在内存中
//                .withClient("browser") //创建了一个client名为browser的客户端
//                .authorizedGrantTypes("refresh_token", "password")//配置验证类型
//                .scopes("ui")//配置客户端域为“ui”
//                .secret("111111")
//                .and()
//                .withClient("workserver")
//                .secret("123456")
//                .authorizedGrantTypes("client_credentials", "refresh_token","password")
//                .scopes("server");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer, jwtAccessTokenConverter));

        endpoints
                .tokenStore(jwtTokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager) //WebSecurity配置好的
                .userDetailsService(customUserDetailService); //读取用户的验证信息
                //.accessTokenConverter(accessTokenConverter());  //配置JwtAccessToken转换器
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //配置获取Token的策略
        security
                .tokenKeyAccess("permitAll()") //对获取Token的请求不再拦截
                .checkTokenAccess("isAuthenticated()") //验证获取Token的验证信息
                .allowFormAuthenticationForClients();
    }



//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(true);
//        return defaultTokenServices;
//    }


//    @Bean
//    RedisTokenStore redisTokenStore(){
//        return new RedisTokenStore(redisConnectionFactory);
//    }

    //token存储数据库
//    @Bean
//    public JdbcTokenStore jdbcTokenStore(){
//        return new JdbcTokenStore(dataSource);
//    }

}
