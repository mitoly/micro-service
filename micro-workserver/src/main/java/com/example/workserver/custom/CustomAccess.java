package com.example.workserver.custom;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Component
public class CustomAccess{

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        String uri = request.getRequestURI();
        Object obj = authentication.getPrincipal();
        Collection<SimpleGrantedAuthority> authorityList = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
        return authorityList.contains(new SimpleGrantedAuthority(uri));
    }

}
