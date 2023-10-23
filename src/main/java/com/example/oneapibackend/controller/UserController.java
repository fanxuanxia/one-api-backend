package com.example.oneapibackend.controller;

import com.example.oneapibackend.common.BaseResponse;
import com.example.oneapibackend.common.ErrorCode;
import com.example.oneapibackend.model.dto.user.UserLoginDto;
import com.example.oneapibackend.model.entity.UserInfo;
import com.example.oneapibackend.model.request.UserLoginRequest;
import com.example.oneapibackend.model.request.UserRegisterRequest;
import com.example.oneapibackend.service.UserInfoService;
import com.example.oneapibackend.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/register")
    public BaseResponse<Object> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null){
            return null;
        }
        String account = userRegisterRequest.getAccount();
        String password = userRegisterRequest.getPassword();

        if(StringUtils.isAnyEmpty(account, password)){
            return null;
        }

        return userInfoService.UserRegister(account, password);

    }

    @PostMapping("/login")
    public BaseResponse<UserLoginDto> UserLogin(@RequestBody UserLoginRequest userLoginRequest , HttpServletRequest request, HttpServletResponse response){
        if(userLoginRequest == null){
            return null;
        }
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        UserLoginDto user = userInfoService.userLogin(username, password, request,response);
        return BaseResponse.success(user);

    }

    @PostMapping("/search")
    public List<UserInfo> SearchUserByName(String name, HttpServletRequest request){
        if(name == null){
            return null;
        }
        UserInfo user =(UserInfo) request.getSession().getAttribute("LOGIN_STATUS");

        //只有管理员才可以看用户列表
        if(user ==null || user.getRole() !="admin"){
            return null;
        }

        return userInfoService.getUserByName(name);
    }

    @GetMapping("/info")
    public BaseResponse<UserLoginDto> getCurrentUserInfo( HttpServletRequest request){
        String token = request.getHeader("Access-Token"); //从前端的请求头中获取Access-token
        Integer id = Integer.parseInt(TokenUtil.extractParams(token, "id"));  //解析出用户id
        UserInfo userInfo = userInfoService.getUserInfo(id);
        if(userInfo !=null ){
            return BaseResponse.success(userInfo);
        }else return BaseResponse.error(ErrorCode.NOT_FOUND_ERROR, ErrorCode.NOT_FOUND_ERROR.getDescription());
    }


    @PostMapping("/logout")
    public Boolean Logout(){
        return  true;
    }




//    @PostMapping("/delete")
//    public BaseResponse deleteUser(Integer id, HttpServletRequest request){
//        if(id <0){
//            return BaseResponse.error(ErrorCode.PARAMS_ERROR,ErrorCode.PARAMS_ERROR.getDescription());
//        }
//        UserInfo user =(UserInfo) request.getSession().getAttribute("LOGIN_STATUS");
//
//        //只有管理员才可以看用户列表
//        if(user ==null || user.getRole() !="admin"){
//            return BaseResponse.error(ErrorCode.NO_AUTH_ERROR,ErrorCode.NO_AUTH_ERROR.getDescription());
//        }
//
//        return userInfoService.getUserByName(name);
//    }
}