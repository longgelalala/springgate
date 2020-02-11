package com.zypcy.springcloud.dynamicroute.service.impl;

import com.zypcy.springcloud.dynamicroute.entity.PagePermission;
import com.zypcy.springcloud.dynamicroute.respository.PagePermissionDefinitionMapper;
import com.zypcy.springcloud.dynamicroute.service.inf.PagePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PagePermissionServiceImpl implements PagePermissionService {

    @Autowired
    private PagePermissionDefinitionMapper pagePermissionDefinitionMapper;

    @Override
    public int add(PagePermission route) {
        route.setCreateTime(new Date());
        return pagePermissionDefinitionMapper.insertSelective(route);
    }

    @Override
    public int update(PagePermission route) {
        return pagePermissionDefinitionMapper.updateByPrimaryKeySelective(route);
    }

    @Override
    public int delete(Long id) {
        return pagePermissionDefinitionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public  List<PagePermission> getPagePermissionByRouteId(String routeId) {
        return pagePermissionDefinitionMapper.selectByRouteId(routeId);
    }

    @Override
    public  PagePermission getPagePermissionByRouteIdAndRole(String routeId,String role) {
        return pagePermissionDefinitionMapper.selectByRouteIdAndRole(routeId,role);
    }

    @Override
    public List<PagePermission> getPagePermission() {
        return pagePermissionDefinitionMapper.listAll();
    }
}
