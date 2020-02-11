package com.zypcy.springcloud.dynamicroute.service.inf;

import com.zypcy.springcloud.dynamicroute.entity.PagePermission;

import java.util.List;

public interface PagePermissionService {

    int add(PagePermission route);

    int update(PagePermission route);

    int delete(Long id);

    List<PagePermission> getPagePermissionByRouteId(String routeId);

    List<PagePermission> getPagePermission();

    PagePermission getPagePermissionByRouteIdAndRole(String routeId,String role);
}
