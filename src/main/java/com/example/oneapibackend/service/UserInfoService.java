package com.example.oneapibackend.service;

import com.example.oneapibackend.common.BaseResponse;
import com.example.oneapibackend.model.dto.user.UserLoginDto;
import com.example.oneapibackend.model.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author fxx
* @description 针对表【user_info】的数据库操作Service
* @createDate 2023-08-23 14:21:16
*/
public interface UserInfoService extends IService<UserInfo> {
    BaseResponse<Object> UserRegister(String account, String password);

    UserLoginDto userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
    List<UserInfo> getUserByName(String name);

    UserInfo getUserInfo(Integer id);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    UserInfo getLoginUser(HttpServletRequest request);
}
