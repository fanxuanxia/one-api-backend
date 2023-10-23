package com.example.oneapibackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oneapibackend.common.BaseResponse;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInfoModifyRequest;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInvokeRequest;
import com.example.oneapibackend.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.oneapibackend.model.response.IdResponse;

import javax.servlet.http.HttpServletRequest;

/**
* @author fxx
* @description 针对表【interface_info】的数据库操作Service
* @createDate 2023-08-26 15:12:09
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    Integer addInterfaceInfo(InterfaceInfoAddRequest interfaceInfoAddRequest);
    BaseResponse<IdResponse> modifyInterfaceInfo(InterfaceInfoModifyRequest modifyRequestRequest);

    BaseResponse<InterfaceInfo>getDetailById(Integer id);

    BaseResponse UpdateStatus(Integer id, String status);

    BaseResponse<String> invokeInterface(InterfaceInvokeRequest interfaceInvokeRequest, HttpServletRequest request);
    QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest interfaceInfoQueryRequest);
    Page<InterfaceInfo> getInterfaceInfoVOByPage(Page<InterfaceInfo> page, HttpServletRequest request);

}
