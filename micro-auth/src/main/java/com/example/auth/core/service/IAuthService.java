package com.example.auth.core.service;

import com.example.auth.core.entity.OAuthClientDetail;
import com.example.auth.core.entity.User;

import java.util.List;

public interface IAuthService {

    User getUserByName(String name);

    List<OAuthClientDetail> getOAuthClientDetails(String clientId);
}
