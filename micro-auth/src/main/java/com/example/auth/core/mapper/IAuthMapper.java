package com.example.auth.core.mapper;

import com.example.auth.core.entity.OAuthClientDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IAuthMapper {

//    @Select("SELECT * FROM oauth_client_details WHERE is_valid = '0' and client_id = #{clientId}")
    @Select("SELECT * FROM oauth_client_details WHERE client_id = #{clientId}")
    List<OAuthClientDetail> getOAuthClientDetails(String clientId);
}
