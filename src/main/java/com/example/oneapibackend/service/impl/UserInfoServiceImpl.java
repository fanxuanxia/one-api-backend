package com.example.oneapibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oneapibackend.common.BaseResponse;
import com.example.oneapibackend.common.BusinessException;
import com.example.oneapibackend.common.ErrorCode;
import com.example.oneapibackend.model.dto.user.UserLoginDto;
import com.example.oneapibackend.model.entity.UserInfo;
import com.example.oneapibackend.model.response.IdResponse;
import com.example.oneapibackend.service.UserInfoService;
import com.example.oneapibackend.mapper.UserInfoMapper;
import com.example.oneapibackend.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
* @author fxx
* @description 针对表【user_info】的数据库操作Service实现
* @createDate 2023-08-23 14:21:16
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{
    @Autowired
    private UserInfoMapper userInfoMapper;

    private static final String  LOGIN_STATUS = "LOGIN_STATUS";

    @Override
    public BaseResponse<Object> UserRegister(String account, String password) {
        if (StringUtils.isAnyEmpty(account, password)){
            return BaseResponse.error(ErrorCode.PARAMS_ERROR);
        }

        if(account.length() <4){
            return BaseResponse.error(ErrorCode.PARAMS_ERROR);
        }

        if (password.length()<8){
            return BaseResponse.error(ErrorCode.PARAMS_ERROR);
        }

//        if( !StringUtils.equals(password)){
//            return ErrorCode.PARAMS_ERROR.getCode();
//        }
        //用户是否已经存在
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper();
        QueryWrapper<UserInfo> queryResult = queryWrapper.eq("account", account);
        if(count(queryResult)>0){
            return BaseResponse.error(ErrorCode.PARAMS_ERROR);
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setName(account);
        userInfo.setAccount(account);
        userInfo.setPassword(password);
//        userInfo.setPassword(Encipher.doEncipher(password));
        userInfo.setRole("member");
        userInfo.setPortrait("https://img.alicdn.com/imgextra/i1/710600684/O1CN01OwjnvQ1GvJkcNOcpb_!!710600684.jpg");
        int insert = userInfoMapper.insert(userInfo);
        if(insert ==1){
            return BaseResponse.success(new IdResponse(userInfo.getId()));
        }else {
            return BaseResponse.error(ErrorCode.OPERATION_ERROR);
        }

    }


    @Override
    public UserLoginDto userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //用户是否已经存在
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper();
//        String encPassword = Encipher.doEncipher(password);

        //根据用户名和密码查询是否存在
        queryWrapper.eq("name", username);
        queryWrapper.eq("password", password);
        UserInfo user = userInfoMapper.selectOne(queryWrapper);
        if (user == null){
            return null;
        }

        //保存用户的登录状态，在session里面设置一个键值对，key是“LOGIN_STATUS”, value是 用户信息
        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_STATUS", user);
        // 设置 Session 的过期时间（可选）
        int sessionTimeoutMinutes = 7*24*60; // 设置为 7天
        session.setMaxInactiveInterval(sessionTimeoutMinutes * 60);

        // 创建 Cookie 对象，将 Session ID 添加到 Cookie 中
        String sessionID = session.getId();
        Cookie cookie = new Cookie("JSESSIONID", sessionID);
        // 设置 Cookie 的过期时间（单位为秒），与 Session 的过期时间保持一致
        cookie.setMaxAge(sessionTimeoutMinutes * 60);
        // 设置 Cookie 的有效路径,该域名下的所有站点都可以地访问该cookie
        cookie.setPath("/");
        // 将 Cookie 添加到响应中
        response.addCookie(cookie);

        //构造返回出去的结果，因为有些东西不用返回给前端，比如密码，所以new一个新的对象
        UserLoginDto safetyUserInfo = new UserLoginDto();
        safetyUserInfo.setId(user.getId());
        safetyUserInfo.setUsername(user.getName());
        safetyUserInfo.setAccount(user.getAccount());
        safetyUserInfo.setRole(user.getRole());
        safetyUserInfo.setPortrait(user.getPortrait());
        safetyUserInfo.setToken(TokenUtil.sign(safetyUserInfo));
        System.out.println(safetyUserInfo.getToken());

        return safetyUserInfo;
    }

    public List<UserInfo> getUserByName(String name){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name", name);
        List<UserInfo> user = userInfoMapper.selectList(queryWrapper);
        return  user;
    }

    @Override
    public UserInfo getUserInfo(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("id", id);
        UserInfo user = userInfoMapper.selectOne(queryWrapper);
        return  user;
    }

    @Override
    public UserInfo getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(LOGIN_STATUS);
        UserInfo currentUser = (UserInfo) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 从数据库查询
        Integer userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }


}




