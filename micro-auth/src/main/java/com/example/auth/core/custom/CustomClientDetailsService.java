package com.example.auth.core.custom;

import com.example.auth.core.entity.OAuthClientDetail;
import com.example.auth.core.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 自定ClientDetail
 * 调用认证中心获取tonken的cient_id  和 client_secret等信息
 */
@Service
public class CustomClientDetailsService implements ClientDetailsService {

    @Autowired
    private IAuthService authService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        List<OAuthClientDetail> clientDetailList = authService.getOAuthClientDetails(clientId); // 从数据库中查找ClientDetail信息
        if (null != clientDetailList && clientDetailList.size() > 0) {
            OAuthClientDetail clientDetail = clientDetailList.get(0);

            BaseClientDetails details = new BaseClientDetails();
            details.setClientId(clientDetail.getClientId());
            details.setClientSecret(clientDetail.getClientSecret());
            details.setAuthorizedGrantTypes(Arrays.asList(clientDetail.getAuthorizedGrantTypes(), "refresh_token")); // 配置认证模式
            details.setScope(Arrays.asList(clientDetail.getScope())); // 配置scope 常用 read write trust 等
            details.setRegisteredRedirectUri(Collections.singleton(clientDetail.getWebServerRedirectUri())); // 重定向路径，授权码模式获取授权码
            if (clientDetail.getAutoApprove()) { // 是否自动授权，授权模式采用
                details.setAutoApproveScopes(Arrays.asList(clientDetail.getScope()));
            }
            details.setAccessTokenValiditySeconds(clientDetail.getAccessTokenValidity()); // token过期时间
            details.setRefreshTokenValiditySeconds(clientDetail.getRefreshTokenValidity()); // 刷新token过期时间
            return details;
        } else {
            throw new ClientRegistrationException(String.format("client id {0} does not exist!", clientId));
        }
    }

}
