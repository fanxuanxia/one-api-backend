package com.example.oneapibackend.config;

import com.example.oneapibackend.common.BaseResponse;
import com.example.oneapibackend.common.ErrorCode;
import com.example.oneapibackend.model.entity.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    private static final String  LOGIN_STATUS = "LOGIN_STATUS";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        HttpSession session = request.getSession();
        UserInfo userObject = (UserInfo)session.getAttribute(LOGIN_STATUS);
        //如果不是登录请求，也不是注册请求的话
//        if(!request.getRequestURI().contains("login") && !request.getRequestURI().contains("register")) {
//            if (userObject == null) {
//                System.out.println(request.getRequestURI());
//                authFailOutPut(response, "登录信息不存在，请重新登录");
//                return false;
//            }
//        }

        return true;
    }

    private void authFailOutPut(HttpServletResponse response , String errmsg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(BaseResponse.error(ErrorCode.FORBIDDEN_ERROR, errmsg)));
        writer.flush();
    }
}
