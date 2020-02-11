package com.zypcy.springcloud.dynamicroute.entity;


import lombok.Data;

import java.util.Date;

@Data
public class PagePermission {


    private Long id;
    private Date createTime;
    //路由的Id
    private String routeId;
    private String role;
    private String permissionValue;


    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PagePermission() {

    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    @Override
    public String toString() {
        return "PagePermission{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", routeId='" + routeId + '\'' +
                ", role='" + role + '\'' +
                ", permissionValue='" + permissionValue + '\'' +
                '}';
    }

    public PagePermission(Long id, Date createTime, String routeId, String role, String permissionValue) {
        this.id = id;
        this.createTime = createTime;
        this.routeId = routeId;
        this.role = role;
        this.permissionValue = permissionValue;
    }

}
