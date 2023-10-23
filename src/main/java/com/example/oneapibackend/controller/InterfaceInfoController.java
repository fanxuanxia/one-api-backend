package com.example.oneapibackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oneapibackend.common.BaseResponse;
import com.example.oneapibackend.common.ErrorCode;
import com.example.oneapibackend.common.InterfaceStatus;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInfoModifyRequest;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.example.oneapibackend.model.dto.interfaceInfo.InterfaceInvokeRequest;
import com.example.oneapibackend.model.entity.InterfaceInfo;
import com.example.oneapibackend.model.request.IdRequest;
import com.example.oneapibackend.model.response.IdResponse;
import com.example.oneapibackend.service.InterfaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/interfaceInfo")
public class InterfaceInfoController {

    @Autowired
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("/getApi")
    public BaseResponse<InterfaceInfo> getInterfaceInfoByPage(@RequestBody InterfaceInfoQueryRequest interfaceInfoQueryRequest,
                                                              HttpServletRequest request){
        long current = interfaceInfoQueryRequest.getCurrent();
        long size = interfaceInfoQueryRequest.getPageSize();
        // 限制爬虫
        if(size >20 ){
            throw new RuntimeException(ErrorCode.PARAMS_ERROR.getDescription());
        }

        Page<InterfaceInfo> page = interfaceInfoService.page(new Page<>(current, size),
                interfaceInfoService.getQueryWrapper(interfaceInfoQueryRequest));

        return BaseResponse.success(interfaceInfoService.getInterfaceInfoVOByPage(page, request));
    }

    @GetMapping("/detail")
    public BaseResponse<InterfaceInfo> getDetailById(@RequestParam("id") Integer id){
        if(id < 0){
            return BaseResponse.error(ErrorCode.PARAMS_ERROR);
        }
        return interfaceInfoService.getDetailById(id);
    }
    @GetMapping("/getAllApi")
    public BaseResponse<List<InterfaceInfo>>getAllApi (){
        return BaseResponse.success( interfaceInfoService.list());
    }



    @PostMapping("/add")
    public BaseResponse<IdResponse> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest interfaceInfoAddRequest){
        if(interfaceInfoAddRequest == null){
            return BaseResponse.error(ErrorCode.PARAMS_ERROR, ErrorCode.PARAMS_ERROR.getDescription());
        }
        Integer id = interfaceInfoService.addInterfaceInfo(interfaceInfoAddRequest);
        return id>0? BaseResponse.success(new IdResponse(id)): BaseResponse.error(ErrorCode.OPERATION_ERROR, ErrorCode.OPERATION_ERROR.getDescription());

    }

    @PostMapping("/modify")
    public BaseResponse<IdResponse> modifyInterfaceInfo(@RequestBody InterfaceInfoModifyRequest infoModifyRequest){
        if(infoModifyRequest == null){
            return BaseResponse.error(ErrorCode.PARAMS_ERROR, ErrorCode.PARAMS_ERROR.getDescription());
        }
        return interfaceInfoService.modifyInterfaceInfo(infoModifyRequest);

    }

    @PostMapping("/onlineInterface")
    public BaseResponse<InterfaceInfo> onlineInterface(@RequestBody IdRequest idRequest){
        if(idRequest ==null || idRequest.getId()<0){
            return BaseResponse.error(ErrorCode.PARAMS_ERROR, ErrorCode.PARAMS_ERROR.getDescription());
        }
       return interfaceInfoService.UpdateStatus(idRequest.getId(), InterfaceStatus.ONLINE.getCode());

    }

    @PostMapping("/offlineInterface")
    public BaseResponse<InterfaceInfo> offlineInterface(@RequestBody IdRequest idRequest){
        if(idRequest ==null || idRequest.getId()<0){
            return BaseResponse.error(ErrorCode.PARAMS_ERROR, ErrorCode.PARAMS_ERROR.getDescription());
        }

        return interfaceInfoService.UpdateStatus(idRequest.getId(), InterfaceStatus.OFFLINE.getCode());
    }


    @PostMapping("/invoke")
    public BaseResponse<String> invokeInterface(@RequestBody InterfaceInvokeRequest interfaceInvokeRequest, HttpServletRequest request){
        if(interfaceInvokeRequest ==null || interfaceInvokeRequest.getInterfaceId() <0){
            return BaseResponse.error(ErrorCode.PARAMS_ERROR);
        }

       return interfaceInfoService.invokeInterface(interfaceInvokeRequest,request);
    }

}
