package com.example.workserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Configuration
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        String header = request.getHeader("Authorization");

        // 待完成，校验权限路由
        //跳过不需要验证的路径
//        if(null != WebSecurityConfig.AUTH_WHITELIST&& Arrays.asList(WebSecurityConfig.AUTH_WHITELIST).contains(url)){
//            chain.doFilter(request, response);
//            return;
//        }
//
//        if(null != WebSecurityConfig.AUTH_WHITELIST&& Arrays.asList(WebSecurityConfig.AUTH_WHITELIST).contains(url)){
//            chain.doFilter(request, response);
//            return;
//        }

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication.getAuthorities());
        Collection<? extends GrantedAuthority> grantedAuthorityCollection  = authentication.getAuthorities();
        boolean hasPermission = false;
        for (GrantedAuthority authority : grantedAuthorityCollection) {
            if (authority.getAuthority().equals(url)) {
                hasPermission = true;
                break;
            }
        }
        hasPermission = false;
        if (hasPermission) {
            filterChain.doFilter(request, response);
        } else {
//            JSONObject json = new JSONObject();
//            json.put("msg", "您没有权限");
//            String result = JSON.toJSONString(json);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("你没有权限");
            logger.error("Token验证失败，url：" + url + "\r\ntoken："+ header + "\r\nresult：" + "result");
        }
    }
}
