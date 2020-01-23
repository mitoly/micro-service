package com.example.workserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface IUserMapper {

    @Select("SELECT * FROM ts_user WHERE id = #{id}")
    Map<String, Object> getUserById(String id);

}
