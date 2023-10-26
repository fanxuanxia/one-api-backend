package com.example.oneapibackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oneapibackend.common.InterfaceStatus;
import com.example.oneapibackend.common.ErrorCode;
import com.example.oneapibackend.common.BaseResponse;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInfoModifyRequest;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInvokeRequest;
import com.example.oneapibackend.model.entity.InterfaceInfo;
import com.example.oneapibackend.model.response.IdResponse;
import com.example.oneapibackend.service.InterfaceInfoService;
import com.example.oneapibackend.mapper.InterfaceInfoMapper;
import com.example.oneapibackend.service.UserInfoService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.*;
import java.util.List;

/**
 * @author fxx
 * @description 针对表【interface_info】的数据库操作Service实现
 * @createDate 2023-08-26 15:12:09
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
        implements InterfaceInfoService {

    @Autowired
    InterfaceInfoMapper interfaceInfoMapper;

    @Autowired
    UserInfoService userInfoService;

    private static final String AK = "access_key";
    private static final String SK = "secret_Key";

    /**
     * 添加接口
     *
     * @param interfaceInfoAddRequest
     * @return
     */
    public Integer addInterfaceInfo(InterfaceInfoAddRequest interfaceInfoAddRequest) {

        //todo  校验入参

        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setName(interfaceInfoAddRequest.getName());
        interfaceInfo.setDescription(interfaceInfoAddRequest.getDescription());
        interfaceInfo.setDomain(interfaceInfoAddRequest.getDomain());
        interfaceInfo.setMethod(interfaceInfoAddRequest.getMethod());
        interfaceInfo.setPath(interfaceInfoAddRequest.getPath());
        interfaceInfo.setHeaders(interfaceInfoAddRequest.getHeaders());
        interfaceInfo.setRequestParams(interfaceInfoAddRequest.getRequestParams());
        interfaceInfo.setResponseHeaders(interfaceInfoAddRequest.getResponseHeaders());
        interfaceInfo.setStatus(InterfaceStatus.INIT.getCode());

        Integer res = interfaceInfoMapper.insert(interfaceInfo);
        if (res > 0) {
            return interfaceInfo.getId();
        } else return -1;
    }


    /**
     * 修改接口信息的接口
     *
     * @param InterfaceInfoModifyRequest
     * @return
     */
    public BaseResponse<IdResponse> modifyInterfaceInfo(InterfaceInfoModifyRequest modifyRequestRequest) {

        //todo  校验入参
        //先从数据库查一下接口吧
        InterfaceInfo info = interfaceInfoMapper.selectById(modifyRequestRequest.getId());
        if (info == null) {
            return BaseResponse.error(ErrorCode.NOT_FOUND_ERROR);
        }
        info.setName(modifyRequestRequest.getName());
        info.setDescription(modifyRequestRequest.getDescription());
        info.setMethod(modifyRequestRequest.getMethod());
        info.setDomain(modifyRequestRequest.getDomain());
        info.setPath(modifyRequestRequest.getPath());
        info.setHeaders(modifyRequestRequest.getHeaders());
        info.setRequestParams(modifyRequestRequest.getRequestParams());
        info.setResponseHeaders(modifyRequestRequest.getResponseHeaders());
        int res = interfaceInfoMapper.updateById(info);
        return res > 0 ? BaseResponse.success(new IdResponse(res)) : BaseResponse.error(ErrorCode.OPERATION_ERROR);
    }

    @Override
    public BaseResponse<InterfaceInfo> getDetailById(Integer id) {
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectById(id);
        return interfaceInfo == null ? BaseResponse.error(ErrorCode.NOT_FOUND_ERROR) : BaseResponse.success(interfaceInfo);

    }

    /**
     * 更改接口状态，处理上线或者下线，仅管理员可操作
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public BaseResponse UpdateStatus(Integer id, String status) {
        //1.先查询接口是不是存在
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectById(id);
        if (interfaceInfo == null) {
            return BaseResponse.error(ErrorCode.NOT_FOUND_ERROR, ErrorCode.NOT_FOUND_ERROR.getDescription());
        }
        //2、判断是不是已经上线/下线了，已上线直接返回
        if (status.equals(interfaceInfo.getStatus())) {
            return BaseResponse.success(interfaceInfo);
        }

        //3.接口存在，并且不是请求的状态，才开始处理
        interfaceInfo.setStatus(status);
        int update = interfaceInfoMapper.updateById(interfaceInfo);
        return update == 1 ? BaseResponse.success(interfaceInfo) : BaseResponse.error(ErrorCode.OPERATION_ERROR);

    }

    @Override
    public BaseResponse<String> invokeInterface(InterfaceInvokeRequest interfaceInvokeRequest, HttpServletRequest request) {
//        Integer id = interfaceInvokeRequest.getInterfaceId();
//        InterfaceInfo info = interfaceInfoMapper.selectById(id);
//
//        // 接口不存在，或者状态不是上线
//        if (info == null || !InterfaceStatus.ONLINE.getCode().equals(info.getStatus())) {
////            return BaseResponse.error(ErrorCode.PARAMS_ERROR,"请求的接口不存在");
//            return BaseResponse.error(ErrorCode.NOT_FOUND_ERROR);
//        }
//
//        // 根据请求获取当前登录的用户，并取出该用户的ak和sk
////        UserInfo user = userInfoService.getLoginUser(request);
////        String accessKey = user.getAccessKey();
////        String secretKey = user.getSecretKey();
//
//        String accessKey = "sdfghjkl";
//        String secretKey = "12345678";
//        // 要反射的方法名
//        String reflectName = info.getReflectName();
//        ApiClient apiClient = new ApiClient(accessKey, secretKey);
//
//        // 获取到所有的方法
//        Method[] methods = apiClient.getClass().getDeclaredMethods();
//        Method method = null;
//
//        // 查找方法是否存在
//        for (Method m : methods) {
//            if (m.getName().equals(reflectName)) {
//                method = m;
//                break;
//            }
//        }
//        if (method != null) {
//            // 反射调用方法
//            try {
//                Object invokeResult = method.invoke(apiClient, interfaceInvokeRequest.getUserRequestBody());
//                System.out.println(invokeResult.toString());
//                return  BaseResponse.success(invokeResult);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            } catch (InvocationTargetException e) {
//                throw new RuntimeException(e);
//            }
//        }

//        return BaseResponse.error(ErrorCode.NOT_FOUND_ERROR);
        return BaseResponse.success(null);
    }


    /**
     * 查询
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest interfaceInfoQueryRequest) {
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        if (interfaceInfoQueryRequest == null) {
            return queryWrapper;
        }

        Integer id = interfaceInfoQueryRequest.getId();
        String nameQuery = interfaceInfoQueryRequest.getName();
        String descQuery = interfaceInfoQueryRequest.getDescription();
        String methodQuery = interfaceInfoQueryRequest.getMethod();
        String pathQuery = interfaceInfoQueryRequest.getPath();
        String statusQuery = interfaceInfoQueryRequest.getStatus();
        String sortField = interfaceInfoQueryRequest.getSortField();
        Boolean isAsc = interfaceInfoQueryRequest.getIsAsc();

        // 拼接查询条件
        queryWrapper.like(StringUtils.isNotBlank(nameQuery), "name", nameQuery);
        queryWrapper.like(StringUtils.isNotBlank(descQuery), "desc", descQuery);
        queryWrapper.like(StringUtils.isNotBlank(pathQuery), "path", pathQuery);
//        if (CollectionUtils.isNotEmpty(tagList)) {
//            for (String tag : tagList) {
//                queryWrapper.like("tags", "\"" + tag + "\"");
//            }
//        }
//        queryWrapper.ne(ObjectUtils.isNotEmpty(notId), "id", notId);
//        queryWrapper.eq("isDelete", false);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(methodQuery), "method", methodQuery);
        queryWrapper.eq(ObjectUtils.isNotEmpty(statusQuery), "status", statusQuery);

//        queryWrapper.orderBy(SqlUtil.validSortField(sortField),isAsc, sortField);
        return queryWrapper;
    }

    @Override
    public Page<InterfaceInfo> getInterfaceInfoVOByPage(Page<InterfaceInfo> page, HttpServletRequest request) {
        List<InterfaceInfo> interfaceInfoList = page.getRecords();
        Page<InterfaceInfo> interfaceInfoPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        interfaceInfoPage.setRecords(interfaceInfoList);

        return interfaceInfoPage;
    }
}




