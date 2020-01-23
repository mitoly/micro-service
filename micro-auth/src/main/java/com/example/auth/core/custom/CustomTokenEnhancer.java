package com.example.auth.core.custom;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Token信息
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) accessToken);
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("info", ""); // 添加信息
        token.setAdditionalInformation(additionalInfo);
        return token;
    }
}
