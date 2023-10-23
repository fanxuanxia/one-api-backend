package com.example.oneapibackend.mapper;

import com.example.oneapibackend.model.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author fxx
* @description 针对表【user_info】的数据库操作Mapper
* @createDate 2023-08-23 14:21:16
* @Entity com.example.oneapidemo.model.entity.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {


}




