package com.example.oneapibackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName interface_info
 */
@TableName(value ="interface_info")
@Data
public class InterfaceInfo implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 对应的SDK函数名
     */
    private String reflectName;

    /**
     * 接口描述信息
     */
    private String description;
    /**
     *域名
     */
    private String domain;

    /**
     * 方法
     */
    private String method;

    /**
     * 路径
     */
    private String path;

    /**
     * 请求头
     */
    private String headers;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 响应头
     */
    private String responseHeaders;

    /**
     * 接口状态
     * ON：上线 OFF：下线
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        InterfaceInfo other = (InterfaceInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getReflectName() == null ? other.getReflectName() == null : this.getReflectName().equals(other.getReflectName()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getHeaders() == null ? other.getHeaders() == null : this.getHeaders().equals(other.getHeaders()))
            && (this.getRequestParams() == null ? other.getRequestParams() == null : this.getRequestParams().equals(other.getRequestParams()))
            && (this.getResponseHeaders() == null ? other.getResponseHeaders() == null : this.getResponseHeaders().equals(other.getResponseHeaders()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getReflectName() == null) ? 0 : getReflectName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getHeaders() == null) ? 0 : getHeaders().hashCode());
        result = prime * result + ((getRequestParams() == null) ? 0 : getRequestParams().hashCode());
        result = prime * result + ((getResponseHeaders() == null) ? 0 : getResponseHeaders().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", reflectName=").append(reflectName);
        sb.append(", description=").append(description);
        sb.append(", method=").append(method);
        sb.append(", path=").append(path);
        sb.append(", headers=").append(headers);
        sb.append(", requestParams=").append(requestParams);
        sb.append(", responseHeaders=").append(responseHeaders);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}